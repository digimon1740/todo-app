package com.kakao.todoapp.web.user;

import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.core.dto.user.UserDto;
import com.kakao.todoapp.core.service.user.UserService;
import com.kakao.todoapp.utils.mapper.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping(path = "/users")
public class UserController  {

	private UserService userService;

	private ModelMapperUtils modelMapperUtils;

	@Autowired
	public UserController(UserService userService, ModelMapperUtils modelMapperUtils) {
		this.userService = userService;
		this.modelMapperUtils = modelMapperUtils;
	}

	@GetMapping(path = "/me")
	public ResponseEntity<UserDto> me(Principal principal) {
		UserDto userDto = modelMapperUtils.convertToDto(userService.findByPrincipal(principal), UserDto.class);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PostMapping(value = "/register")
	ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
		userService.create(modelMapperUtils.convertToEntity(userDto, User.class));
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

}
