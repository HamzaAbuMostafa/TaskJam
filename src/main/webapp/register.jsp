<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TaskJam - Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .card {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }
        .card h2 {
            margin-top: 0;
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #555;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box; /* Ensures padding doesn't affect width */
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 10px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: #721c24;
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 15px;
            text-align: center;
            font-size: 14px;
        }
        .footer-text {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
        }
        .footer-text a {
            color: #007bff;
            text-decoration: none;
        }
        .footer-text a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="card">
    <h2>Create an Account</h2>

    <%
        // Check if the Servlet redirected here with an error in the URL
        String error = request.getParameter("error");
        if (error != null && !error.trim().isEmpty()) {
    %>
    <!-- This div only appears if an error exists -->
    <div class="error-message">
        <%= error %>
    </div>
    <% } %>

    <!-- The action matches your @WebServlet("/registerServlet") -->
    <form action="registerServlet" method="POST">
        <div class="form-group">
            <label for="userName">Username</label>
            <!-- name="userName" MUST match request.getParameter("userName") -->
            <input type="text" id="userName" name="userName" required>
        </div>

        <div class="form-group">
            <label for="email">Email Address</label>
            <!-- name="email" MUST match request.getParameter("email") -->
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <!-- name="password" MUST match request.getParameter("password") -->
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="btn">Register</button>
    </form>

    <div class="footer-text">
        Already have an account? <a href="login.jsp">Log in here</a>
    </div>
</div>

</body>
</html>