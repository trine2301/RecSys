package recsys.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import recsys.model.AccountingTransactionEntity;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class TransactionRepository implements CrudRepository<AccountingTransactionEntity, Long> {

    @ReadOnly
    @NonNull
    @Transactional
    public abstract List<AccountingTransactionEntity> findAll();

//    @NonNull
//    public abstract Optional<AccountingTransactionEntity> findById(@NotNull @NonNull Long id);

    public abstract List<AccountingTransactionEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
