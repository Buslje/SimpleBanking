package banking;


import java.sql.*;
import java.util.*;

import static banking.CreditCard.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    public static Map<String, CreditCard> savedAccounts = new HashMap<>();
    static String fileName;
    //public static CardDaoSqlite dao = new CardDaoSqlite("jdbc:sqlite:card.s3db");

    static boolean isTrue = false;

    public static void main(String[] args) {

        fileName = "card.s3db";

        createDatabase(fileName);
        createTable(fileName);
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
                    createAccount();
                    break;
                case 2:
                    logIntoAccount();
            }
        } while (choice != 0);
    }

    /*public static void createAnAccount() {
        CreditCard card = new CreditCard();
        String cardNum ="";
        String pin ="";
        System.out.println("Your card number has been created");
        System.out.println("Your card number:");
        cardNum = createAccountNumber(cardNum);
        System.out.println(cardNum);
        System.out.println("Your card PIN:");
        pin = createPin(pin);
        System.out.println(pin);
        savedAccounts.put(cardNum, card);
        //dao.saveCreditCardToDatabase(card);
    }*/
    static void printData(CreditCard card) {

        System.out.println();

        System.out.println("Your card has been created");

        System.out.println("Your card number:");
        System.out.println(card.getCardNum());

        System.out.println("Your card PIN:");
        System.out.println(card.getPin());

    }

    static void createAccount() {

        Insert app = new Insert(fileName);

        CreditCard account = new CreditCard();

        // Inserting data into an SQL database

        app.insert(Long.parseLong(account.getCardNum()), Integer.parseInt(account.getPin()));

        printData(account);

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
                isTrue = true;
                break;
            }
        }while(option!=0);
    }
    public static void createDatabase(String fileName) {

        // Connection string

        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void createTable(String fileName) {

        // Connection string

        String url = "jdbc:sqlite:" + fileName;

        // Creating table

        String sql = "CREATE TABLE IF NOT EXISTS card (" +
                "  `id` INTEGER NOT NULL PRIMARY KEY," +
                "  `number` TEXT," +
                "  `pin` TEXT," +
                "  `balance` INTEGER DEFAULT 0"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Create new table

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

