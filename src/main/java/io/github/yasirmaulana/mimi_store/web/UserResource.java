package io.github.yasirmaulana.mimi_store.web;

import io.github.yasirmaulana.mimi_store.dto.RegisterRequestDTO;
import io.github.yasirmaulana.mimi_store.dto.WebResponse;
import io.github.yasirmaulana.mimi_store.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

    private UserService userService;

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterRequestDTO dto) {
        userService.register(dto);
        return WebResponse.<String>builder().status("OK").build();
    }
}
