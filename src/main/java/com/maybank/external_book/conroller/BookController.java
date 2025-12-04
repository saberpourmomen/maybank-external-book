package com.maybank.external_book.conroller;


import com.maybank.external_book.dto.BookResponse;
import com.maybank.external_book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookResponse> getByIsbn(@PathVariable String isbn) {
        try {
            return ResponseEntity.ok(bookService.getByIsbn(isbn));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookResponse>> searchByName(@RequestParam String title,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return  new ResponseEntity<>(bookService.getByTitle(title,page,size), HttpStatus.OK);
    }
}
