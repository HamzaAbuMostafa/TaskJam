<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
        .create-btn {
            background-color: #28a745;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }
        .create-btn:hover {
            background-color: #218838;
        }
        .projects-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .project-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        .project-table th, .project-table td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .project-table th {
            background-color: #f8f9fa;
            font-weight: bold;
            color: #333;
        }
        .project-table tr:hover {
            background-color: #f1f1f1;
        }
        .view-link {
            color: #007bff;
            text-decoration: none;
            font-weight: bold;
        }
        .view-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<%
    Integer userId = (Integer) request.getSession(false).getAttribute("userId");
%>

<div class="container">
    <div class="header">
        <h2>Welcome to TaskJam Dashboard!</h2>
        <a href="logout" class="logout-btn">Logout</a>
    </div>

    <div class="content">
        <p><strong>Your User ID is:</strong> <%= userId %></p>
        <p>You have successfully authenticated and are viewing a protected page.</p>

        <hr style="border: 0; border-top: 2px solid #eaeaea; margin: 20px 0;">

        <div class="projects-header">
            <h3 style="margin: 0;">Your Projects</h3>
            <!-- Routes to the doGet method of ProjectServlet -->
            <a href="project" class="create-btn">+ Create Project</a>
        </div>

        <!-- JSTL Table Rendering -->
        <table class="project-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Project Name</th>
                <th>Description</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <!-- Loop over the DTOs attached to the request by the DashboardServlet -->
            <c:forEach items="${projectsList}" var="project">
                <tr>
                    <!-- Because ProjectDTO is a record, we call the methods directly -->
                    <td>${project.id()}</td>
                    <td>${project.name()}</td>
                    <td>${project.description()}</td>
                    <td>
                        <a href="project-detail.jsp?id=${project.id()}" class="view-link">View Workspace</a>
                    </td>
                </tr>
            </c:forEach>

            <!-- Optional: Show a message if the list is empty -->
            <c:if test="${empty projectsList}">
                <tr>
                    <td colspan="4" style="text-align: center; color: #777;">
                        You haven't created any projects yet.
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>