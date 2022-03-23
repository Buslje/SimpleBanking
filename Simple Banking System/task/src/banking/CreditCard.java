package banking;


import static banking.Main.random;

public class CreditCard {

    private static String cardNum;
    private static String pin;



    public static String createAccountNumber(String cardNum) {
        String bankNumber = "400000";
        int lowerAccountNumber = 100000000;
        int upperAccountNumber = 999999999;
        int accountNumber = random.nextInt(upperAccountNumber - lowerAccountNumber + 1) + lowerAccountNumber;
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

        cardNum = accountDigits + String.valueOf(lastDigit);
        return cardNum;
    }

    public static String createPin(String pin) {
        int lower = 1000;
        int upper = 9999;
        pin = String.valueOf(random.nextInt(upper - lower + 1) + lower);
        return pin;
    }

}


