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
        /**
         * V1
        System.out.println("Introduzca una cantidad en dólares: ");
        Scanner scanner = new Scanner(System.in);
        double amount = Double.parseDouble(scanner.next());
        double exchangeRate = getExchangeRate("USD", "EUR");
        System.out.println(amount + "USD equivalen a " + amount*exchangeRate + " EUR");
        * */
        
        MoneyCalculator moneyCalculator = new MoneyCalculator();
        moneyCalculator.control();
        
    }
    
    private double amount;
    private double exchangeRate;
    String currencyFrom;
    String currencyTo;
    
    private void control() throws IOException {
        input();
        process();
        output();
    }
    
    private void input() {
        System.out.println("Introduzca una cantidad de dinero: ");
        Scanner scanner = new Scanner(System.in);
        
        amount = Double.parseDouble(scanner.next());
        
        System.out.println("Introduce una divisa origen: ");
        currencyFrom = scanner.next();
        
        System.out.println("Introduce una divisa destino: ");
        currencyTo = scanner.next();
    }
    
    private void process() throws IOException {
        exchangeRate = getExchangeRate(currencyFrom, currencyTo);
    }
    
    private void output() {
        System.out.println(amount + " " + currencyFrom + " equivalen a " + amount * exchangeRate + " " + currencyTo);
    }
    
    private static double getExchangeRate(String from, String to) throws IOException {
        URL url = new URL ("https://free.currconv.com/api/v7/convert?q=" + from + "_" + to + "&compact=ultra&apiKey=68af9a7d365674ae2230");
        URLConnection connection = url.openConnection();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line = reader.readLine();
            String line1 = line.substring(line.indexOf(":")+1, line.indexOf("}"));
            return Double.parseDouble(line1);
        }
    }
}
