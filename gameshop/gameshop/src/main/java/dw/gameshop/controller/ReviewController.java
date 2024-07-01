package dw.gameshop.controller;

import dw.gameshop.dto.ReviewDto;
import dw.gameshop.model.Review;
import dw.gameshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }




    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview() {
        return new ResponseEntity<>(reviewService.getReviewAll(), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> saveReview(@RequestBody Review review) {
        return new ResponseEntity<>(reviewService.saveReview(review),HttpStatus.OK);
    }

    @GetMapping("/review/dto")
    public ResponseEntity<List<ReviewDto>> getReviewAllByDte() {
        return new ResponseEntity<>(reviewService.getReviewAllByDto(), HttpStatus.OK);
    }
}
