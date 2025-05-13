import java.util.HashMap;
import java.util.Scanner;

public class CurrencyExchange {
    private HashMap<String, Double> exchangeRates;

    public CurrencyExchange() {
        exchangeRates = new HashMap<>();
        exchangeRates.put("USD", 1.0); // Base currency
        exchangeRates.put("EUR", 0.91);
        exchangeRates.put("GBP", 0.79);
        exchangeRates.put("INR", 83.24);
        exchangeRates.put("JPY", 140.67);
        exchangeRates.put("AUD", 1.56);
    }

    public void addCurrency(String currencyCode, double rate) {
        exchangeRates.put(currencyCode.toUpperCase(), rate);
        System.out.println("Currency added: " + currencyCode);
    }

    public void updateRate(String currencyCode, double newRate) {
        if (exchangeRates.containsKey(currencyCode.toUpperCase())) {
            exchangeRates.put(currencyCode.toUpperCase(), newRate);
            System.out.println("Exchange rate updated for " + currencyCode);
        } else {
            System.out.println("Currency not found!");
        }
    }

    public double convert(String fromCurrency, String toCurrency, double amount) {
        fromCurrency = fromCurrency.toUpperCase();
        toCurrency = toCurrency.toUpperCase();

        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            System.out.println("Invalid currency code!");
            return -1;
        }

        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);

        return (amount / fromRate) * toRate;
    }

    public void displayRates() {
        System.out.println("Current Exchange Rates:");
        for (String currency : exchangeRates.keySet()) {
            System.out.println(currency + " : " + exchangeRates.get(currency));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyExchange exchange = new CurrencyExchange();

        while (true) {
            System.out.println("\n1. Display Exchange Rates");
            System.out.println("2. Add Currency");
            System.out.println("3. Update Exchange Rate");
            System.out.println("4. Convert Currency");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    exchange.displayRates();
                    break;
                case 2:
                    System.out.print("Enter currency code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter exchange rate: ");
                    double rate = scanner.nextDouble();
                    exchange.addCurrency(code, rate);
                    break;
                case 3:
                    System.out.print("Enter currency code: ");
                    String updateCode = scanner.nextLine();
                    System.out.print("Enter new exchange rate: ");
                    double newRate = scanner.nextDouble();
                    exchange.updateRate(updateCode, newRate);
                    break;
                case 4:
                    System.out.print("Enter from currency code: ");
                    String from = scanner.nextLine();
                    System.out.print("Enter to currency code: ");
                    String to = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    double convertedAmount = exchange.convert(from, to, amount);
                    if (convertedAmount != -1) {
                        System.out.println("Converted amount: " + convertedAmount);
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
