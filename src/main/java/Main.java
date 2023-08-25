import Navigation.altexNav;
import Navigation.emagNav;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public  static void main(String[] args) throws IOException, InterruptedException {


        ArrayList<String> keywords = new ArrayList<String>(List.of("telefoane ieftine"));

          emagNav emag = new emagNav("https://www.emag.ro",keywords);
          emag.navigateTo();

          altexNav altex = new altexNav("https://altex.ro",keywords);
          altex.navigateTo();
    }




}
