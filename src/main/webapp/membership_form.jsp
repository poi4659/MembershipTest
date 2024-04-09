<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Membership Sign Up</title>
</head>
<body>
    <h1>Membership Sign Up</h1>
    <form action="./MembershipInsert.mes" method="post" id="membershipSign">
		<h4>골드: 10000원	실버: 7000원</h4>
        멤버십 등급 : <input type="radio" name="membership_grade" value="Gold"> Gold
        <input type="radio" name="membership_grade" value="Silver"> Silver<br>
       	
       	결제 수단 : <input type="radio" name="payment_method" value="card"> 카드
        <input type="radio" name="payment_method" value="kakaopay"> 카카오페이<br>
        
        <input type="submit" value="Sign Up">
    </form>
</body>
</html>
