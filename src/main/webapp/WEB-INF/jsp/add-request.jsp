<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>Добавить заявку</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-white">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
    <h1>Добавить заявку</h1>
</div>
<form action="add-request" method="post" name="burning" id="burning" class="w3-container">
    <div class="w3-row w3-margin">
        <label>Имя: </label>
        <input class="w3-input w3-border" required type="text" name="name" id="name" value="" size="25"/>
    </div>
    <div class="w3-row w3-margin">
        <div class="w3-col" style="width:45%">
            <label>Дата: </label>
            <input class="w3-input w3-border" required type="date" name="date" id="date" value="${current_date}"
                   min="${current_date}"/>
        </div>
        <div class="w3-col" style="width:10%">
        &nbsp;
        </div>
        <div class="w3-col" style="width:45%">
            <label>Время: </label>
            <input class="w3-input w3-border" required type="time" name="time" id="time" value="00:00"/>
        </div>
    </div>
    <div class="w3-row w3-margin">
        <label>Программа: </label>
        <select class="w3-select w3-border" required size="2" name="type">
            <option value="1">Утиль (26 часов)</option>
            <option selected value="2">Политой (26 часов)</option>
        </select>
    </div>
    <div class="w3-row w3-margin">
        <label>Комментарий: </label>
        <textarea class="w3-input w3-border" name="comment" id="comment" cols="48" rows="8"> </textarea>
    </div>
    <div class="w3-row w3-margin">
        <input class="w3-btn w3-green w3-round-large" style="float: left;margin-right: 20px;" name="submit" type="submit" id="submit" value="Отправить"/>

        <button class="w3-btn w3-grey w3-round-large"  type="cancel"
                onclick="window.location='/calendar';return false;">Отмена
        </button>
    </div>
</form>
</body>
</html>