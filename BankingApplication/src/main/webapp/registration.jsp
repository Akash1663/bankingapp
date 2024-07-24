<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Customer Registration</title>

<!-- Font Icon -->
<link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="css/style.css">
</head>
<style>
 body {
            font-family: Arial, sans-serif;
            background: url('images/istockphoto-1343346538-640x640.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

 .container {
            background-color: white; /* Set the container background color to lightgreen */
        }
</style>
<body>
     
<div class="main">

    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-image">
                    <figure>
                        <img src="images/signup-image.jpg" alt="sign up image">
                    </figure>
                </div>
                <div class="signup-form">
                    <h2 class="form-title">Customer Registration</h2>
                    <form method="post" action="CustomerRegistrationServlet" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="fullname" id="name" placeholder="Your Name" required />
                        </div>
                        <div class="form-group">
                            <label for="address"><i class="zmdi zmdi-home"></i></label>
                            <input type="text" name="address" id="address" placeholder="Address" required />
                        </div>
                        <div class="form-group">
                            <label for="mobile"><i class="zmdi zmdi-phone"></i></label>
                            <input type="number" name="mobilenumber" id="mobile" placeholder="Contact No" required />
                        </div>
                        <div class="form-group">
                            <p>Type of Account</p>
                            <select name="accountType" required>
                                <option value=""></option>
                                <option value="Saving Account">Saving Account</option>
                                <option value="Current Account">Current Account</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="Ibal"><i class="zmdi zmdi-money"></i></label>
                            <input type="number" name="initialbalance" id="Ibal" placeholder="Initial Balance" required />
                        </div>
                        <div class="form-group">
                            <label for="dob"><i class="zmdi zmdi-calendar"></i></label>
                            <input type="date" name="dob" id="dob" placeholder="Date of Birth" required />
                        </div>
                        <div class="form-group">
                            <label for="idproof"><i class="zmdi zmdi-assignment"></i></label>
                            <input type="text" name="idProof" id="idproof" placeholder="ID Proof" required />
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Register" />
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
