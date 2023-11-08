package recsys.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import recsys.model.BankTransactionEntity;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class BankTransactionRepository implements CrudRepository<BankTransactionEntity, Long> {

    @ReadOnly
    @NonNull
    @Transactional
    public List<BankTransactionEntity> findAll() {
        return null;
    }

    public abstract Optional<BankTransactionEntity> findById(@NotNull @NonNull Long id);
}
