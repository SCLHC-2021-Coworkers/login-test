<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="../templates/taglibs.jsp" %>

<html>
<head>
    <%@ include file="../templates/headprops.jsp" %>
    <link href="/css/login.css" rel="stylesheet">
    <title>Login</title>
</head>
<body>
<sec:authorize access="isAuthenticated()">
    <script>
        window.location = '/';
    </script>
</sec:authorize>

<sec:authorize access="isAnonymous()">
<div class="row">
    <div class="col-xl-4"></div>
    <div class="col-xl-3">

        <form id="login_form" style="margin-top:80px; padding: 20px 30px 20px 30px;">
            <img src="images/logo.png" style="margin-bottom: 110px;">
            <div class="form-group">
                <label>아이디:</label>
                <input type="text" id="username" class="form-control">
            </div>
            <div class="form-group">
                <label>비밀번호:</label>
                <input type="password" id="password" class="form-control">
            </div>
            <div class="d-flex justify-content-center">
                <div>
                    <button type="submit" class="btn btn-info" value="Submit" style="width:400px">로그인</button>
                </div>
            </div>
            <br/>
            <div class="d-flex justify-content-end">
                <button
                        type="button"
                        class="btn btn-link"
                        id="to_register_btn"
                        style="text-decoration: underline; text-underline-position:under;"
                >
                    관리자등록
                </button>
            </div>
        </form>
    </div>
</div>
</sec:authorize>

<%@ include file="../templates/footer.jsp" %>
<script src="/js/auth/login.js"></script>
<script src="/js/auth/checkRegPermit.js"></script>

</body>
</html>