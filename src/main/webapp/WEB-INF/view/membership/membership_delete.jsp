<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십 해지</title>
</head>
<body>
<h1>멤버십 해지</h1>


<form action="./MembershipDelete.mes" method="post">
<fieldset>
<div class="form-group row">
<label for="user_id">사용자 아이디</label>
<div> 
<input type="text" name="user_id" id="user_id" 
value="${param.user_id}" readonly>
</div>
</div>
<div class="form-group">

<button type="submit">삭제</button>
<button type="reset">취소</button>
</div>
</fieldset>
</form>



</body>
</html>
