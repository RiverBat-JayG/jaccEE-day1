package edu.acc.java3.login;

public class CandyVan {
    private static final String[] TREASURES = {"Gold Bullion", "Lollypop",
        "\"Remember to drink your Ovaltine\" message", "Faberge Egg",
        "Junior Mints", "Two Gold Doubloons", "Rainbow Unicorn Horn",
        "Grandpa's Dentures"};
    
    public static String getTreasure() {
        return TREASURES[(int)(Math.random() * TREASURES.length)];
    }
}
