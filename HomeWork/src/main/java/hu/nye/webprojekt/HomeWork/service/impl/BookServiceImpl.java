package hu.nye.webprojekt.HomeWork.service.impl;

import hu.nye.webprojekt.HomeWork.dto.BookDTO;
import hu.nye.webprojekt.HomeWork.entity.BookEntity;
import hu.nye.webprojekt.HomeWork.exception.BookNotFoundException;
import hu.nye.webprojekt.HomeWork.repository.BookRepository;
import hu.nye.webprojekt.HomeWork.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDTO> findAll() {
        List<BookEntity> books = bookRepository.findAll();

        List<BookDTO> result = new ArrayList<>();

        for(BookEntity bookEntity : books){
            BookDTO bookDTO = modelMapper.map(bookEntity,BookDTO.class);
            result.add(bookDTO);
        }
        return result;
    }

    @Override
    public Optional<BookDTO> findById(Long id) {
        Optional<BookEntity> bookEntityOptional = bookRepository.findById(id);

        Optional<BookDTO> bookDTO = bookEntityOptional.map(bookEntity -> modelMapper.map(bookEntity, BookDTO.class));

        return bookDTO;
    }


    @Override
    public BookDTO save(BookDTO bookDTO) {
        /*
        BookEntity bookEntity = modelMapper.map(BookDTO, BookEntity.class);
        bookEntity.setId(null);

        BookEntity savedBook = bookRepository.save(bookEntity);
        return modelMapper.map(savedBook, BookDTO.class);
         */
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<BookEntity> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()){
            bookRepository.delete(optionalBook.get());
        } else {
            throw new BookNotFoundException("Book not found with id"+ id);
        }
    }

    @Override
    public BookDTO update(BookDTO bookDTO) {
        Long id = bookDTO.getId();

        boolean existsById = bookRepository.existsById(id);

        if(existsById){
            BookEntity bookToSave = modelMapper.map(bookDTO, BookEntity.class);
            BookEntity savedBook = bookRepository.save(bookToSave);
            return modelMapper.map(savedBook,BookDTO.class);
        } else {
            throw new BookNotFoundException("Book not found with id " + id);
        }
    }


}
