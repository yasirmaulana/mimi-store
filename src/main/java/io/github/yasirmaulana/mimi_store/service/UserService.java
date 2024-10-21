package io.github.yasirmaulana.mimi_store.service;

import io.github.yasirmaulana.mimi_store.dto.RegisterRequestDTO;

public interface UserService {

    void register(RegisterRequestDTO request);
}
