<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 수정</title>
</head>
<body>
    <h1>멤버십 수정</h1>
    <form action="./MembershipUpdate.mes" method="post" id="membershipSign">
        아이디: <input type="text" name="user_id" id="user_id"
        value="${param.user_id}"><br>
        멤버십 등급:
        <input type="radio" name="new_membership_grade" value="Gold"> Gold (10000원)
        <input type="radio" name="new_membership_grade" value="Silver"> Silver (7000원)<br>
       	
        결제 수단:
        <input type="radio" name="new_payment_method" value="card"> 카드
        <input type="radio" name="new_payment_method" value="kakaopay"> 카카오페이<br>
        
        <input type="submit" value="수정하기">
    </form>
</body>
</html>