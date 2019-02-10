<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
<title>Вход</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body  class="w3-white">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>Вход</h1>
</div>
	<font color="red">${errorMessage}</font>
	<form class="w3-container" method="post">
        <div class="w3-row w3-margin">
            <label>Логин</label>
            <input class="w3-input w3-border" type="text" name="name" />
		</div><div class="w3-row w3-margin">
            <label>Пароль</label>
            <input class="w3-input w3-border" type="password" name="password" />
        </div>
        <div class="w3-row w3-margin">
		    <input class="w3-btn w3-green w3-round-large" value="Войти" type="submit" />
		</div>
	</form>
</body>

</html>