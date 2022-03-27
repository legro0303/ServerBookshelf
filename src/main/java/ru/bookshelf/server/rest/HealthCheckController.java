package ru.bookshelf.server.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bookshelf.server.service.dto.BookDTO;

import javax.validation.Valid;

@Slf4j
@Tag(name = "Health check controller")
@RestController
@RequestMapping("health-check")
public class HealthCheckController {
    @PostMapping()
    public void checkHealth() {}
}
