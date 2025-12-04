package com.maybank.external_book.service;


import com.maybank.external_book.dto.BookResponse;
import com.maybank.external_book.mapper.BookMapper;
import com.maybank.external_book.model.Book;
import com.maybank.external_book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public BookResponse getByIsbn(String isbn) {
        Book book= bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found with ISBN: " + isbn));
        return BookMapper.mapToResponse(book);
    }

    @Transactional(readOnly = true)
    public Page<BookResponse> getByTitle(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Book> books = bookRepository.findByTitleContainingIgnoreCase(title,pageable);
        List<BookResponse> bookList= books.stream().map(BookMapper::mapToResponse).toList();
        return new PageImpl<>(bookList,pageable, books.getTotalElements());
    }
}
