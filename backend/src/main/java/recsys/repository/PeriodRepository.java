package recsys.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import recsys.model.PeriodEntity;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Repository for managing PeriodEntity objects.
 * Provides methods for performing CRUD operations within a transaction.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
@Repository
public abstract class PeriodRepository implements CrudRepository<PeriodEntity, Long> {

    /**
     * CRUD-method to find all period entities.
     *
     * @return Returns all period entities.
     */
    @Transactional
    public abstract List<PeriodEntity> findAll();

    /**
     * Saves period entities.
     * @param entity The entity to save. Must not be {@literal null}.
     * @return The saved entity.
     */
    @Transactional
    public abstract PeriodEntity save(PeriodEntity entity);

}
