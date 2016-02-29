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

    <title>停车位状态——EzParking停车管理系统</title>
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
            <span class="first">EzParking</span>
            <span class="second">停车管理系统</span>
        </a>

        <ul class="nav navbar-nav navbar-right">
            <%--<li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">账户管理</a></li>--%>
            <c:if test="${!empty sessionScope.account}">
                <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                    欢迎您,${sessionScope.admin.userName}
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

            <p class="stat"><span class="number" style="color: red">${sessionScope.state_num[3]}</span>个空闲车位</p>

            <p class="stat"><span class="number" style="color: red">${sessionScope.state_num[2]}</span>个已占用车位</p>

        </div>

        <h1 class="page-title">订单一览</h1>

    </div>

    <ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/scan_order">主页</a> <span class="divider">/</span></li>
        <li class="active">订单一览</li>
    </ul>

    <div class="container-fluid">
        <div class="row-fluid">

            <div class="row-fluid">
                <div class="block span6">
                    <a href="#tablewidget" class="block-heading" data-toggle="collapse">订单列表<span
                            class="label label-warning">${fn:length(orders)}</span></a>

                    <div id="tablewidget" class="block-body collapse in">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>客户信息</th>
                                <th>产品信息</th>
                                <th>约定交货时间</th>
                                <th>贸易方式</th>
                                <th>订单进度</th>
                                <th>编辑</th>

                            </tr>
                            </thead>

                            <tbody>
                            <c:if test="${empty orders}">
                                <tr>
                                    <td colspan="6" style="text-align:center;color: grey">无任何订单</td>
                                </tr>
                            </c:if>
                            <c:if test="${!empty orders}">
                                <c:forEach items="${orders}" var="order">
                                    <tr>
                                        <td>${order.customerName}</td>
                                        <td>${order.productionInfo}</td>
                                        <td>${order.agreedDeliveryDate}</td>
                                        <td>${order.tradeWay}</td>
                                        <c:if test="${order.orderState == 0}">
                                            <td style="color: red">已预约</td>
                                        </c:if>
                                        <c:if test="${order.orderState == 1}">
                                            <td style="color: red">生产中</td>
                                        </c:if>
                                        <c:if test="${order.orderState == 2}">
                                            <td style="color: red">运输中</td>
                                        </c:if>
                                        <c:if test="${order.orderState == 3}">
                                            <td style="color: red">结算中</td>
                                        </c:if>
                                        <c:if test="${order.orderState == 4}">
                                            <td style="color: red">已完成</td>
                                        </c:if>

                                        <td><a href="get_order/${order.id}"><span
                                                class="glyphicon glyphicon-list"
                                                aria-hidden="true"></span>详情</a>|
                                            <a href="delete_order/${order.id}"><span
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
                        <a href="get_users">管理账户</a>
                    </li>
                    <li class="list-group-item">
                        <a href="#">编辑业务员提成</a>
                    </li>
                </ul>
            </div>
        </c:if>
        <c:if test="${authority == 1}">
            <div class="panel-body">
                <ul class="list-group">
                    <li class="list-group-item">
                        <a href="${pageContext.request.contextPath}/create_order">创建订单</a>
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


