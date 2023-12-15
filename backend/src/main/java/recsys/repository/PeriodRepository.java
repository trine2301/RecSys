package recsys.repository;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import recsys.model.PeriodEntity;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public abstract class PeriodRepository implements CrudRepository<PeriodEntity, Long> {

    @Transactional
    public abstract List<PeriodEntity> findAll();

    @Transactional
    public abstract PeriodEntity save(PeriodEntity entity);

}
