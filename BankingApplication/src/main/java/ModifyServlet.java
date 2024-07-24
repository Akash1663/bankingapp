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

@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountNumber = request.getParameter("accountNumber");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String mobileNumber = request.getParameter("mobileNumber");
        String accountType = request.getParameter("accountType");
        String balanceStr = request.getParameter("balance");
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = sqlconnect.getConnection();

            StringBuilder sql = new StringBuilder("UPDATE info SET ");
            boolean first = true;

            if (fullName != null && !fullName.isEmpty()) {
                sql.append("fullname = ?");
                first = false;
            }
            if (address != null && !address.isEmpty()) {
                if (!first) sql.append(", ");
                sql.append("address = ?");
                first = false;
            }
            if (mobileNumber != null && !mobileNumber.isEmpty()) {
                if (!first) sql.append(", ");
                sql.append("mobilenumber = ?");
                first = false;
            }
            if (accountType != null && !accountType.isEmpty()) {
                if (!first) sql.append(", ");
                sql.append("accounttype = ?");
                first = false;
            }
            if (balanceStr != null && !balanceStr.isEmpty()) {
                if (!first) sql.append(", ");
                sql.append("initialbalance = ?");
                first = false;
            }
            if (dob != null && !dob.isEmpty()) {
                if (!first) sql.append(", ");
                sql.append("dob = ?");
                first = false;
            }
            if (idProof != null && !idProof.isEmpty()) {
                if (!first) sql.append(", ");
                sql.append("idproof = ?");
            }
            sql.append(" WHERE accountnumber = ?");

            stmt = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (fullName != null && !fullName.isEmpty()) stmt.setString(paramIndex++, fullName);
            if (address != null && !address.isEmpty()) stmt.setString(paramIndex++, address);
            if (mobileNumber != null && !mobileNumber.isEmpty()) stmt.setString(paramIndex++, mobileNumber);
            if (accountType != null && !accountType.isEmpty()) stmt.setString(paramIndex++, accountType);
            if (balanceStr != null && !balanceStr.isEmpty()) stmt.setDouble(paramIndex++, Double.parseDouble(balanceStr));
            if (dob != null && !dob.isEmpty()) stmt.setString(paramIndex++, dob);
            if (idProof != null && !idProof.isEmpty()) stmt.setString(paramIndex++, idProof);
            stmt.setString(paramIndex, accountNumber);

            int rowsAffected = stmt.executeUpdate();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            if (rowsAffected > 0) {
                out.println("<html><body><h3>Customer details updated successfully.</h3></body></html>");
            } else {
                out.println("<html><body><h3>Failed to update customer details.</h3></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Database error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
