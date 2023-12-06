package com.dmdev.exception;

import com.dmdev.validator.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidationExceptionTest {
    @Test
    void validationExceptionTest() {
        List<Error> errors = Arrays.asList(
                Error.of("TestException1", "Error message 1"),
                Error.of("TestException2", "Error message 2")
        );
        ValidationException validationException = new ValidationException(errors);

        assertThrows(ValidationException.class, () -> {
            throw validationException;
        });

        Assertions.assertEquals(errors, validationException.getErrors());
    }
}
