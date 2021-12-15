<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../templates/taglibs.jsp"%>

<html>
<head>
    <title>home</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <label>로그인 완료</label>

</sec:authorize>

<sec:authorize access="isAnonymous()">
    <script>
        alert('접근 권한이 없습니다. 로그인 페이지로 이동합니다.');
        window.location = '/login';
    </script>
</sec:authorize>

<%@ include file="../templates/footer.jsp" %>
</body>
</html>