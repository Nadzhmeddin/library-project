package ru.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.project.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
