package com.kakao.todoapp.core.service.user;

import com.kakao.todoapp.core.domain.user.User;
import com.kakao.todoapp.core.repository.user.UserRepository;
import com.kakao.todoapp.core.service.message.MessageSourceService;
import com.kakao.todoapp.utils.crypto.PasswordEncodeUtils;
import com.kakao.todoapp.web.error.exception.ResourceExistsException;
import com.kakao.todoapp.web.error.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private MessageSourceService messageSourceService;

    @Autowired
    public UserService(UserRepository userRepository,
                       MessageSourceService messageSourceService) {
        this.userRepository = userRepository;
        this.messageSourceService = messageSourceService;
    }

    @Transactional(readOnly = true)
    public User findByPrincipal(Principal principal) {
        return Optional.ofNullable(principal)
                .map(p -> findByName(p.getName())).orElse(null);
    }

    @Transactional(readOnly = true)
    public User findByName(String name) {
        return userRepository.findByName(name).orElse(null);
    }

    @Transactional(readOnly = true)
    public User findByNameOrElseThrow(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException(messageSourceService.getMessage("user.not.exist")));
    }

    @Transactional
    public User create(User user) {
        Assert.notNull(user, messageSourceService.getMessage("user.name.invalid"));
        Assert.hasText(user.getName(), messageSourceService.getMessage("user.name.invalid"));
        Assert.hasText(user.getPassword(), messageSourceService.getMessage("user.password.invalid"));

        User exists = findByName(user.getName());
        if (exists != null) {
            throw new ResourceExistsException(messageSourceService.getMessage("user.exists"));
        }
        user.setPassword(PasswordEncodeUtils.encodePassword(user.getPassword()));
        user.setRegTime(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void stampLoginTime(String name) {
        Assert.hasText(name, messageSourceService.getMessage("user.name.invalid"));
        User user = findByNameOrElseThrow(name);
        user.setLastLoginTime(LocalDateTime.now());
    }
}