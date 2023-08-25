package Navigation;

import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class AbstractNavigator implements INavigator {
    /**
     * The site url, e.g. the starting point of the crawler.
     */
    private final String siteUrl;
    protected WebDriver driver;

    public String getSiteUrl() {
        return siteUrl;
    }

    /**
     * The keywords to search for.
     */
    protected List<String> searchKeywords;


    protected AbstractNavigator(String siteUrl, List<String> searchKeywords) {

        this.siteUrl = siteUrl;
        this.searchKeywords = searchKeywords;
    }


}
