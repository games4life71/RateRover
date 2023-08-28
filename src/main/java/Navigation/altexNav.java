package Navigation;

import MIsc.Review;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class altexNav extends AbstractNavigator {


    public altexNav(String siteUrl, List<String> searchKeywords) {
        super(siteUrl, searchKeywords);
    }

    //need the url for the driver
    private StringBuilder url;

    //TODO make use of the searchKeywords
    @Override
    public void navigateTo() throws IOException, IndexOutOfBoundsException {
        // TODO Auto-generated method stub
        WebDriver driver = new ChromeDriver();
        driver.get(this.getSiteUrl() + "/cauta/?q=" + this.searchKeywords.get(0)); //load the page
        // driver.get("https://altex.ro/");
        // driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement element = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/main/div[2]/div[2]/div[2]/ul[2]/li[1]/div"))
                .findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/main/div[2]/div[2]/div[2]/ul[2]/li[1]/div/a[1]"));
        url = new StringBuilder(element.getAttribute("href"));
        url.append("/#reviews");
        driver.close();


        //driver.close();
    }

    @Override
    public List<Review> extractReviews() throws IOException {
        //TODO implement this method
        //write how many ratings each star has
        //the big div where all the ratings are

        driver = new ChromeDriver();
        driver.get(url.toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        //navigate to the reviews section
        WebElement ratings = driver.findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/div[1]"))
                .findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/div[1]/div[2]"));

        //get the text from the big div
        String text = ratings.getText();

        //put the stars and numbers of reviews in a dictionary
        Dictionary<Integer, Integer> Stars = new Hashtable<Integer, Integer>();

        //transform the text into a string array for easy access
        String[] arrOfStr = text.split("\n");
        for (int i = 0; i < arrOfStr.length; i += 2) {
            //get the rating
            Integer rating = Integer.parseInt(arrOfStr[i]);
            //get the number of reviews
            Integer count = Integer.parseInt(arrOfStr[i + 1].substring(0, arrOfStr[i + 1].indexOf(" ")));

            Stars.put(rating, count);
        }

        System.out.println(Stars);
        //close the driver
        driver.close();

        return null;
    }
}
