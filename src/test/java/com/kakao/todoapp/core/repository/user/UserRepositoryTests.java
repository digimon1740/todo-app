package com.kakao.todoapp.core.repository.user;

import com.kakao.todoapp.core.domain.user.Role;
import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.utils.crypto.PasswordEncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Before
	public void setUp() {
		User user = new User("tony", PasswordEncodeUtils.encodePassword("password"), Role.ROLE_USER);
		this.entityManager.persist(user);
	}

	@Test
	public void findUserShouldReturnUser() throws Exception {
		User actual = this.userRepository.findByName("tony");
		assertThat(actual).isNotNull();
		assertThat(actual.getName()).isEqualTo("tony");
		log.info("actual user : {}", actual);
	}

	@Test
	public void findUserShouldReturnNull() throws Exception {
		User user = this.userRepository.findByName("tony2");
		assertThat(user).isNull();
	}
}
