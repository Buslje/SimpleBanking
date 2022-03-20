package banking;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static String cardNum ="";
    static String accountNumber ="";
    static int nCard = 0;
    static int pinNumber =0;
    static String pin ="";
    static String bin ="";
    static String accId ="";
    static String checkSum ="";
    static boolean menuShow = false;
    static List<String> accountInfo= new ArrayList<String>();
    public static void main(String[] args) {
        CreditCard card = new CreditCard("400000","100000000 + rnd.nextInt(900000000)","5");
        int choice = 0;
        do {
            if (menuShow == false) {
                menu1();
            } else {
                break;
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CreateAnAccount(card);
                    break;
                case 2:
                    LogIntoAccount(card);
            }
        } while (choice != 0);
    }

        public static void CreateAnAccount(CreditCard card) {

            System.out.println("Your card number has been created");
            System.out.println("Your card number:");
            cardNum = CreateAccountNumber(bin, accId, nCard);
            System.out.println(cardNum);
            System.out.println("Your card PIN:");
            pin = CreatePin(pinNumber, pin);
            System.out.println(pin);
            setCardsAndPins(cardNum,pin);

    }
        public static void LogIntoAccount(CreditCard card) {
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
    public static String CreateAccountNumber(String bin,String accId,int nCard) {
        bin = "400000";
        nCard = 100000000 + random.nextInt(900000000);
        accId = Integer.toString(nCard);
        cardNum = bin + nCard + "5";
        return cardNum;
    }
    public static String CreatePin(int pinNumber, String pin) {
    pinNumber = 1000 + random.nextInt(9000);
    pin = Integer.toString(pinNumber);
    return pin;
    }

    public static void setCardsAndPins(String cardNum, String pin) {
        accountInfo.add(cardNum);
        accountInfo.add(pin);
    }
    public static void menu1() {
        System.out.println("1. Create an account\n"+
                "2. Log into account\n" +
                "0. Exit");
    }
    public static void menu2(){
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
                menuShow = true;
                break;
            }
        }while(option!=0);
    }

}
