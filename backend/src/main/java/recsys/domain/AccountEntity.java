package recsys.domain;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    private String accountNumber;

    private String name;



    public String getAccountNumber() {
        return this.accountNumber;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BookkeepingAccountEntity{" +
            "accountNo='" + accountNumber + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
