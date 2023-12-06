package com.dmdev.validator;

import com.dmdev.dto.CreateUserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateUserValidatorTest {

    CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserDto innaUser = CreateUserDto.builder()
            .name("testName")
            .birthday("1980-08-06")
            .email("test@gmail.com")
            .password("dummy")
            .role("USER")
            .gender("FEMALE")
            .build();
    private final CreateUserDto ivanUser = CreateUserDto.builder()
            .name("testName")
            .birthday("19800806")
            .email("test@gmail.com")
            .password(null)
            .role(null)
            .gender(null)
            .build();

    @Test
    void shouldValidate() {
        var actualResult = createUserValidator.validate(innaUser);
        Assertions.assertTrue(actualResult.getErrors().isEmpty());
        Assertions.assertTrue(actualResult.isValid());
    }
    @Test
    void shouldNotValidate() {
        var actualResult = createUserValidator.validate(ivanUser);
        Assertions.assertFalse(actualResult.isValid());
        Assertions.assertEquals(3, actualResult.getErrors().size());
        final List<Error> expErrors = buildExtError();
        final List<Error> actErrors = actualResult.getErrors();
        for (int i = 0; i < actErrors.size(); i++) {
            validateError(expErrors.get(i), actErrors.get(i));
        }
    }

    private void validateError(Error expError, Error actError) {
        Assertions.assertEquals(expError.getCode(), actError.getCode());
        Assertions.assertEquals(expError.getMessage(), actError.getMessage());
    }

    @Test
    void throwsNullPointerExceptionWhenValidating() { //TODO poor naming
        Assertions.assertThrows(NullPointerException.class, () -> createUserValidator.validate(null));
    }

    private List<Error> buildExtError() {
        List<Error> errors = new ArrayList<>();
        errors.add(Error.of("invalid.birthday", "Birthday is invalid"));
        errors.add(Error.of("invalid.gender", "Gender is invalid"));
        errors.add(Error.of("invalid.role", "Role is invalid"));
        return errors;
    }
}
