package org.userService.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.userService.entity.Role;
import org.userService.entity.User;
import org.userService.repository.UserRepository;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        userRepository.save(new User(1L, "pablo", "Anna",
                "pablito@mail.ru", "1987.12.16",
                Collections.singleton(Role.ADMIN), "1234"));

        userRepository.save(new User(2L, "Andrey", "Andreev",
                "andrey@mail.ru", "1990.09.24",
                Collections.singleton(Role.USER), "1234"));
    }
}
