package dev.jirenus.movies.controllers;

import dev.jirenus.movies.models.Movie;
import dev.jirenus.movies.models.ResponseObject;
import dev.jirenus.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/{imdbId}")
    public ResponseEntity<ResponseObject> getSingleMovie(@PathVariable String imdbId) {
        try {
            Optional<Movie> foundMovie = movieService.getSingleMovie(imdbId);
            return foundMovie.isPresent() ?
                    ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "Query movie successfully", foundMovie)
                    ) :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new ResponseObject("failed", "Cannot find movie with id: " + imdbId, "")
                    );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("failed", "Query movie failed", e.getMessage())
            );
        }
    }
}
