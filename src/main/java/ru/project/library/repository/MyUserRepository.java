package ru.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.library.entity.MyUser;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Long> {
}
