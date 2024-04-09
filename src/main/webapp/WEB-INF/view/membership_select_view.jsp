<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 관리</title>
</head>

<body>
<header id="main-header">
<div class="container">
<div class="row">
<div class="col-md-6">
<h1> 멤버십 관리 </h1>
</div>
</div>
</div>
</header>

<section>
<div class="container">
<div class="row">
<div class="card">
<div class="card-header">
<h5>멤버십 가입 여부</h5>
</div>

<div class="card-body">
<c:choose>
    <c:when test="${empty membershipDTO}">
        <p>멤버십에 가입하지 않았습니다.</p>
        <a href="./MembershipInsert.mes?user_id=${user_id}">멤버십 가입하기</a>
    </c:when>
    <c:otherwise>
        <p>멤버십에 가입되어 있습니다.</p>
        <!-- 여기에 가입 정보를 보여주는 코드 작성 -->
        <a href="./MembershipSelectDetail.mes?user_id=${user_id}">멤버십 상세보기</a>
    </c:otherwise>
</c:choose>

</div>

</div>
</div>
</div>
</section>

</body>
</html>
