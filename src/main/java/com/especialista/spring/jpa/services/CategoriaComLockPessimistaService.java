package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.entities.Categoria;
import com.especialista.spring.jpa.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoriaComLockPessimistaService {

    private final CategoriaRepository repository;

//  se esse método lançar CannotAcquireLockException, executa de novo
    @Retryable(retryFor = CannotAcquireLockException.class,
        maxAttempts = 1                               // se falhar 1 vez chama o @Recover
    )
    @Transactional
    public CategoriaDTO updateComLockPessimistaUsando_READ(Integer id, Integer contador) {
        log.info("Buscando categoria com PESSIMISTIC_READ");

        Categoria categoria = repository.updateComLockPessimistaUsando_READ(id)
            .orElseThrow();

        try { Thread.sleep(10000); // simulando processamento
        } catch (InterruptedException ignored) { }

        categoria.setNome("Eletrodomésticos [" + contador + "]");

//      Com LockModeType.PESSIMISTIC_READ *NÃO* existe garantia de que apenas um commit será executado com sucesso,
//      ou seja, pode ser executar mais de um commit com sucesso em transações simultâneas,
//      ele garante a leitura consistente não o bloqueio do update/delete enquanto a leitura etá ativa
        repository.save(categoria);

        log.info("Atualizando com versão {}, {}", categoria.getVersao(), categoria.getNome());
        return new CategoriaDTO(categoria);
    }



//  Se todas as tentativas falhar, esse método é chamado
//  1º parâmetro: Exception
//  demais parâmetros: iguais ao método original
    @Recover
    public CategoriaDTO recover(CannotAcquireLockException e, Integer id, Integer contador) {
/*  — > LockModeType.PESSIMISTIC_READ: usado para garantir que um registro não seja modificado enquanto
      sua transação está usando os dados para leitura.
        - Vai ler esse dado garantindo que ninguém vai alterá‑lo enquanto estiver trabalhando nesse dado

        Ou seja:
          - Você pode ler o registro com segurança
          - Outras transações *NÃO* podem modificá‑lo
          - Outras transações podem ler (dependendo do banco)
          - O lock dura até o commit ou rollback
 */
        log.error("Falha após múltiplas tentativas para id: {}", id);
        throw e;
    }
}
