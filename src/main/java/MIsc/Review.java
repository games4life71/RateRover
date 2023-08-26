package MIsc;

import java.util.Dictionary;
import java.util.List;

/**
 * Class that represents the information that a review contains.
 */
public class Review {

    private final int Stars; //The number of stars the review has
    private final String Title; //The title of the review

    private final String Text; //The text of the review

    private final Boolean Verified; //if the review is verified ... The user has bought the product

    public Review(int stars, String title, String text, Boolean verified) {
        Stars = stars;
        Title = title;
        Text = text;
        Verified = verified;
    }

    public int getStars() {
        return Stars;
    }

    public String getTitle() {
        return Title;
    }

    public String getText() {
        return Text;
    }

    public Boolean getVerified() {
        return Verified;
    }
}
