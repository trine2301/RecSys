package recsys.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import recsys.domain.AccountEntity;


/**
 * TODO
 */
@Repository
public abstract class AccountRepository implements CrudRepository<AccountEntity, String> {

    @Override
    public Iterable<AccountEntity> findAll() {
        return null;
    }

}
