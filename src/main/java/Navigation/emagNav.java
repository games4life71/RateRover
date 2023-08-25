package Navigation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class emagNav extends AbstractNavigator {
    public emagNav(String siteUrl, List<String> searchKeyword) {
        super(siteUrl, searchKeyword);

    }

    //TODO make use of the searchKeywords
    @Override
    public void navigateTo() throws  IndexOutOfBoundsException {

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

        WebElement Ratings = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/section[14]/div/div[1]")).findElement(By.className("reviews-summary-container"));

        System.out.println(Ratings.getText());
        driver.close();

    }
    @Override
    public void extractReviews() throws IndexOutOfBoundsException {

        //TODO implement this method
    }
}
