package pl.pjwstk.s24278_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private final int id;
    private int balance;
    private final String name;
    private final String surname;
}
