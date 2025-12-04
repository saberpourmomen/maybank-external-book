package com.maybank.external_book.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String author;

    @NotBlank(message = "ISBN cannot be empty")
    private String isbn;
}
