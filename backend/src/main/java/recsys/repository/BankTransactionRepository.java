package recsys.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import recsys.model.BankTransactionEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public abstract class BankTransactionRepository implements CrudRepository<BankTransactionEntity, String> {

    @ReadOnly
    @NonNull
    @Transactional
    public abstract List<BankTransactionEntity> findAll();
}
