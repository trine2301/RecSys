package recsys.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Repository
public interface TransactionRepository extends CrudRepository<AccountEntity, String> {

    @Override
    public <S extends AccountEntity> S save(@Valid @NotNull S entity);

    @Override
    public Iterable<AccountEntity> findAll();
}
