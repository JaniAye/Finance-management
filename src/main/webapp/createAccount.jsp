<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 5/19/2023
  Time: 10:43 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Finance Management System - Create Account</title>
</head>
<body>
<h1>Finance Management System - Create Account</h1>
    <form action="CreateAccountServlet" method="post">
        <label for="accountType">Account Type:</label>
        <select id="accountType" name="accountType" required>
            <option value="loan">Loan</option>
            <option value="fixedDeposit">Fixed Deposit</option>
            <option value="savings">Savings</option>
        </select><br>

        <label for="initialDeposit">Initial Deposit:</label>
        <input type="number" id="initialDeposit" name="initialDeposit" required><br>

        <input type="submit" value="Create Account">
    </form>
</body>
</html>
