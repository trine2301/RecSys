package recsys.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import recsys.model.AccountEntity;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public abstract class TransactionRepository implements CrudRepository<AccountEntity, String> {

    @Override
    public Optional<AccountEntity> findById(@NotNull String s) {
        return Optional.empty();
    }

    @Override
    public Iterable<AccountEntity> findAll() {
        return null;
    }
}
