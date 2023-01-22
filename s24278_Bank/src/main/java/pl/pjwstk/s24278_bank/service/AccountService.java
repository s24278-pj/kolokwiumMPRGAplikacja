package pl.pjwstk.s24278_bank.service;

import org.springframework.stereotype.Service;
import pl.pjwstk.s24278_bank.exception.DatabaseException;
import pl.pjwstk.s24278_bank.exception.ValidationException;
import pl.pjwstk.s24278_bank.model.Account;
import pl.pjwstk.s24278_bank.repository.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void create(Account account) {
        if (isInvalid(account.getName())) {
            throw new ValidationException("Name is required");
        }
        if (isInvalid(account.getSurname())) {
            throw new ValidationException("Surname is required");
        }
        try {
            accountRepository.create(account);
        } catch (Exception e) {
            throw new DatabaseException("Database error ", e);
        }
    }

    private boolean isInvalid(String attribute) {
        return attribute == null || attribute.isBlank();
    }

    public Optional<Account> findById(int id) {
        Optional<Account> optionalAccount = accountRepository.findByID(id);
        if (accountRepository.findByID(id).isPresent()) {
            return optionalAccount;
        } else {
            throw new ValidationException("Can not found account");
        }
    }

    public Optional<Account> withdraw(int id, int amount) {
        if (amount < 0) {
            throw new ValidationException("Tried to withraw negative amount");

            try {
                return accountRepository.withdraw(id, amount);
            } catch (Exception e) {
                throw new ValidationException("Id not found");
            }
        }
    }
}
