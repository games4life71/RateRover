package Navigation;

import MIsc.Review;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class altexNav extends AbstractNavigator {


    public altexNav(String siteUrl, List<String> searchKeywords) {
        super(siteUrl, searchKeywords);
    }


    //TODO make use of the searchKeywords
    @Override
    public void navigateTo() throws IOException ,IndexOutOfBoundsException{
        // TODO Auto-generated method stub
        WebDriver driver = new ChromeDriver();
        driver.get(this.getSiteUrl() + "/cauta/?q=" + this.searchKeywords.get(0)); //load the page
        // driver.get("https://altex.ro/");
        // driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement element = driver.findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/main/div[2]/div[2]/div[2]/ul[2]/li[1]/div"))
                .findElement(By.xpath("//*[@id=\"__next\"]/div[2]/div[1]/main/div[2]/div[2]/div[2]/ul[2]/li[1]/div/a[1]"));
        StringBuilder url = new StringBuilder(element.getAttribute("href"));
        url.append("/#reviews");
        driver.close();

        driver = new ChromeDriver();
        driver.get(url.toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        driver.close();
    }

    @Override
    public List<Review> extractReviews() throws IOException {
        //TODO implement this method

        return null;
    }
}
