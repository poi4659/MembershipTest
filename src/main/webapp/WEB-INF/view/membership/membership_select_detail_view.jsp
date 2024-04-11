<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 정보</title>
</head>
<body>

	<h1>상세보기</h1>
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
    
    <a href="./MembershipUpdateView.mes?user_id=${membershipDTO.user_id}">
    멤버십 수정하기</a>

    <a href="./MembershipDeleteView.mes?user_id=${membershipDTO.user_id}">
    멤버십 해지하기</a>

</body>
</html>
