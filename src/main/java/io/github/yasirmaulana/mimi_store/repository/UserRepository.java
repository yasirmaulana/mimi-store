package io.github.yasirmaulana.mimi_store.repository;

import io.github.yasirmaulana.mimi_store.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
