import MIsc.ProductInfo;
import MIsc.Review;
import Navigation.altexNav;
import Navigation.emagNav;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {


        ArrayList<String> keywords = new ArrayList<String>(List.of("iphone 12"));

//        emagNav emag = new emagNav("https://www.emag.ro", keywords);
//        emag.navigateTo();
//        List<Review> reviews = new ArrayList<Review>();
//        reviews.addAll(emag.extractReviews());
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setReviews(reviews);

          altexNav altex = new altexNav("https://altex.ro",keywords);
          altex.navigateTo();
          altex.extractReviews();



    }


}
