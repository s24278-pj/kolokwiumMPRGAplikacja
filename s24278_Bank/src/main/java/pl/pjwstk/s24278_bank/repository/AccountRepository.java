package pl.pjwstk.s24278_bank.repository;

import org.springframework.stereotype.Repository;
import pl.pjwstk.s24278_bank.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private List<Account> accountList = new ArrayList<>();

    public void create(Account account) throws Exception {
        if (accountList.contains(account)) {
            throw new Exception();
        }
        accountList.add(account);
    }

    public Optional<Account> findByID(int id) {
        return accountList.stream().filter(it -> it.getId() == id).findFirst();
    }

    public void removeAll() {
        accountList = new ArrayList<>();
    }

    public Optional<Account> withdraw(int id, int balance) throws Exception {
        Optional<Account> account = findByID(id);

        if (account.isPresent()) {
            Account acc = account.get();
            acc.setBalance(acc.getBalance() - balance);
        } else {
            throw new Exception("Not found");
        }
    }
}
