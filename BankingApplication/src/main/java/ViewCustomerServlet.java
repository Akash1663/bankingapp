import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.sqlconnect;

@WebServlet("/viewcustomerservlet")
public class ViewCustomerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // SQL query to fetch customer details
    private static final String FETCH_CUSTOMER_SQL = "SELECT * FROM info WHERE accountnumber = ?";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountnumber = request.getParameter("accountnumber");

        try (Connection connection = sqlconnect.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FETCH_CUSTOMER_SQL)) {

            preparedStatement.setString(1, accountnumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Fetch customer details
                String fullname = resultSet.getString("fullname");
                String address = resultSet.getString("address");
                String mobilenumber = resultSet.getString("mobilenumber");
                String accounttype = resultSet.getString("accounttype");
                String initialbalance = resultSet.getString("initialbalance");
                String dob = resultSet.getString("dob");
                String idProof = resultSet.getString("idproof");

                // Set attributes to forward to JSP
                request.setAttribute("fullname", fullname);
                request.setAttribute("address", address);
                request.setAttribute("mobilenumber", mobilenumber);
                request.setAttribute("accounttype", accounttype);
                request.setAttribute("initialbalance", initialbalance);
                request.setAttribute("dob", dob);
                request.setAttribute("idProof", idProof);

                // Forward to JSP
                request.getRequestDispatcher("customerdetails.jsp").forward(request, response);
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body>No customer found with this accountnumber: " + accountnumber + "</p></body></html>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><p>Error connecting to the database: " + e.getMessage() + "</p></body></html>");
        }
    }
}
