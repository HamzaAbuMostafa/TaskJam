<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TaskJam - Project Details</title>
    <style>
        /* Reusing the exact CSS from your dashboard page */
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
        .dashboard-btn {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .dashboard-btn:hover {
            background-color: #0056b3;
        }
        .success-message {
            color: #155724;
            background-color: #d4edda;
            border: 1px solid #c3e6cb;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="header">
        <h2>Project Workspace</h2>
        <a href="dashboard.jsp" class="dashboard-btn">Back to Dashboard</a>
    </div>

    <%
        String message = request.getParameter("message");
        if (message != null && !message.trim().isEmpty()) {
    %>
    <div class="success-message">
        <%= message %>
    </div>
    <% } %>

    <div class="content">
        <%
            String projectId = request.getParameter("id");
        %>
        <h3>You are viewing Project ID: <%= projectId %></h3>
        <p>Your manual transaction succeeded! The project was saved, and the creator was assigned as a member.</p>

        <hr>
        <p><em>(In a future phase, you will use projectService.getProjectDetails(<%= projectId %>) to load the real data here)</em></p>
    </div>
</div>

</body>
</html>