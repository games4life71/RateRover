package MIsc;

import java.util.Dictionary;
import java.util.List;

/**
 * Class that represents the product info. It contains the stars and the reviews.
 */
public class ProductInfo {

    private Dictionary<Integer, Integer> Stars;
    private List<Review> Reviews;

    public Dictionary<Integer, Integer> getStars() {
        return Stars;
    }

    public void setStars(Dictionary<Integer, Integer> stars) {
        Stars = stars;
    }

    public List<Review> getReviews() {
        return Reviews;
    }

    public void setReviews(List<Review> reviews) {
        Reviews = reviews;
    }
}
