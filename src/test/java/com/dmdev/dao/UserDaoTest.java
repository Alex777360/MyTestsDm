package com.dmdev.dao;

import com.dmdev.IntegrationTestBase;
import com.dmdev.entity.Gender;
import com.dmdev.entity.Role;
import com.dmdev.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserDaoTest extends IntegrationTestBase {

    private final UserDao userDao = UserDao.getInstance();
    @Mock
    private UserDao userDaoMock;
    private final User user = User.builder()
            .name("testName")
            .birthday(LocalDate.of(1990, 1, 10))
            .email("test@gmail.com")
            .password("dummy")
            .role(Role.ADMIN)
            .gender(Gender.MALE)
            .build();

    @Test
    void shouldSave() {
        userDao.save(user);
        var actualResult = userDao.findById(user.getId());
        Assertions.assertTrue(actualResult.isPresent());
        Assertions.assertEquals(user, actualResult.get());
    }

    @Test
    void shouldFindAll() {
        var actualResult = userDao.findAll();
        Assertions.assertEquals(5, actualResult.size());
    }

    @Test
    void shouldFindByEmailAndPassword() {
        userDao.save(user);
        var actualResult = userDao.findByEmailAndPassword(user.getEmail(), user.getPassword());
        Assertions.assertTrue(actualResult.isPresent());
        Assertions.assertEquals(user, actualResult.get());
    }

    @Test
    void shouldDeleteById() {
        var actualResult = userDao.delete(1);
        Assertions.assertTrue(actualResult);
        Assertions.assertTrue(userDao.findById(1).isEmpty());
    }

    @Test
    void shouldFailDeleteIfIdIsNegative() { //TODO rename method name
        var actualResult = userDao.delete(-1);
        Assertions.assertFalse(actualResult);
    }

    @Test
    void shouldUpdate() {
        final User testUser = User.builder() //rename local variable, it should have informative name
                .id(1)
                .name("testName")
                .birthday(LocalDate.of(1990, 1, 10))
                .email("test@gmail.com")
                .password("dummy")
                .role(Role.ADMIN)
                .gender(Gender.MALE)
                .build();

        userDao.update(testUser);
        Optional<User> updatedResult = userDao.findById(1);
        Assertions.assertTrue(updatedResult.isPresent());
        Assertions.assertEquals(testUser, updatedResult.get());
    }
}
