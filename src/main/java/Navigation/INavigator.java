package Navigation;

import java.io.IOException;

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
   public void extractReviews() throws IOException;

}
