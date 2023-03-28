package hu.nye.webprojekt.HomeWork.service;

import hu.nye.webprojekt.HomeWork.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> findAll();

    Optional<BookDTO> findById(Long id);

    BookDTO save(BookDTO bookDTO);

    void delete(Long id);

    BookDTO update(BookDTO bookDTO);
}
