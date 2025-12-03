package ru.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.library.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
