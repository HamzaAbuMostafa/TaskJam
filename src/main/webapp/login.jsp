<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TaskJam - Login</title>
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
            box-sizing: border-box;
        }
        .btn {
            width: 100%;
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            font-weight: bold;
            cursor: pointer;
            margin-top: 10px;
        }
        .btn:hover {
            background-color: #218838;
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
        .success-message {
            color: #155724;
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
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
    </style>
</head>
<body>

<div class="card">
    <h2>Welcome Back</h2>

    <%
        // Check for success messages (triggered by RegisterServlet)
        String success = request.getParameter("success");
        if (success != null && !success.trim().isEmpty()) {
    %>
    <div class="success-message">
        <%= success %>
    </div>
    <% } %>

    <%
        // Check for login errors (triggered by LoginServlet)
        String error = request.getParameter("error");
        if (error != null && !error.trim().isEmpty()) {
    %>
    <div class="error-message">
        <%= error %>
    </div>
    <% } %>

    <!-- Posts to your LoginServlet -->
    <form action="loginServlet" method="POST">
        <div class="form-group">
            <label for="email">Email Address</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>
        </div>

        <button type="submit" class="btn">Log In</button>
    </form>

    <div class="footer-text">
        Don't have an account? <a href="register.jsp">Register here</a>
    </div>
</div>

</body>
</html>