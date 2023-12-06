package com.dmdev.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalDateFormatterTest {
    @Test
    void shouldFormatDateCorrect () {
        Assertions.assertEquals(LocalDate.of(1990, 11, 11),LocalDateFormatter.format("1990-11-11"));
    }
    @Test
    void shouldThrowExceptionIfDateFormatIsIncorrect () {
        assertAll(
                () -> assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format("dummy")),
                () -> assertThrows(NullPointerException.class, () -> LocalDateFormatter.format(null)),
                () -> assertThrows(DateTimeParseException.class, () -> LocalDateFormatter.format("1990/11/11"))
        );
    }
    @Test
    void isValidShouldReturnTrue() {
        Assertions.assertTrue(LocalDateFormatter.isValid("1990-11-11"));
    }
    @Test
    void isValidShouldReturnFalse() {
        assertAll(
                () -> Assertions.assertFalse(LocalDateFormatter.isValid("1990-11-aa")),
                () -> Assertions.assertFalse(LocalDateFormatter.isValid("1990/11/11")),
                () -> Assertions.assertFalse(LocalDateFormatter.isValid(null))
        );
    }
}
