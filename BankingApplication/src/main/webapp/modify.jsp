<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Customer Details - Banking Application</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" ></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/index-styles.css" rel="stylesheet" />
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

        .edit-container {
            background: linear-gradient(135deg, lightgreen, lightgreen);
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 600px;
            width: 100%;
        }

        .edit-container h2 {
            margin-bottom: 20px;
            color: #333;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 1px;
        }

        .edit-container table {
            width: 100%;
            border-collapse: collapse;
        }

        .edit-container table, .edit-container th, .edit-container td {
            border: 1px solid #ddd;
            padding: 8px;
        }

        .edit-container th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        .edit-container td {
            text-align: left;
        }

        .edit-container input[type="text"] {
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
            margin-bottom: 20px;
        }

        .edit-container button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .edit-container button:hover {
            background-color: #45a049;
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

        .back-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="edit-container">
        <h2>Edit Customer Details</h2>
        <form action="ModifyServlet" method="post">
            <table>
                <tr><td>Account Number:</td><td><input type="text" name="accountNumber" required></td></tr>
                <tr><td>Full Name:</td><td><input type="text" name="fullName"></td></tr>
                <tr><td>Address:</td><td><input type="text" name="address"></td></tr>
                <tr><td>Mobile Number:</td><td><input type="text" name="mobileNumber"></td></tr>
                <tr><td>Account Type:</td><td><input type="text" name="accountType"></td></tr>
                <tr><td>Balance:</td><td><input type="text" name="balance"></td></tr>
                <tr><td>DOB:</td><td><input type="date" name="dob"></td></tr>
                <tr><td>ID Proof:</td><td><input type="text" name="idProof"></td></tr>
            </table>
            <button type="submit">Update Details</button>
             <a href="admin_index.jsp" class="back-button">Back</a>
        </form>
    </div>
</body>
</html>
