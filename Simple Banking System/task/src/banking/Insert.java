package banking;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class Insert {

        private final String fileName;

        protected Insert(String fileName) {
            this.fileName = fileName;
        }

        private Connection connect () {

            String url = "jdbc:sqlite:" + fileName;
            Connection conn = null;

            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return conn;

        }
        public void insert(long number, int pin) {

            String sql = "INSERT INTO card (number,pin) VALUES(?,?)";

            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                // Setting parameters

                pstmt.setLong(1, number);
                pstmt.setInt(2, pin);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }