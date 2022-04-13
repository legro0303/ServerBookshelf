package ru.bookshelf.server.service.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class DeleteDTO {
    @Positive(message = "ID should be more or equal than 1")
    long id;
    @NotEmpty(message =  "Owner should not be empty")
    String owner;
}
