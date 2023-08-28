package Navigation;

import MIsc.Review;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class emagNav extends AbstractNavigator {
    public emagNav(String siteUrl, List<String> searchKeyword) {
        super(siteUrl, searchKeyword);

    }

    //TODO make use of the searchKeywords
    @Override
    public List<Review> extractReviews() throws IndexOutOfBoundsException {

        //TODO implement this method
        // WebElement Ratings = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/section[14]/div/div[1]")).findElement(By.className("reviews-summary-container"));


        List<WebElement> numbers = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/section[14]/div/div[1]"))
                .findElement(By.className("reviews-summary-container")).
                findElements(By.className("reviews-summary-bars"));

        Dictionary<Integer, Integer> Stars = new Hashtable<Integer, Integer>();

        for (WebElement number : numbers) {
            // for each number get the rating and the number of reviews
            Integer rating = number.getText().charAt(0) - '0';
//            System.out.println(rating);

            Integer count = Integer.parseInt(number.getText().substring(number.getText().indexOf("(") + 1, number.getText().indexOf(")")));
//            System.out.println(count);
            //add the rating and the number of reviews to the dictionary
            Stars.put(rating, count);

        }

        //wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));


        List<Review> reviews = new ArrayList<>();
        //get the number of  pages
        Integer numberOfPages = driver.findElement(By.className("pagination")).findElements(By.tagName("li")).size();


        //extract the reviews for each rating
        try {


            //loop through the pages
            for (int i = 0; i < numberOfPages-1; i++) {
                List<WebElement> ratingsText = driver.findElement(By.className("product-conversations-list")).findElements(By.className("product-review-item"));

                for (WebElement ratingText : ratingsText) {
                        //get the title
                        String title = ratingText.findElement(By.className("product-review-title")).getText();

                        System.out.println("The title is" + title);

                        String verified = ratingText
                                .findElement(By.className("col-md-10"))
                                .findElement(By.className("mrg-btm-xs"))
                                .getText();

                        boolean isVerified = false;
                        if (verified.contains("Review cumparator eMAG"))
                            isVerified = true;

                        String text = ratingText
                                .findElement(By.className("review-body-container")).getText();
                        System.out.println("The text is " + text);


                        //get the number of stars
                        String starsClassName = ratingText
                                .findElement(By.className("star-rating-container"))
                                .findElement(By.className("star-rating-read")).getAttribute("class");
                        Integer stars = -1;
                        if (starsClassName.contains("rated-1"))
                            stars = 1;
                        else if (starsClassName.contains("rated-2"))
                            stars = 2;
                        else if (starsClassName.contains("rated-3"))
                            stars = 3;
                        else if (starsClassName.contains("rated-4"))
                            stars = 4;
                        else if (starsClassName.contains("rated-5"))
                            stars = 5;

                        reviews.add(new Review(stars, title, text, isVerified));

                        //click the next page
                        driver.findElement(By.className("pagination")).findElements(By.tagName("li")).iterator().next().click();

                }


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        driver.close();
        return reviews;

    }

    @Override
    public void navigateTo() throws IndexOutOfBoundsException {

        this.driver = new ChromeDriver();
        driver.get(this.getSiteUrl()); //load the page

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(200));
        WebElement textBox = driver.findElement(By.xpath("/html/body/div[4]/nav[1]/div/div/div[2]/div/form/div[1]/div[1]/input[2]"));
        textBox.click();
        textBox.sendKeys(this.searchKeywords.get(0));


        textBox.submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
        WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/section[1]/div/div[3]/div[2]/div[5]/div[1]/div/div/div[3]/a/div[1]/img"));
        element.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));

        WebElement review = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[3]/div/ul/li[3]/a"));
        review.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));


        // driver.close();


    }
}
