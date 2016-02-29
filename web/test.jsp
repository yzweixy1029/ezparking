<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/2/15
  Time: 9:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="include.inc.jsp" %>
<html>
<head>
    <title>$Title$</title>
    <script type="application/javascript" src="lib/jquery-2.1.4.min.js"></script>
</head>
<body>

<button onclick="testAdd()">parking-add</button>
<button onclick="testDrop()">parking-drop</button>
<button onclick="testOrder()">record-order</button>
<div id="table">
    <table style="padding: 3px;margin: 3px">
        <div id="content">

        </div>
    </table>
</div>

<script type="application/javascript">
    function testAdd() {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "addParking/a",
            success: function (data) {
                var listContent = '';
                $.each(data, function (key, value) {
                    listContent += '<tr><td>' + value['parkingID'] + '</td><td>' + value['plateNumber'] + '</td><td>' + value['statu'] + '</td></tr>';
                });
                $("#content").html(listContent);
            }
        });
    }

    function testDrop() {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "dropParking/21",
            success: function (data) {
                var listContent = '';
                $.each(data, function (key, value) {
                    listContent += '<tr><td>' + value['parkingID'] + '</td><td>' + value['plateNumber'] + '</td><td>' + value['statu'] + '</td></tr>';
                });
                $("#content").html(listContent);
            }
        });
    }

    function testOrder() {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "getSortedRecords/startTime",
            success: function (data) {
                var listContent = '';
                $.each(data, function (key, value) {
                    listContent += '<tr><td>' + value['parkingID'] + '</td><td>' + value['cost'] + '</td><td>' + getLocalTime(value['startTime']) + '</td><td>' + getLocalTime(value['endTime']) + '</td><td>' + value['plateNumber'] + '</td></tr>';
                });
                $("#content").html(listContent);
            }
        });
    }

    function getLocalTime(nS) {
        return new Date(nS * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
    }

</script>

</body>
</html>
