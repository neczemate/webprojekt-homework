package hu.nye.webprojekt.HomeWork.controller;


import hu.nye.webprojekt.HomeWork.dto.BookDTO;
import hu.nye.webprojekt.HomeWork.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/movies")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(path="/", method = RequestMethod.GET)
    public List<BookDTO> findAll(){
        List<BookDTO> books = bookService.findAll();
        return books;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BookDTO> findById(@PathVariable Long id) {
        Optional<BookDTO> bookDTO = bookService.findById(id);

        ResponseEntity<BookDTO> response;

        if(bookDTO.isPresent()) {
            response = ResponseEntity.ok(bookDTO.get());
        }
        else{
            response = ResponseEntity.notFound().build();
        }
        return response;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<BookDTO> save(@RequestBody BookDTO bookDTO) {
        BookDTO savedBook= bookService.save(bookDTO);
        return ResponseEntity.status(201).body(savedBook);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        bookService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO bookDTO){
        BookDTO updateBook = bookService.update(bookDTO);

        return  ResponseEntity.status(200).body(updateBook);
    }



}
