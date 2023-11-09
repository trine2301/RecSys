package recsys.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import io.micronaut.transaction.annotation.ReadOnly;

import lombok.RequiredArgsConstructor;
import recsys.model.PeriodEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public abstract class PeriodRepository implements CrudRepository<PeriodEntity, Long> {

    @ReadOnly
    public abstract List<PeriodEntity> findAll();


}


