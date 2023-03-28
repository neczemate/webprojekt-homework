package hu.nye.webprojekt.HomeWork.repository;

import hu.nye.webprojekt.HomeWork.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
