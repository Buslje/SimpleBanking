package banking;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static banking.CreditCard.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static List<String> accountInfo= new ArrayList<String>();

    static boolean isTrue = false;

    public static void main(String[] args) {

        boolean isTrue = false;
        int choice = 0;
        do {
            if (isTrue == false) {
                menu1();
            } else {
                break;
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    createAnAccount();
                    break;
                case 2:
                    logIntoAccount();
            }
        } while (choice != 0);
    }

    public static void createAnAccount() {
        String cardNum ="";
        String pin ="";
        System.out.println("Your card number has been created");
        System.out.println("Your card number:");
        cardNum = createAccountNumber(cardNum);
        System.out.println(cardNum);
        System.out.println("Your card PIN:");
        pin = createPin(pin);
        System.out.println(pin);
        setCardsAndPins(cardNum, pin);
    }
    public static void logIntoAccount() {

        boolean menu = false;
        System.out.println("Enter your card number:");
        String cn = scanner.next();
        System.out.println("Enter your PIN:");
        String p = scanner.next();

        for (String num: accountInfo){
            if (num.equals(cn) && accountInfo.get(accountInfo.indexOf(num)+1).equals(p)) {
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
                isTrue = true;
                break;
            }
        }while(option!=0);
    }

    public static void setCardsAndPins(String cardNum, String pin) {
        accountInfo.add(cardNum);
        accountInfo.add(pin);
    }
}

