package pl.pjwstk.s24278_bank.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DatabaseException extends RuntimeException {
    private final String message;
    private final Exception exception;
}

