import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import DAO.sqlconnect;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String accountnumber = (String) session.getAttribute("accountnumber");
        Double currentBalance = (Double) session.getAttribute("initialbalance");

        if (accountnumber == null) {
            response.sendRedirect("customerlogin.jsp");
            return;
        }

        try (Connection connection = sqlconnect.getConnection()) {
            if ("add".equals(action)) {
                double amount = Double.parseDouble(request.getParameter("amount"));
                String sql = "UPDATE info SET initialbalance = initialbalance + ? WHERE accountnumber = ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setDouble(1, amount);
                    statement.setString(2, accountnumber);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        session.setAttribute("initialbalance", currentBalance + amount);
                        logTransaction(connection, accountnumber, amount, "Deposit");
                    }
                }
                response.sendRedirect("transactiondetails.jsp");

            } else if ("withdraw".equals(action)) {
                double amount = Double.parseDouble(request.getParameter("amount"));
                String sql = "UPDATE info SET initialbalance = initialbalance - ? WHERE accountnumber = ? AND initialbalance >= ?";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setDouble(1, amount);
                    statement.setString(2, accountnumber);
                    statement.setDouble(3, amount);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        session.setAttribute("initialbalance", currentBalance - amount);
                        logTransaction(connection, accountnumber, amount, "Withdrawal");
                    }
                }
                response.sendRedirect("transactiondetails.jsp");

            } else if ("transfer".equals(action)) {
                double amount = Double.parseDouble(request.getParameter("amount"));
                String recipientAccountNumber = request.getParameter("recipient_account_number");

                if (amount <= currentBalance) {
                    try {
                        connection.setAutoCommit(false);

                        // Check if the recipient exists and get their balance
                        String sqlCheckRecipient = "SELECT initialbalance FROM info WHERE accountnumber = ?";
                        try (PreparedStatement statementCheckRecipient = connection.prepareStatement(sqlCheckRecipient)) {
                            statementCheckRecipient.setString(1, recipientAccountNumber);
                            ResultSet resultSet = statementCheckRecipient.executeQuery();

                            if (resultSet.next()) {
                                double recipientBalance = resultSet.getDouble("initialbalance");

                                // Update sender's balance
                                String sqlUpdateSender = "UPDATE info SET initialbalance = initialbalance - ? WHERE accountnumber = ?";
                                try (PreparedStatement statementUpdateSender = connection.prepareStatement(sqlUpdateSender)) {
                                    statementUpdateSender.setDouble(1, amount);
                                    statementUpdateSender.setString(2, accountnumber);
                                    int rowsUpdatedSender = statementUpdateSender.executeUpdate();
                                    if (rowsUpdatedSender <= 0) {
                                        connection.rollback();
                                        throw new SQLException("Failed to update sender's balance");
                                    }
                                }

                                // Update recipient's balance
                                String sqlUpdateRecipient = "UPDATE info SET initialbalance = initialbalance + ? WHERE accountnumber = ?";
                                try (PreparedStatement statementUpdateRecipient = connection.prepareStatement(sqlUpdateRecipient)) {
                                    statementUpdateRecipient.setDouble(1, amount);
                                    statementUpdateRecipient.setString(2, recipientAccountNumber);
                                    int rowsUpdatedRecipient = statementUpdateRecipient.executeUpdate();
                                    if (rowsUpdatedRecipient <= 0) {
                                        connection.rollback();
                                        throw new SQLException("Failed to update recipient's balance");
                                    }
                                }

                                connection.commit();
                                session.setAttribute("initialbalance", currentBalance - amount);
                                logTransaction(connection, accountnumber, amount, "Transfer to " + recipientAccountNumber);
                                logTransaction(connection, recipientAccountNumber, amount, "Transfer from " + accountnumber);
                                response.sendRedirect("transactiondetails.jsp");
                            } else {
                                connection.rollback();
                                session.setAttribute("error", "Recipient account not found");
                                response.sendRedirect("transactiondetails.jsp");
                            }
                        }
                    } catch (SQLException e) {
                        connection.rollback();
                        e.printStackTrace();
                        session.setAttribute("error", "Error processing transfer");
                        response.sendRedirect("transactiondetails.jsp");
                    } finally {
                        connection.setAutoCommit(true);
                    }
                } else {
                    session.setAttribute("error", "Insufficient funds");
                    response.sendRedirect("transactiondetails.jsp");
                }
            } else if ("viewTransactions".equals(action)) {
                // Fetch and display the last transactions
                String transactionSql = "SELECT * FROM transactions WHERE accountnumber = ? ORDER BY date DESC LIMIT 10";
                try (PreparedStatement transactionStatement = connection.prepareStatement(transactionSql)) {
                    transactionStatement.setString(1, accountnumber);
                    ResultSet transactionResultSet = transactionStatement.executeQuery();

                    request.setAttribute("transactions", transactionResultSet);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("accountstatment.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    session.setAttribute("error", "Error fetching transactions");
                    response.sendRedirect("accountstatment.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("error", "Database error");
            response.sendRedirect("transactiondetails.jsp");
        }
    }

    private void logTransaction(Connection connection, String accountnumber, double amount, String transactionType) throws SQLException {
        String sql = "INSERT INTO transactions (accountnumber, amount, transaction_type) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, accountnumber);
            statement.setDouble(2, amount);
            statement.setString(3, transactionType);
            statement.executeUpdate();
        }
    }
}