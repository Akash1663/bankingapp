<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    <link rel="stylesheet" href="reg.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('images/istockphoto-1343346538-640x640.jpg') no-repeat center center fixed;
            background-size: cover;
            background-color: #f2f2f2;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
            border-radius: 10px;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin: 10px 0;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        .value {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Registration Successful</h2>
        <div class="form-group">
            <label for="fullname">Full Name:</label>
            <div class="value"><%= request.getAttribute("fullname") %></div>
        </div>
        <div class="form-group">
            <label for="address">Address:</label>
            <div class="value"><%= request.getAttribute("address") %></div>
        </div>
        <div class="form-group">
            <label for="mobilenumber">Mobile Number:</label>
            <div class="value"><%= request.getAttribute("mobilenumber") %></div>
        </div>
        <div class="form-group">
            <label for="accountType">Account Type:</label>
            <div class="value"><%= request.getAttribute("accountType") %></div>
        </div>
        <div class="form-group">
            <label for="initialbalance">Initial Balance:</label>
            <div class="value"><%= request.getAttribute("initialbalance") %></div>
        </div>
        <div class="form-group">
            <label for="dob">Date of Birth:</label>
            <div class="value"><%= request.getAttribute("dob") %></div>
        </div>
        <div class="form-group">
            <label for="idProof">ID Proof:</label>
            <div class="value"><%= request.getAttribute("idProof") %></div>
        </div>
        <div class="form-group">
            <label for="accountNumber">Account Number:</label>
            <div class="value"><%= request.getAttribute("accountNumber") %></div>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <div class="value"><%= request.getAttribute("password") %></div>
        </div>
    </div>
</body>
</html>
