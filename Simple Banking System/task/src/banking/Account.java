
package banking;

import java.util.*;

public class Account {


    public Account() {
    }

    public Account(String args) {

    }

    private String cardNumber;
    private String pin;
    private int balance;
    private int id;


    public String generateCardNumber() {

        Random random = new Random();

        String bankNumber = "400000";
        int min = 100000000;
        int max = 999999999;

        int accountNumber = random.nextInt(max - min + 1) + min;
        String accountDigits = bankNumber + accountNumber;

        int sum = 0;
        boolean alternate = false;

        for (int i = accountDigits.length() - 1; i >= 0; --i) {
            int digit = Character.getNumericValue(accountDigits.charAt(i));
            digit = (alternate = !alternate) ? (digit * 2) : digit;
            digit = (digit > 9) ? (digit - 9) : digit;
            sum += digit;
        }

        int lastDigit = (sum * 9) % 10;

        cardNumber = accountDigits + String.valueOf(lastDigit);
        return cardNumber;
    }


    public String generatePin() {

        int max = 9999;
        int min = 1000;

        int generatedPin = (int)Math.floor(Math.random() * (max - min + 1) + min);

        pin = Integer.toString(generatedPin);
        return pin;
    }


    public boolean checkLuhnAlgorithm(String cardNumberCheck) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumberCheck.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(cardNumberCheck.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


