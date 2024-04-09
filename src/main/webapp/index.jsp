<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버십</title>
</head>

<body>
<!-- 
바로 가입 페이지로 이동
<script type="text/javascript">

location.href = "MembershipInsertView.mes";

</script>
 -->

<!-- 시작 --> 
<form id="userIdForm" action="MembershipSelect.mes" method="post">
        <label for="userId">사용자 아이디:</label>
        <input type="text" id="userId" name="user_id">
        <input type="submit" value="확인">
</form>

</body>
</html>
