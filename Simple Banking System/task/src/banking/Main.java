package banking;


import java.sql.*;
import java.util.*;

import static banking.CreditCard.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static Map<String, CreditCard> savedAccounts = new HashMap<>();
    public static AccountDaoSqlite dao = new AccountDaoSqlite("jdbc:sqlite:card.s3db");



    public static void main(String[] args) {

        firstMenu();
    }

    public static void firstMenu() {
        String choice = "";

        while (!choice.equals("0")) {

            System.out.println("1. Create an account");
            System.out.println("2. Log into account");
            System.out.println("0. Exit");

            choice = scanner.next();
            switch (choice) {
                case "1":
                    createAnAccount();
                    break;
                case "2":
                    logIntoAccount();
                    break;
                case "0":
                    System.out.println("Bye!");
                    System.exit(0);
                    break;
            }
        }
    }

    public static void createAnAccount() {
        CreditCard card = new CreditCard();
        String cardNum ="";
        String pin ="";
        System.out.println("Your card number has been created");
        System.out.println("Your card number:");
        cardNum = card.createAccountNumber();
        System.out.println(cardNum);
        System.out.println("Your card PIN:");
        pin = card.createPin();
        System.out.println(pin);
        savedAccounts.put(cardNum, card);
        dao.saveAccountToDatabase(card);
    }

    public static void logIntoAccount() {

        boolean menu = false;
        System.out.println("Enter your card number:");
        String cn = scanner.next();
        System.out.println("Enter your PIN:");
        String p = scanner.next();

        for (String card: savedAccounts.keySet()){
            if (savedAccounts.get(card).getCardNum().equals(cn) && savedAccounts.get(card).getPin().equals(p)) {
                System.out.println("You have successfully logged in!\n");
                menu = true;
            }
        }
        if (menu == true){
            menu2();
        } else {
            System.out.println("Wrong card number or PIN!");
        }

    }

    public static void menu1() {

        System.out.println("1. Create an account\n"+
                "2. Log into account\n" +
                "0. Exit");
    }
    public static void menu2() {
        int option = 3;
        System.out.println("1. Balance\n" +
                "2. Log out\n" +
                "0. Exit");
        do {
            option = scanner .nextInt();
            if (option == 1){
                System.out.println("Balance: 0");
                System.out.println("1. Balance\n" +
                        "2. Log out\n" +
                        "0. Exit");
            }
            else if (option == 2) {
                System.out.println("You have successfully logged out!\n");
                break;
            }
            else if (option == 0){
                System.out.println("Bye!");
                System.exit(0);
                break;
            }
        }while(option!=0);
    }
}

