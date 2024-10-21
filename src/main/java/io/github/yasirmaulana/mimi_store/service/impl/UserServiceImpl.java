package io.github.yasirmaulana.mimi_store.service.impl;

import io.github.yasirmaulana.mimi_store.domain.User;
import io.github.yasirmaulana.mimi_store.dto.RegisterRequestDTO;
import io.github.yasirmaulana.mimi_store.repository.UserRepository;
import io.github.yasirmaulana.mimi_store.security.BCrypt;
import io.github.yasirmaulana.mimi_store.service.UserService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private Validator validator;


    @Override
    public void register(RegisterRequestDTO request) {
        Set<ConstraintViolation<RegisterRequestDTO>> constraintViolations = validator.validate(request);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username already registered");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        userRepository.save(user);
    }
}
