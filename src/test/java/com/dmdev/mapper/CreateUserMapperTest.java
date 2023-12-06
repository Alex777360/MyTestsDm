package com.dmdev.mapper;

import com.dmdev.dto.CreateUserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CreateUserMapperTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private CreateUserDto buildUserDto() {
        return CreateUserDto.builder()
                .name("testName")
                .birthday("1980-08-06")
                .email("test@gmail.com")
                .password("dummy")
                .role("USER")
                .gender("FEMALE")
                .build();
    }

    private CreateUserDto buildUserDtoWithNull() {
        return CreateUserDto.builder()
                .name(null)
                .birthday(null)
                .email(null)
                .password(null)
                .role(null)
                .gender(null)
                .build();
    }

    private CreateUserDto buildInvalidUserDtoWithWrongFormat() {
        return CreateUserDto.builder()
                .name("testName")
                .birthday("dummy")
                .email("test@gmail.com")
                .password("dummy")
                .role("USER")
                .gender("FEMALE")
                .build();
    }

    @Test
    void shouldMapCreateUserDtoToUser() {

        final User actualResult = CreateUserMapper.getInstance().map(buildUserDto());

        Assertions.assertEquals(actualResult.getName(), buildUserDto().getName());

        Assertions.assertEquals(actualResult.getBirthday(), LocalDate.parse(buildUserDto().getBirthday(), formatter));

        Assertions.assertEquals(actualResult.getEmail(), buildUserDto().getEmail());
        Assertions.assertEquals(actualResult.getPassword(), buildUserDto().getPassword());

        Assertions.assertEquals(actualResult.getRole(), Role.find(buildUserDto().getRole()).orElse(null));
        Assertions.assertEquals(actualResult.getGender(), Gender.find(buildUserDto().getGender()).orElse(null));
    }

    @Test
    void shouldThrowExceptionIfDateNull() {
        Assertions.assertThrows(NullPointerException.class,
                () -> CreateUserMapper.getInstance().map(buildUserDtoWithNull()));
    }

    @Test
    void shouldThrowExceptionIfDateFormatIsIncorrect () {
        Assertions.assertThrows(DateTimeParseException.class,
                            () -> CreateUserMapper.getInstance().map(buildInvalidUserDtoWithWrongFormat()));
    }
}
