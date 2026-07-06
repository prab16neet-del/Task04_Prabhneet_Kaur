import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;

public class DecodeLabs_P4_Java {

    static Scanner sc = new Scanner(System.in);

    // Exchange Rates (Base Currency = USD)
    static final BigDecimal USD = new BigDecimal("1.00");
    static final BigDecimal INR = new BigDecimal("83.50");
    static final BigDecimal EUR = new BigDecimal("0.92");
    static final BigDecimal GBP = new BigDecimal("0.79");
    static final BigDecimal JPY = new BigDecimal("157.20");

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("     CURRENCY CONVERTER SYSTEM");
        System.out.println("========================================");

        boolean repeat = true;

        do {

            displayCurrencies();

            int from = getCurrencyChoice("Enter Source Currency : ");
            int to = getCurrencyChoice("Enter Target Currency : ");

            BigDecimal amount = getAmount();

            BigDecimal result = convertCurrency(from, to, amount);

            if (result != null) {
                System.out.println("----------------------------------------");
                System.out.printf("Converted Amount : %.2f %s%n",
                        result.doubleValue(),
                        getCurrencyName(to));
                System.out.println("----------------------------------------");
            }

            System.out.println();
            System.out.print("Do you want another conversion? (Y/N): ");

            char choice = sc.next().toUpperCase().charAt(0);

            if (choice != 'Y') {
                repeat = false;
            }

            System.out.println();

        } while (repeat);

        System.out.println("Thank you for using Currency Converter!");
        sc.close();
    }

    // Display Currency Menu
    static void displayCurrencies() {

        System.out.println("\nAvailable Currencies");
        System.out.println("1. USD");
        System.out.println("2. INR");
        System.out.println("3. EUR");
        System.out.println("4. GBP");
        System.out.println("5. JPY");
        System.out.println();
    }

    // Take Currency Choice
    static int getCurrencyChoice(String message) {

        while (true) {

            try {

                System.out.print(message);
                int choice = sc.nextInt();

                if (choice >= 1 && choice <= 5) {
                    return choice;
                }

                System.out.println("Invalid Choice! Select between 1 and 5.");

            } catch (InputMismatchException e) {

                System.out.println("Only numeric input allowed.");
                sc.nextLine();

            }

        }

    }

    // Take Amount
    static BigDecimal getAmount() {

        while (true) {

            try {

                System.out.print("Enter Amount : ");
                BigDecimal amount = sc.nextBigDecimal();

                if (amount.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Amount cannot be negative.");
                } else {
                    return amount;
                }

            } catch (InputMismatchException e) {

                System.out.println("Invalid Amount.");
                sc.nextLine();

            }

        }

    }
        // Currency Conversion
    static BigDecimal convertCurrency(int from, int to, BigDecimal amount) {

        BigDecimal fromRate = getRate(from);
        BigDecimal toRate = getRate(to);

        // Convert source currency to USD
        BigDecimal usdAmount = amount.divide(fromRate, 10, RoundingMode.HALF_UP);

        // Convert USD to target currency
        BigDecimal convertedAmount = usdAmount.multiply(toRate);

        return convertedAmount.setScale(2, RoundingMode.HALF_UP);
    }

    // Get Exchange Rate
    static BigDecimal getRate(int currency) {

        switch (currency) {

            case 1:
                return USD;

            case 2:
                return INR;

            case 3:
                return EUR;

            case 4:
                return GBP;

            case 5:
                return JPY;

            default:
                return BigDecimal.ZERO;
        }
    }

    // Get Currency Name
    static String getCurrencyName(int currency) {

        switch (currency) {

            case 1:
                return "USD";

            case 2:
                return "INR";

            case 3:
                return "EUR";

            case 4:
                return "GBP";

            case 5:
                return "JPY";

            default:
                return "";
        }
    }
    }