<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Membership Sign Up</title>
</head>
<body>
    <h1>Membership Information</h1>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Membership Grade</th>
            <th>Payment Date</th>
            <th>Payment Method</th>
            <th>Payment Price</th>
        </tr>
        <tr>
            <td>${membershipDTO.user_id}</td>
            <td>${membershipDTO.membership_grade}</td>

            <td>${membershipDTO.payment_date}</td> 
            
            <td>${membershipDTO.payment_method}</td>
            <td>${membershipDTO.payment_price}</td>
        </tr>
    </table>
</body>
</html>
