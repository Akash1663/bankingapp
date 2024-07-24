<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Customer - Banking Application</title>
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

        .form-container {
            background: linear-gradient(135deg, lightgreen, lightgreen);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .form-container h2 {
            margin-bottom: 20px;
            color: #333;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .form-container label {
            display: block;
            margin: 10px 0 5px;
            font-size: 16px;
            color: #666;
        }

        .form-container input {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        .button {
            width: 100%;
            padding: 15px;
            font-size: 18px;
            color: white;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #0056b3;
        }

        .back-button {
            width: 100%;
            padding: 15px;
            font-size: 18px;
            color: white;
            background-color: #28a745;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>View Customer</h2>
        <form action="viewcustomerservlet" method="post">
            <label for="accountnumber">Account Number</label>
            <input type="text" id="accountnumber" name="accountnumber" required>
            <button type="submit" class="button">View Customer</button>
        </form>
        <button class="back-button" onclick="window.location.href='admin_index.jsp'">Back to Dashboard</button>
    </div>
</body>
</html>
