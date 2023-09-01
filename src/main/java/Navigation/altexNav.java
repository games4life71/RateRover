package Navigation;

import MIsc.Review;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.*;

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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //navigate to the reviews section
        WebElement ratings = driver.findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/div[1]"))
                .findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/div[1]/div[2]"));

        //get the text from the big div
        String text = ratings.getText();

        //put the stars and numbers of reviews in a dictionary
        Map<Integer, Integer> Stars = new HashMap<>();

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

        //wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));


        String textWithNumberOfPages = driver
                .findElement(By.className("Toolbar"))
                .findElement(By.tagName("nav"))
                .getText();

        String[] array = textWithNumberOfPages.split("\n");

        Integer numberOfPages = Integer.valueOf(array[array.length - 1]);


        System.out.println("Number of pages: " + numberOfPages);

        //list of reviews for the given product
        List<Review> reviews = new ArrayList<>();

        for (int i = 0; i < numberOfPages ; i++) {
            //find the li of reviews
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            Integer numberOfList = driver.findElement(By.className("pb-8"))
                    .findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/ul"))
                    .findElements(By.tagName("li"))
                    .size();


            List<WebElement> listOfReviews = driver.findElement(By.className("pb-8"))
                    .findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/ul"))
                    .findElements(By.tagName("li"));

            System.out.println("Number of reviews per page: " + numberOfList);


            for (WebElement item : listOfReviews) {
                String title = item.findElement(By.tagName("h3")).getText();
                System.out.println("The title is: " + title);

                //if the buying is verified by altex
                boolean isVerified = false;
                if (item.getText().contains("Achizitie verificata")) {
                    isVerified = true;
                }

                String ratingText = item.findElement(By.className("break-words")).findElement(By.tagName("p")).getText();
                System.out.println("The text is: " + ratingText);

                //get the number of stars
                //go to where the stars are in the <li> attribute
                List<WebElement> stars = item.findElement(By.className("md:flex")).findElement(By.className("flex")).findElements(By.tagName("svg"));
                Integer numberOfStars = 0;
                //iterate through each svg and see if it is yellow
                for (WebElement star : stars) {
                    if (star.getAttribute("class").contains("text-yellow")) {
                        numberOfStars++;
                    }
                }
                System.out.println("The number of stars is: " + numberOfStars);

                //add this review to the list of reviews
                reviews.add(new Review(numberOfStars, title, ratingText, isVerified));


            }

            //go to the next page
//                WebElement nextPage = driver.findElement(By.className("Toolbar"))
//                    .findElement(By.className("flex"))
//                    .findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/div[4]/div/div/div/button[2]"));
//                nextPage.click();

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", driver.findElement(By.className("Toolbar"))
                    .findElement(By.xpath("//*[@id=\"reviews\"]/div[2]/div/div/div[4]/div/div/div/button[2]")));

            System.out.println("The number of page: " + (i+1));
            System.out.println("\n");


        }

        //close the driver
        //driver.close();

        return null;
    }
}
