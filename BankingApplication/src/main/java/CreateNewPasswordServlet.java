import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.sqlconnect;

@WebServlet("/CreateNewPasswordServlet")
public class CreateNewPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String accountnumber = request.getParameter("accountnumber");
        String newPassword = request.getParameter("newPassword");

        try (Connection conn = sqlconnect.getConnection()) {
            // SQL query to update the password in the info table
            String updateSql = "UPDATE info SET password = ? WHERE accountnumber = ?";
            try (PreparedStatement updateStatement = conn.prepareStatement(updateSql)) {
                updateStatement.setString(1, newPassword);
                updateStatement.setString(2, accountnumber);
                
                int rowsAffected = updateStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Delete the entry from the newcustomer table
                    String deleteSql = "DELETE FROM newcustomer WHERE accountnumber = ?";
                    try (PreparedStatement deleteStatement = conn.prepareStatement(deleteSql)) {
                        deleteStatement.setString(1, accountnumber);
                        deleteStatement.executeUpdate();
                    }
                    
                    // Redirect to login.jsp on successful password update
                    response.sendRedirect("customerlogin.jsp?success=Password%20updated%20successfully");
                } else {
                    out.println("<html><head><title>Update Failed</title></head><body>");
                    out.println("<h2>Update Failed</h2>");
                    out.println("<p>Unable to update password. Please try again.</p>");
                    out.println("</body></html>");
                }
            }
        } catch (SQLException e) {
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h2>Database Error</h2>");
            out.println("<p>Error: " + e.getMessage() + "</p>");
            out.println("</body></html>");
        }
    }
}
