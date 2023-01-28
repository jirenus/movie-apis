package dev.jirenus.movies.controllers;

import dev.jirenus.movies.models.ResponseObject;
import dev.jirenus.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllMovies() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query movies successfully", movieService.getAllMovies())
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("failed", "Query movies failed", e.getMessage())
            );
        }
    }
}
