<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>YOU HAVE BEEN REGISTERED SUCESSFULLY</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('images/istockphoto-1343346538-640x640.jpg') no-repeat center center fixed;
            background-size: cover;
            background-color: #f0f0f0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        .details-container {
            width: 400px;
            background-color:lightgreen;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .details-container h2 {
            margin-top: 0;
            font-weight: bold;
            color: #333;
            text-align: center;
        }

        .details-container p {
            margin-bottom: 10px;
        }

        .details-container form {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .details-container button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        .details-container button:hover {
            background-color: red;
        }
    </style>
</head>
<body>
    <div class="details-container">
        <h2>Registration Details</h2>
        <p><strong>Full Name:</strong> <%= request.getAttribute("fullname") %></p>
        <p><strong>Address:</strong> <%= request.getAttribute("address") %></p>
        <p><strong>Mobile Number:</strong> <%= request.getAttribute("mobilenumber") %></p>
        <p><strong>Type of Account:</strong> <%= request.getAttribute("accounttype") %></p>
        <p><strong>Initial Balance:</strong> <%= request.getAttribute("initialbalance") %></p>
        <p><strong>Date of Birth:</strong> <%= request.getAttribute("dob") %></p>
        <p><strong>ID Proof:</strong> <%= request.getAttribute("idProof") %></p>
		<p><strong>Account Number:</strong> <%= request.getAttribute("accountnumber") %></p>
		<p><strong>Password:</strong> <%= request.getAttribute("password") %></p>
        <form action="admin_index.jsp">
            <button type="submit">Back to Login</button>
        </form>
    </div>
</body>
</html>

