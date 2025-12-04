package com.maybank.external_book.mapper;

import com.maybank.external_book.dto.BookRequest;
import com.maybank.external_book.dto.BookResponse;
import com.maybank.external_book.model.Book;

public class BookMapper {

    private BookMapper(){}

    public static Book mapToModel(BookRequest bookRequest){
        return Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .isbn(bookRequest.getIsbn())
                .build();
    }

    public static BookResponse mapToResponse(Book book){
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .build();
    }
}
