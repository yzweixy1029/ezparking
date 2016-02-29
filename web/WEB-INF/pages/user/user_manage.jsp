<%--
  Created by IntelliJ IDEA.
  User: Caterina
  Date: 2015/5/6
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/include.inc.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>账户管理——奥坦科石油设备有限公司资源管理系统</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath}/lib/jquery-2.1.4.min.js" type="text/javascript"></script>


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

        a:focus {
            outline: none;
        }

        a {
            text-decoration: none;
        }

    </style>


</head>

<body class="">
<!--<![endif]-->

<!--顶部导航栏开始-->
<div class="navbar">
    <div class="navbar-inner">

        <a class="brand" href="${pageContext.request.contextPath}/scan_order">
            <span class="first">Oiltech</span>
            <span class="second">资源管理系统</span>
        </a>

        <ul class="nav navbar-nav navbar-right">
            <%--<li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">账户管理</a></li>--%>
            <c:if test="${!empty sessionScope.account}">
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                    欢迎您,${sessionScope.account.name}
                    <span class="caret"></span>
                </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuDivider">
                        <li><a href="${pageContext.request.contextPath}/logout">注销</a></li>
                    </ul>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<!--顶部导航栏结束-->

<!--左侧正文开始-->
<div class="content">

    <div class="header">

        <div class="stats">

            <p class="stat"><span class="number" style="color: red">${sessionScope.state_num[3]}</span>份结算中订单</p>

            <p class="stat"><span class="number" style="color: red">${sessionScope.state_num[2]}</span>份运输中订单</p>

            <p class="stat"><span class="number" style="color: red">${sessionScope.state_num[1]}</span>份在生产订单</p>

            <p class="stat"><span class="number" style="color: red">${sessionScope.state_num[0]}</span>份预约订单</p>

        </div>

        <h1 class="page-title">账户管理</h1>

    </div>

    <ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/scan_order">主页</a> <span class="divider">/</span></li>
        <li class="active">账户管理</li>
    </ul>

    <div class="container-fluid">
        <div class="row-fluid">

            <div class="row-fluid">
                <div class="block span6">
                    <a href="#tablewidget" class="block-heading" data-toggle="collapse">账户列表<span
                            class="label label-warning">${fn:length(users)}</span></a>

                    <div id="tablewidget" class="block-body collapse in">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>用户名</th>
                                <th>权限</th>
                                <th>编辑</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:if test="${!empty users}">
                                <c:forEach items="${users}" var="user">
                                    <tr>
                                        <td>${user.id}</td>
                                        <td>${user.name}</td>
                                        <c:if test="${user.authority == 0}">
                                            <td style="color: red">经理</td>
                                        </c:if>
                                        <c:if test="${user.authority == 1}">
                                            <td style="color: red">业务员</td>
                                        </c:if>
                                        <c:if test="${user.authority == 2}">
                                            <td style="color: red">财务</td>
                                        </c:if>

                                        <td><a href="${pageContext.request.contextPath}/get_user/${user.id}"><span
                                                class="glyphicon glyphicon-list"
                                                aria-hidden="true"></span>修改信息</a>|
                                            <a href="${pageContext.request.contextPath}/delete_user/${user.id}"><span
                                                    class="glyphicon glyphicon-trash"
                                                    aria-hidden="true"></span>删除</a>
                                        </td>

                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


            <footer style="position:fixed;bottom:0px;width: 98%">
                <hr>

                <p class="pull-right">&copy; 2015 <a href="#" target="_blank">Chw</a></p>

            </footer>

        </div>
    </div>

</div>
<!--右侧正文结束-->

<div class="sidebar-nav">
    <div class="panel panel-default" style="height: 100%">
        <div class="panel-heading">
            <h3 class="panel-title">操作</h3>
        </div>
        <c:if test="${authority == 0}">
            <div class="panel-body">
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="${pageContext.request.contextPath}/sign_up">新增账户</a>
                    </li>
                </ul>
            </div>
        </c:if>

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


