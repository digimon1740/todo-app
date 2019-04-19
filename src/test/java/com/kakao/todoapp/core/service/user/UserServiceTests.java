package com.kakao.todoapp.core.service.user;

import com.kakao.todoapp.core.domain.user.Role;
import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.core.repository.user.UserRepository;
import com.kakao.todoapp.core.service.message.MessageSourceService;
import com.kakao.todoapp.utils.crypto.PasswordEncodeUtils;
import com.kakao.todoapp.web.error.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@Slf4j
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @MockBean
    private MessageSourceService messageSourceService;

    @MockBean
    private UserRepository userRepository;

    private UserService userService;

    private static final String USER_NAME = "user";

    @Before
    public void setUp() {
        userService = new UserService(userRepository, messageSourceService);
    }

    @Test
    public void findByNameTest() {
        given(this.userRepository.findByName(USER_NAME).orElse(null))
                .willReturn(new User(USER_NAME, PasswordEncodeUtils.encodePassword("password"), Role.ROLE_USER));

        User actual = userService.findByName(USER_NAME);

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(USER_NAME);
        assertThat(actual.getRole()).isEqualTo(Role.ROLE_USER);

        log.info("actual : {}", actual);
    }

    @Test
    public void findByNameOrElseThrowTest() {
        given(this.userRepository.findByName("none"))
                .willReturn(Optional.of(new User("none", PasswordEncodeUtils.encodePassword("password"), Role.ROLE_USER)));

        assertThatThrownBy(() -> userService.findByNameOrElseThrow(USER_NAME))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    public void createTest() {
        User actual = new User(USER_NAME, PasswordEncodeUtils.encodePassword("password"), Role.ROLE_USER);
        given(this.userRepository.save(actual)).willReturn(actual);

        User user = userService.create(new User(USER_NAME, "password", Role.ROLE_USER));

        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(USER_NAME);
        assertThat(user.getRole()).isEqualTo(Role.ROLE_USER);

        log.info("user : {}", user);
    }

}