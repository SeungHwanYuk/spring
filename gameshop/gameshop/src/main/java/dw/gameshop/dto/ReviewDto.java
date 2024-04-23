package dw.gameshop.dto;


import dw.gameshop.model.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDto {

    private long gameId;
    private String gameName;
    private String userId;
    private int reviewPoint;
    private String reviewText;


    public ReviewDto toReviewDtoFromReview(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewPoint(review.getPoint());
        reviewDto.setReviewText(review.getReviewText());
        reviewDto.setGameName(review.getGame().getTitle());
        reviewDto.setUserId(review.getUser().getUserId());
        reviewDto.setGameId(review.getGame().getId());
    return reviewDto;
    }
}
