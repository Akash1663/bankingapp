import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.sqlconnect;

@WebServlet("/CustomerRegistrationServlet")
public class CustomerRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String mobilenumber = request.getParameter("mobilenumber");
        String accountType = request.getParameter("accountType");
        String initialbalance = request.getParameter("initialbalance");
        String dob = request.getParameter("dob");
        String idProof = request.getParameter("idProof");

        if (fullname == null || fullname.trim().isEmpty() || 
            address == null || address.trim().isEmpty() || 
            mobilenumber == null || mobilenumber.trim().isEmpty() || 
            accountType == null || accountType.trim().isEmpty() || 
            initialbalance == null || initialbalance.trim().isEmpty() || 
            dob == null || dob.trim().isEmpty() || 
            idProof == null || idProof.trim().isEmpty()) {

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>All fields are required.</h3></body></html>");
            return;
        }

        double initialBalance;
        try {
            initialBalance = Double.parseDouble(initialbalance);
        } catch (NumberFormatException e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Invalid initial balance.</h3></body></html>");
            return;
        }

        String accountNumber = generateAccountNumber();
        String password = generatePassword();

        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;

        try {
            conn = sqlconnect.getConnection();

            // Insert into info table
            String sql = "INSERT INTO info (fullname, address, mobilenumber, accounttype, initialbalance, dob, idproof, accountnumber, password) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullname);
            stmt.setString(2, address);
            stmt.setString(3, mobilenumber);
            stmt.setString(4, accountType);
            stmt.setDouble(5, initialBalance);
            stmt.setString(6, dob);
            stmt.setString(7, idProof);
            stmt.setString(8, accountNumber);
            stmt.setString(9, password);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                // Insert into newcustomer table
                String sql2 = "INSERT INTO newcustomer (accountnumber, password, fullname) VALUES (?, ?, ?)";
                stmt2 = conn.prepareStatement(sql2);
                stmt2.setString(1, accountNumber);
                stmt2.setString(2, password);
                stmt2.setString(3, fullname);

                int rowsAffected2 = stmt2.executeUpdate();

                if (rowsAffected2 > 0) {
                    request.setAttribute("fullname", fullname);
                    request.setAttribute("address", address);
                    request.setAttribute("mobilenumber", mobilenumber);
                    request.setAttribute("accountType", accountType);
                    request.setAttribute("initialbalance", initialBalance);
                    request.setAttribute("dob", dob);
                    request.setAttribute("idProof", idProof);
                    request.setAttribute("accountNumber", accountNumber);
                    request.setAttribute("password", password);

                    RequestDispatcher dispatcher = request.getRequestDispatcher("customerdetails.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.setContentType("text/html");
                    PrintWriter out = response.getWriter();
                    out.println("<html><body><h3>Registration failed for newcustomer table.</h3></body></html>");
                }
            } else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<html><body><h3>Registration failed.</h3></body></html>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body><h3>Database error: " + e.getMessage() + "</h3></body></html>");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (stmt2 != null) stmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private String generateAccountNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder accountNumber = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
}
