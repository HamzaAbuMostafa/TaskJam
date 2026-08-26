<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TaskJam - Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #eaeaea;
            padding-bottom: 10px;
            margin-bottom: 20px;
        }
        .logout-btn {
            background-color: #ff4d4d;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .logout-btn:hover {
            background-color: #cc0000;
        }
    </style>
</head>
<body>

<%
    // 1. Session Authorization Check
    // Grab the current session (passing 'false' means it won't create a new one if it doesn't exist)
    HttpSession currentSession = request.getSession(false);
    Integer userId = null;

    if (currentSession != null) {
        userId = (Integer) currentSession.getAttribute("userId");
    }

    // If the session is missing or the userId attribute is null, kick them out
    if (userId == null) {
        response.sendRedirect("register.jsp?error=Please log in to view your dashboard");
        return; // Stop processing the rest of the JSP
    }
%>

<div class="container">
    <div class="header">
        <h2>Welcome to TaskJam Dashboard!</h2>
        <!-- You will eventually map this to a LogoutServlet -->
        <a href="logoutServlet" class="logout-btn">Logout</a>
    </div>

    <div class="content">
        <p><strong>Your User ID is:</strong> <%= userId %></p>
        <p>You have successfully authenticated and are viewing a protected page.</p>

        <hr>

        <h3>Your Projects</h3>
        <p><em>(Project fetching logic will go here later using ProjectMembersService)</em></p>
    </div>
</div>

</body>
</html>