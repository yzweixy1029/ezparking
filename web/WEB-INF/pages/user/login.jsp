<%--
  Created by IntelliJ IDEA.
  User: Caterina
  Date: 2015/5/6
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>管理员登录——EzParking停车管理系统</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath}/lib/jquery-2.1.4.min.js" type="text/javascript"></script>

    <!-- Demo page code -->

    <style type="text/css">
        #line-chart {
            height: 300px;
            width: 800px;
            margin: 0px auto;
            margin-top: 1em;
        }

        .brand {
            font-family: georgia, serif;
        }

        .brand .first {
            color: #ccc;
            font-style: italic;
            font-size: 34px;
        }

        .brand .second {
            color: #fff;
            font-weight: bold;
            font-size: 24px;
        }

        .board {
            margin-left: 15%;
            margin-right: 15%;
            margin-top: 5%;
            min-height: 450px;
        }
    </style>


</head>

<!--[if lt IE 7 ]>
<body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]>
<body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]>
<body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]>
<body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
<!--<![endif]-->

<div style="margin-left: 40px;margin-top: 40px">

    <a class="brand" href="${pageContext.request.contextPath}/index.jsp">
        <span class="first">EzParking</span>
        <span class="second">停车管理系统</span>
    </a>

</div>

<div class="board">


    <div class="row-fluid" style="float: right;margin-right: 20px">
        <div class="dialog">
            <div class="block">
                <p class="block-heading">登录</p>
                <br>

                <div class="block-body text-center">
                    <form:form action="sign_in" method="post" modelAttribute="admin">

                        <label>用户名</label>
                        <form:input path="userName" cssClass="span12"/>
                        <br>
                        <br>
                        <label>密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <form:password path="password" cssClass="span12"/>
                        <br>
                        <label class="remember-me"><input name="is_rmb" type="checkbox" value="1"
                                                          checked/>记住账号</label>
                        <br>
                        <button type="submit" class="btn btn-primary" style="width: 230px">登录</button>
                        <p style="color: red">${requestScope.error_message}</p>
                        <div class="clearfix"></div>
                    </form:form>
                    <br>
                    <br>

                    <p class="pull-right"><a href="reset-password.jsp">忘记密码？</a></p>
                    <br>
                </div>
            </div>

        </div>
    </div>
</div>


<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript">
    $("[rel=tooltip]").tooltip();
    $(function () {
        $('.demo-cancel-click').click(function () {
            return false;
        });
    });
</script>

</body>
</html>


