<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h1>${title}</h1>
    <h1>${message}</h1>

	<sec:authorize access="isAuthenticated()">
		<!-- For login user -->

        <h2>Добро пожаловать, ${name}</h2>
        <h2><a href="javascript:formSubmit()"> Выход</a></h2>

		<c:url value="/logout" var="logoutUrl" />
		<form id="logout" action="${logoutUrl}" method="post" >
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

		<!--<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>-->
		<script>
			function formSubmit() {
				document.getElementById("logout").submit();
			}
		</script>



	</sec:authorize>
	<sec:authorize access="isAnonymous()">
		<!-- For anonymous user -->
			<h2>Вы не вошли в систему. <a href="login">Вход</a></h2>
	</sec:authorize>
</body>
</html>