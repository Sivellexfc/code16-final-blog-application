package org.code16.services.entity;

import lombok.RequiredArgsConstructor;
import org.code16.entity.User;
import org.code16.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByUserId(Long id){
        return userRepository.findById(id);
    }
}
