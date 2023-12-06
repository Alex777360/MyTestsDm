package com.dmdev.validator;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidationResultTest {

    private Error error; //TODO private
    private ValidationResult validationResult; //TODO private

    @BeforeEach
    void prepare() {
        validationResult = new ValidationResult();
        error = Error.of("500", "Internal Server Error");
        validationResult.add(error);
    }

    @Test
    void errorShouldBeAdded() {
        Assertions.assertEquals(1, validationResult.getErrors().size());
        Assertions.assertSame(error, validationResult.getErrors().get(0));
    }
    @Test
    void listShouldBeEmpty() {
        validationResult.getErrors().clear();
        Assertions.assertTrue(validationResult.isValid());
    }
    @Test
    void listShouldNotBeEmpty() {
        Assertions.assertFalse(validationResult.isValid());
    }
}
