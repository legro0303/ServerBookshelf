package ru.bookshelf.server.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Slf4j
@Service
public class CheckErrorsService {
    public String checkErrorsExist(BindingResult bindingResult)
    {
        List<FieldError> errors = bindingResult.getFieldErrors();
        StringBuilder s = new StringBuilder();

        if (bindingResult.hasErrors())
        {
            for (FieldError error : errors)
            {
                log.info("Client post uncorrected data [{}]", error.getDefaultMessage());
                s.append(error.getDefaultMessage() + "\n");
            }
            return s.toString();
        }
        else
        {
            return null;
        }
    }
}
