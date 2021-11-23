package com.crema.creamaspring.scraper;

import java.io.OutputStream;

public class testmain {
    public static void main(String[] args) {
        TitleScraper test = new TitleScraper();

        test.retrieveData();
        System.out.println(test.retrieveData());
    }

}
