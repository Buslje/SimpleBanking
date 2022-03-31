
package banking;

import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccountDaoSqlite {


    private Connection connection;


    private static String fileName;
    Map<Integer, Account> savedAccounts = new HashMap<>();


    static Connection connectToDatabase(String fileName) {
        String url = ("jdbc:sqlite:" + fileName);
        Connection connection = null;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


    AccountDaoSqlite(String fileName) {
        this.fileName = fileName;

        String createTableSql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "    id INTEGER PRIMARY KEY,\n"
                + "    number TEXT NOT NULL,\n"
                + "    pin TEXT NOT NULL,\n"
                + "    balance INTEGER DEFAULT 0 \n"
                + ");";

        try {

            connection = connectToDatabase(fileName);

            Statement account = connection.createStatement();
            account.execute(createTableSql);

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }


    public void saveAccountToDatabase(Account account) {

        connection = connectToDatabase(fileName);

        String sql = "INSERT INTO card (number, pin, balance) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getCardNumber());
            statement.setString(2, account.getPin());
            statement.setInt(3, account.getBalance());

            statement.execute();

            account.setId(statement.getGeneratedKeys().getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Map<Integer, Account> cardInformation() {

        connection = connectToDatabase(fileName);

        String sql = "SELECT * FROM card";

        try {

            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Account account = new Account();

                account.setId(result.getInt("id"));
                account.setCardNumber(result.getString("number"));
                account.setPin(result.getString("pin"));
                account.setBalance(result.getInt("balance"));

                savedAccounts.put(account.getId(), account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedAccounts;
    }


    public boolean checkCardExist(String cardNumberCheck) {

        connection = connectToDatabase(fileName);

        String sql = "SELECT number, pin FROM card WHERE number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cardNumberCheck);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                if (!resultSet.isClosed()) {
                    return true;
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }


    public boolean checkLogInAccount(String cardNumberCheck, String pinNumberCheck) {

        connection = connectToDatabase(fileName);

        String sql = "SELECT number, pin FROM card WHERE number = ? AND pin = ?;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cardNumberCheck);
            preparedStatement.setString(2, pinNumberCheck);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                if (!resultSet.isClosed()) {
                    return true;
                }
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }


    public int getBalance(String cardNumberCheck) {

        connection = connectToDatabase(fileName);

        String sql = "SELECT balance FROM card WHERE number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cardNumberCheck);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                if (!resultSet.isClosed()) {
                    int balance = resultSet.getInt("balance");
                    return balance;
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public void updateBalanceOnAccount(int money, String cardNumberCheck) {

        connection = connectToDatabase(fileName);

        String sql = "UPDATE card SET balance = balance + ? WHERE number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, cardNumberCheck);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteAccount(String cardNumberCheck) {

        connection = connectToDatabase(fileName);

        String sql = "DELETE FROM card WHERE number = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, cardNumberCheck);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

