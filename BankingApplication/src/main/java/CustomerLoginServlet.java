import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import DAO.sqlconnect;

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountnumber = request.getParameter("accountnumber");
        String password = request.getParameter("password");

        try (Connection connection = sqlconnect.getConnection()) {
            // Check credentials in newcustomer table
            String checkNewCustomerSQL = "SELECT * FROM newcustomer WHERE accountnumber = ? AND password = ?";
            try (PreparedStatement checkNewCustomerStatement = connection.prepareStatement(checkNewCustomerSQL)) {
                checkNewCustomerStatement.setString(1, accountnumber);
                checkNewCustomerStatement.setString(2, password);
                ResultSet newCustomerResultSet = checkNewCustomerStatement.executeQuery();

                if (newCustomerResultSet.next()) {
                    // Password matches in newcustomer table, redirect to createpassword.jsp and delete the row
                    String deleteNewCustomerSQL = "DELETE FROM newcustomer WHERE accountnumber = ?";
                    try (PreparedStatement deleteNewCustomerStatement = connection.prepareStatement(deleteNewCustomerSQL)) {
                        deleteNewCustomerStatement.setString(1, accountnumber);
                        deleteNewCustomerStatement.executeUpdate();
                    }
                    response.sendRedirect("createnewpassword.jsp?accountnumber=" + URLEncoder.encode(accountnumber, "UTF-8"));
                    return; // Exit the servlet to avoid further processing
                }
            }

            // Password does not match in newcustomer table, check info table
            String checkInfoSQL = "SELECT * FROM info WHERE accountnumber = ? AND password = ?";
            try (PreparedStatement checkInfoStatement = connection.prepareStatement(checkInfoSQL)) {
                checkInfoStatement.setString(1, accountnumber);
                checkInfoStatement.setString(2, password);
                ResultSet infoResultSet = checkInfoStatement.executeQuery();

                if (infoResultSet.next()) {
                    // Password matches in info table, set session attributes and redirect to customer_index.jsp
                    HttpSession session = request.getSession();
                    session.setAttribute("accountnumber", accountnumber);
                    session.setAttribute("initialbalance", infoResultSet.getDouble("initialbalance"));
                    session.setAttribute("fullname", infoResultSet.getString("fullname"));
                    response.sendRedirect("customer_index.jsp");
                } else {
                    // Credentials do not match in info table, redirect to login.jsp with error
                    String encodedError = URLEncoder.encode("Invalid credentials", "UTF-8");
                    response.sendRedirect("customerlogin.jsp?error=" + encodedError);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String encodedError = URLEncoder.encode("Database error", "UTF-8");
            response.sendRedirect("customerlogin.jsp?error=" + encodedError);
        }
    }
}
