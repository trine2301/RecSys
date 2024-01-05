package recsys.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import recsys.model.AccountingTransactionEntity;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


/**
 * Repository for managing AccountingTransactionEntity objects.
 * Provides methods for fetching all transactions and finding
 * transactions based on a date range.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
@Repository
public abstract class AccountingTransactionRepository implements CrudRepository<AccountingTransactionEntity, Long> {

    /**
     * CRUD-method to find all accounting transaction entities.
     *
     * @return Returns all transaction entities.
     */
    @ReadOnly
    @NonNull
    @Transactional
    public abstract List<AccountingTransactionEntity> findAll();

    /**
     * CRUD-method to find a date between a given time period.
     *
     * @param startDate The start of the time period.
     * @param endDate   The end of the time-period.
     * @return Returns a list of accounting transactions entities
     * within the given time-frame.
     */
    public abstract List<AccountingTransactionEntity> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
