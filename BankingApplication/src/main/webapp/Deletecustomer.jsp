<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Remove Customer - Banking Application</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: url('images/istockphoto-1343346538-640x640.jpg') no-repeat center center fixed;
            background-size: cover;
            background-color: #e0e0e0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .search-container {
            background: linear-gradient(135deg, lightgreen, lightgreen);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .search-container h2 {
            margin-bottom: 20px;
            color: #333;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .search-container input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
            margin-bottom: 20px;
        }

        .search-container button {
            background-color: #f44336;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        .back-button {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: inline-block;
            margin-bottom: 20px;
        }

        .search-container button:hover {
            background-color: #d32f2f;
        }

        .message {
            color: green;
            font-size: 14px;
            margin-top: 10px;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }
        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="search-container">
        <h2>Remove Customer</h2>
        <form action="DeleteCustomerServlet" method="post">
            <input type="text" name="accountnumber" placeholder="Enter Account no" required>
            <button type="submit">Delete Customer</button>
            <br>
            <br>
              <a href="admin_index.jsp" class="back-button">Back</a>
        </form>
        <div class="message">
            <%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
        </div>
        <div class="error-message">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
        </div>
    </div>
</body>
</html>
