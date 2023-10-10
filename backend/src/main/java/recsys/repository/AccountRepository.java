package recsys.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import recsys.model.AccountEntity;

import java.util.List;


/**
 * This interface provides methos for performing CRUD operations on the AccountEntity.
 *
 */
@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, String> {

    //TODO implement methods if needed.
    public List<AccountEntity> findAll();
}
