package com.mycompany.moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author Ayoze
 */
public class MoneyCalculator {

    public static void main(String[] args) throws Exception{
        
        System.out.println("Introduzca una cantidad en dólares: ");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        double exchangeRate = getExchangeRate("USD", "EUR");
        System.out.println(amount + "USD equivalen a " + amount*exchangeRate + " EUR");
        
    }
    
    private static double getExchangeRate(String from, String to) throws IOException {
        URL url = new URL ("https://free.currconv.com/api/v7/convert?q=" + from + "_" + to + "&compact=ultra&apiKey=68af9a7d365674ae2230");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(to)+12, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }
}
