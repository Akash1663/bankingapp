import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.sqlconnect;

@WebServlet("/DeleteCustomerServlet")
public class DeleteCustomerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountnumber = request.getParameter("accountnumber");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = sqlconnect.getConnection();

            // Check if user exists
            String checkUserSql = "SELECT fullName FROM info WHERE accountnumber = ?";
            stmt = conn.prepareStatement(checkUserSql);
            stmt.setString(1, accountnumber);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("fullName");

                // User exists, proceed to delete
                String deleteUserSql = "DELETE FROM info WHERE accountnumber = ?";
                stmt = conn.prepareStatement(deleteUserSql);
                stmt.setString(1, accountnumber);
                stmt.executeUpdate();

                request.setAttribute("message", "Customer " + fullName + " is removed successfully.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Deletecustomer.jsp");
                dispatcher.forward(request, response);
            } else {
                // User does not exist
                request.setAttribute("errorMessage", "User not found");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Deletecustomer.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Database error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
