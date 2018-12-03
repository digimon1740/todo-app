package com.kakao.todoapp.core.repository.user;

import com.kakao.todoapp.core.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);
}
