package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.entities.Categoria;
import com.especialista.spring.jpa.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoriaComLockOtimistaService {

    private final CategoriaRepository repository;

//  se esse método lançar OptimisticLockingFailureException, executa de novo
    @Retryable(retryFor = OptimisticLockingFailureException.class,
        maxAttempts = 8,                                // se falhar 8 vezes chama o @Recover
        backoff = @Backoff(delay = 100, multiplier = 3) // tempo de espera entre as tentativas: 1 → 2 100 ms | 2 → 3 200 ms | 3 → 4 400 ms
    )
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Cada tentativa do retry roda em uma NOVA transação
    public CategoriaDTO updateComLockOtimista(Integer id) {

//     retorna quantas tentativas já ocorreram, começa com 0
        int retryNumber = RetrySynchronizationManager.getContext().getRetryCount();

        log.info("Retry Number: "+ retryNumber);

        String nome = "Eletrodomésticos [" + retryNumber + "]";

        Categoria categoria = repository.findById(id).orElseThrow();
        categoria.setNome(nome);

        repository.save(categoria); // não precisa do flush

        log.info("Atualizando com versão {}", categoria.getVersao());
        return new CategoriaDTO(categoria);
    }


//  Se todas as tentativas falhar, esse método é chamado
//  1º parâmetro: Exception
//  demais parâmetros: iguais ao método original
    @Recover
    public CategoriaDTO recover(OptimisticLockingFailureException e, Integer id) {

        log.error("Falha após múltiplas tentativas para id: {}", id);

        throw e;
    }

}
