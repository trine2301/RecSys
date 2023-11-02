//package recsys.repository;
//
//import io.micronaut.data.annotation.Repository;
//import io.micronaut.data.repository.CrudRepository;
//import recsys.model.BankTransactionEntity;
//
//import javax.validation.Valid;
//import javax.validation.constraints.NotNull;
//
//
//@Repository
//public interface TransactionRepository extends CrudRepository<BankTransactionEntity, String> {
//
//    @Override
//    public <S extends BankTransactionEntity> S save(@Valid @NotNull S entity);
//
//    @Override
//    public Iterable<BankTransactionEntity> findAll();
//}
