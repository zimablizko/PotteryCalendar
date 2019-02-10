<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
<title>Календарь гончаров</title>
<!--<link rel="stylesheet" href="/css/style.css" type="text/css" media="all">-->
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="/fullcalendar/fullcalendar.css" />
<script src="/fullcalendar/lib/jquery.min.js"></script>
<script src="/fullcalendar/lib/moment.min.js"></script>
<script src="/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/fullcalendar/gcal.js"></script>
<script type="text/javascript" src="/fullcalendar/locale/ru.js"></script>
<script>
$(function() {
  // page is now ready, initialize the calendar...
  $('#calendar').fullCalendar({
                                 googleCalendarApiKey: 'AIzaSyAM24RI5HuNdRytVFVy2TaDr4QRqQquk5w',
                                 events: {
                                   googleCalendarId: 'ceramics.petersburg@gmail.com'
                                 },
                                 header: {
                                         left: 'prev,next today',
                                         center: 'title',
                                         right: 'month,basicWeek,basicDay'
                                       },
                                 locale:'ru'
                               });
});
</script>
</head>
<body class="w3-white">
<div class="w3-container w3-blue-grey w3-opacity w3-left-align">
  <h1>Календарь</h1>
</div>
<div class="w3-container w3-light-grey w3-opacity w3-left-align" style="padding: 13px 0px 0px 16px;">
<div style="float: left;margin-right: 20px;">
<form action="add-request-form" method="post">
<input class="w3-btn w3-green w3-round-large" type="submit" value="Добавить заявку" />
</form>
</div>
<div style="float: left;">
<form action="delete-request-form" method="post">
<input class="w3-btn w3-red w3-round-large" type="submit" value="Удалить заявку" disabled/>
</form>
</div>
<div style="float: right;margin-right: 20px;">
<form action="login" method="get">
<input class="w3-btn w3-grey w3-round-large" type="submit" value="Войти" />
</form>
</div>
</div>


<br>
<div class="w3-container w3-center">
<div id="calendar" style="max-width: 900px;margin: auto;"></div>
<!--<iframe src="https://calendar.google.com/calendar/b/1/embed?showTitle=0&amp;showPrint=0&amp;height=600&amp;wkst=2&amp;bgcolor=%23FFFFFF&amp;src=ceramics.petersburg%40gmail.com&amp;color=%231B887A&amp;ctz=Europe%2FMoscow" style="border-width:0" width="800" height="600" frameborder="0" scrolling="no"></iframe>-->
</div>
</body>
</html>