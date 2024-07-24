<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('images/istockphoto-1343346538-640x640.jpg') no-repeat center center fixed;
            background-size: cover;
            text-align: center;
            padding-top: 50px;
            color: #fff; /* Optional: change text color for better readability */
        }
        .button-container {
            margin: 20px;
        }
        .login-button {
            display: inline-block;
            padding: 15px 25px;
            font-size: 20px;
            text-align: center;
            text-decoration: none;
            color: #fff;
            background-color: green;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            margin: 10px;
        }
        .login-button:hover {
            background-color: lightgreen;
        }
    </style>
</head>
<body>
    <h1>Welcome to the Bank Application</h1>
    <div class="button-container">
        <a href="login.jsp" class="login-button">Admin Login</a>
    </div>
    <div class="button-container">
        <a href="customerlogin.jsp" class="login-button">Customer Login</a>
    </div>
</body>
</html>
