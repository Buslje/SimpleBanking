
package banking;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    static Scanner scanner = new Scanner(System.in);
    static Map<String, Account> savedAccounts = new HashMap<>();

    static AccountDaoSqlite dao;
    static Account account = new Account();

    public static String pinNumberCheck;
    public static String cardNumberCheck;
    public static int money;


    public static void main(String[] args) {

        account = new Account(args[1]);
        dao = new AccountDaoSqlite(args[1]);

        firstMenu();
    }


    public static void firstMenu() {

        String selekcija = "";

        while (!selekcija.equals("0")) {
            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            selekcija = scanner.next();

            switch (selekcija) {

                case "1":
                    createAccount();
                    break;

                case "2":
                    System.out.println("Enter your card number:");
                    cardNumberCheck = scanner.next();

                    System.out.println("Enter your PIN:");
                    pinNumberCheck = scanner.next();

                    if (!dao.checkLogInAccount(cardNumberCheck, pinNumberCheck)) {
                        System.out.println("Wrong card number or PIN number!");
                    } else {
                        System.out.println("You have successfully logged in!");
                        secondMenu(cardNumberCheck);
                    }
                    break;

                case "0":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
            }
        }
    }


    public static void secondMenu(String cardNumberCheck) {

        String odabir = "";

        while (!odabir.equals("0")) {

            System.out.println("1. Balance");
            System.out.println("2. Add income");
            System.out.println("3. Do transfer");
            System.out.println("4. Close account");
            System.out.println("5. Log out");
            System.out.println("0. Exit");

            odabir = scanner.next();

            switch (odabir) {

                case "1":
                    System.out.println("Balance: " + dao.getBalance(cardNumberCheck));
                    break;

                case "2":
                    System.out.println("Enter income:");
                    money = scanner.nextInt();

                    dao.updateBalanceOnAccount(money, cardNumberCheck);
                    System.out.println("Income was added!");
                    break;

                case "3":
                    System.out.println("Enter card number:");
                    String cardNumberToTransfer = scanner.next();

                    if (!account.checkLuhnAlgorithm(cardNumberToTransfer)) {
                        System.out.println("Probably you made a mistake in the card number. Please try again!");

                    } else if (!dao.checkCardExist(cardNumberToTransfer)) {
                        System.out.println("Such card does not exist.");

                    } else if (cardNumberCheck.equals(cardNumberToTransfer)) {
                        System.out.println("You can't transfer money to the same account!");

                    } else if (dao.checkCardExist(cardNumberToTransfer)) {

                        System.out.println("Enter how much money you want to transfer: ");
                        money = scanner.nextInt();
                        if (dao.getBalance(cardNumberCheck) >= money) {
                            dao.updateBalanceOnAccount(-money, cardNumberCheck);
                            dao.updateBalanceOnAccount(money, cardNumberToTransfer);
                            System.out.println("Success!");

                        } else {
                            System.out.println("Not enough money!");
                        }
                    } else {
                        System.out.println("Wrong input");
                    }
                    break;

                case "4":
                    dao.deleteAccount(cardNumberCheck);
                    System.out.println("The account has been closed!");
                    odabir = "0";
                    break;

                case "5":
                    System.out.println("You have successfully logged out!");
                    odabir = "0";
                    break;

                case "0":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
            }
        }
    }


    public static void createAccount() {

        Account account = new Account();
        String cardNumber = "";
        String cardPin = "";

        System.out.println("Your card number has been created");

        cardNumber = account.generateCardNumber();
        System.out.println("Your card number:" + "\n" + cardNumber);

        cardPin = account.generatePin();
        System.out.println("Your card PIN:" + "\n" + cardPin);

        savedAccounts.put(cardNumber, account);
        dao.saveAccountToDatabase(account);
    }
}

