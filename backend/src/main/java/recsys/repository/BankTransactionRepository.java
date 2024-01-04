package recsys.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import lombok.RequiredArgsConstructor;
import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class BankTransactionRepository implements CrudRepository<BankTransactionEntity, Long> {

    @ReadOnly
    @NonNull
    @Transactional
    public abstract List<BankTransactionEntity> findAll();

    //public abstract Optional<BankTransactionEntity> findById(@NotNull @NonNull Long id);

    @NotNull
    public abstract List<BankTransactionEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
