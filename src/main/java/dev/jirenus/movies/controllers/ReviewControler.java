package dev.jirenus.movies.controllers;

import dev.jirenus.movies.models.ResponseObject;
import dev.jirenus.movies.repositories.ReviewRepository;
import dev.jirenus.movies.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewControler {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> createReview(@RequestBody Map<String, String> payload) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Create review successfully",
                            reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")))
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("failed", "Create review failed", e.getMessage())
            );
        }
    }
}
