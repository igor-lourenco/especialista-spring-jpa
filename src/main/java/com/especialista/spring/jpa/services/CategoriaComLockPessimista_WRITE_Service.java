package com.especialista.spring.jpa.services;

import com.especialista.spring.jpa.DTOs.CategoriaDTO;
import com.especialista.spring.jpa.entities.Categoria;
import com.especialista.spring.jpa.repositories.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoriaComLockPessimista_WRITE_Service {

    private final CategoriaRepository repository;

//  se esse método lançar CannotAcquireLockException, executa de novo
    @Retryable(retryFor = CannotAcquireLockException.class,
        maxAttempts = 1,                               // se falhar 1 vez chama o @Recover
        backoff = @Backoff(delay = 200)
    )
    @Transactional
    public CategoriaDTO updateComLockPessimistaUsando_WRITE(Integer id, Integer contador) {
        log.info("Buscando categoria com PESSIMISTIC_WRITE");

        Categoria categoria = repository.findByIdComLockPessimistaUsando_WRITE(id)
            .orElseThrow();

        categoria.setNome("Eletrodomésticos [" + contador + "]");

//      Com LockModeType.PESSIMISTIC_WRITE, existe garantia de exclusividade no acesso ao registro,
//      apenas uma transação por vez pode ler e modificar o dado. As demais transações ficam bloqueadas
//      até que ocorra o commit ou rollback da transação detentora do lock.
        repository.save(categoria);

        log.info("Atualizando com versão {}, {}", categoria.getVersao(), categoria.getNome());
        return new CategoriaDTO(categoria);
    }



//  Se todas as tentativas falhar, esse método é chamado
//  1º parâmetro: Exception
//  demais parâmetros: iguais ao método original
    @Recover
    public CategoriaDTO recover(CannotAcquireLockException e, Integer id, Integer contador) {
/*  — > LockModeType.PESSIMISTIC_WRITE: usado quando precisa de exclusividade total sobre um registro, garantindo que
      ninguém mais leia ou altere aquele dado enquanto sua transação estiver ativa
        - Vai trabalhar nesse dado e ninguém mais pode nem ler, nem modificar até terminar
        - É um lock exclusivo no banco de dados

        Ou seja:
          - Executa um SELECT com lock exclusivo
          - O banco:
            - bloqueia leitura com lock
            - bloqueia UPDATE
            - bloqueia DELETE
          - O lock só é liberado no:
             - commit
             - rollback
          - Só uma transação controla o registro

        Observação:
          - Quando mais de uma transação usa LockModeType.PESSIMISTIC_WRITE sobre o mesmo registro e todas tentam
          alterar, o comportamento é:
            - Somente UMA transação por vez pode obter o lock e alterar o dado.
            - As outras ficam bloqueadas na tentativa de obter o lock (já na leitura)
            - Se o lock não for liberado a tempo, falham por timeout ou deadlock
*/
        log.error("Falha após múltiplas tentativas para id: {}", id);
        throw e;
    }
}
