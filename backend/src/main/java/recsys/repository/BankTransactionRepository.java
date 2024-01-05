package recsys.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import recsys.model.BankTransactionEntity;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


/**
 * Repository for managing BankTransactionEntity objects.
 * Provides methods for performing CRUD operations within a transaction.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
@Repository
public abstract class BankTransactionRepository implements CrudRepository<BankTransactionEntity, Long> {

    @ReadOnly
    @NonNull
    @Transactional
    public abstract List<BankTransactionEntity> findAll();

    @NotNull
    public abstract List<BankTransactionEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
