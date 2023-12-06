package com.dmdev.service;

import com.dmdev.IntegrationTestBase;
import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import com.dmdev.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserServiceTest extends IntegrationTestBase {
    UserService userService = UserService.getInstance();

    @Test
    void shouldLoginSuccessfully() {
        final User ivanUser = getUser(1, "Ivan", LocalDate.of(1990, 01, 10), "ivan@gmail.com",
                "111", Role.ADMIN, Gender.MALE);
        var actualResult = userService.login(ivanUser.getEmail(), ivanUser.getPassword());
        Assertions.assertTrue(actualResult.isPresent());
    }
    @Test
    void shouldLoginFailed() {
        final User ivanUser = getUser(1, "unexist", LocalDate.of(1990, 01, 10), "unexist@gmail.com",
                "111", Role.ADMIN, Gender.MALE);
        var actualResult = userService.login(ivanUser.getEmail(), ivanUser.getPassword());
        Assertions.assertFalse(actualResult.isPresent());
    }

    @Test
    void shouldCreateUserDto() {
        final CreateUserDto inna = CreateUserDto.builder()
                .name("testName")
                .birthday("1980-08-06")
                .email("test@gmail.com")
                .password("dummy")
                .role("USER")
                .gender("FEMALE")
                .build();

        var actual = userService.create(inna);
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(inna.getName(), actual.getName());
        Assertions.assertEquals(inna.getBirthday(), String.valueOf(actual.getBirthday()));
        Assertions.assertEquals(inna.getRole(), actual.getRole().name());
        Assertions.assertEquals(inna.getGender(), actual.getGender().name());
        Assertions.assertEquals(inna.getEmail(), actual.getEmail());
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsNull() {
        final CreateUserDto innaUser = CreateUserDto.builder()
                .name("testName")
                .birthday("19800806")
                .email("test@gmail.com")
                .password(null)
                .role("USER")
                .gender("FEMALE")
                .build();

        Assertions.assertThrows(ValidationException.class, () -> userService.create(innaUser));
    }

    private User getUser(int id, String name, LocalDate birthday, String email, String password,
                        Role role, Gender gender) {
        return User.builder()
                .id(id)
                .name(name)
                .birthday(birthday)
                .email(email)
                .password(password)
                .role(role)
                .gender(gender)
                .build();
    }
}
