package ru.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.library.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
