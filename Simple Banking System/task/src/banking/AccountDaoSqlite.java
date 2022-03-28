package banking;

import java.sql.*;

public class AccountDaoSqlite {



    private Connection connection;

    AccountDaoSqlite (String card) {

        String createTableSql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    number TEXT NOT NULL,\n"
                + "    pin TEXT NOT NULL,\n"
                + "    balance INTEGER DEFAULT 0 \n"
                + ");";

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:card.s3db");

            Statement account = connection.createStatement();
            account.execute(createTableSql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }



    public void saveAccountToDatabase(CreditCard card) {
        String sql = "INSERT INTO card (number, pin, balance) VALUES(?, ?, ?)";

        try  {

            PreparedStatement save = connection.prepareStatement(sql);

            save.setString(1, card.getCardNum());
            save.setString(2, card.getPin());
            save.setString(3, String.valueOf(card.getBalance()));

            save.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}