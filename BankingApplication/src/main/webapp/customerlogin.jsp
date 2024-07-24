<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Customer Login</title>

<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css --> 
<link rel="stylesheet" href="css/style.css">
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            background: url('images/images.png') no-repeat center center fixed;
            background-size: cover;
            text-align: center;
            padding-top: 50px;
            color: #fff; /* Optional: change text color for better readability */
        }
        .button-container {
            margin: 20px;
        }
         .container {
            background-color: lightgreen; /* Set the container background color to lightgreen */
        }
    </style>
<body>

    <div class="main">

        <!-- Sign in Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure>
                            <img src="images/signin-image.jpg" alt="sing up image">
                        </figure>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Customer Login</h2>
                        <form method="post" action="CustomerLoginServlet" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="accountnumber"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="accountnumber" id="accountnumber" placeholder="Account Number" required />
                            </div>
                            <div class="form-group">
                                <label for="password"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="password" placeholder="Password" required />
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Log in" />
                            </div>
                        </form>
                        <% if (request.getAttribute("error") != null) { %>
                            <p class="error"><%= request.getAttribute("error") %></p>
                        <% } %>
                    </div>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body>
<!-- This template was made by Colorlib (https://colorlib.com) -->
</html>
