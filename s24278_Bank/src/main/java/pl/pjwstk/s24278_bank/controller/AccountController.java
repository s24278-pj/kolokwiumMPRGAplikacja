package pl.pjwstk.s24278_bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.s24278_bank.exception.DatabaseException;
import pl.pjwstk.s24278_bank.exception.ValidationException;
import pl.pjwstk.s24278_bank.model.Account;
import pl.pjwstk.s24278_bank.service.AccountService;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        try {
            accountService.create(account);
        } catch (ValidationException validationException) {
            return ResponseEntity.badRequest().build();
        } catch (DatabaseException databaseException) {
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(account);
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccount(@PathVariable int id){
        Optional<Account> account = accountService.findById(id);

        return account.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
