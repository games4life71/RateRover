package Navigation;

import MIsc.Review;

import java.io.IOException;
import java.util.List;

/**
 * Interface that defines the methods that a navigator must implement.
 *
 */
public interface INavigator {

   /**
    * Method that navigates to the page.
    * @throws IOException
    */
   public  void navigateTo() throws IOException;

   /**
    * Method that extracts the reviews from the page.
    * @throws IOException
    */
   public List<Review> extractReviews() throws IOException;

}
