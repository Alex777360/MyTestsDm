package com.dmdev.mapper;

import com.dmdev.dto.UserDto;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserMapperTest {
    private static final int ID = 2;
    private static final String NAME = "testName";
    private static final LocalDate BIRTHDAY = LocalDate.of(1980, 8, 6);
    private static final String EMAIL = "example@gmail.com";
    private static final Role ROLE = Role.USER;
    private static final Gender GENDER = Gender.FEMALE;

    @Test
    void shouldMapUserToUserDto() {
        final User expectedResult = buildUser();
        final UserDto actualResult = UserMapper.getInstance().map(expectedResult);

        Assertions.assertEquals(actualResult.getId(), expectedResult.getId());
        Assertions.assertEquals(actualResult.getName(), expectedResult.getName());
        Assertions.assertEquals(actualResult.getBirthday(), expectedResult.getBirthday());
        Assertions.assertEquals(actualResult.getEmail(), expectedResult.getEmail());
        Assertions.assertEquals(actualResult.getRole(), expectedResult.getRole());
        Assertions.assertEquals(actualResult.getGender(), expectedResult.getGender());
    }

    private User buildUser() {
        User user = new User();
        user.setId(ID);
        user.setBirthday(BIRTHDAY);
        user.setName(NAME);
        user.setGender(GENDER);
        user.setEmail(EMAIL);
        user.setRole(ROLE);
        return user;
    }

}
