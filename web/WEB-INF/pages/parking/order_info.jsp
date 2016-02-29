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

    <title>订单详情——奥坦科石油设备有限公司资源管理系统</title>
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

        <h1 class="page-title">订单详情</h1>

    </div>

    <ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/scan_order">主页</a> <span class="divider">/</span></li>
        <li class="active">订单详情</li>
    </ul>

    <div class="container-fluid">
        <div class="row-fluid">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <span style="margin-left: 20px">订单详情</span>

                        <c:if test="${order.orderState==0}">
                            <div class="progress" style="float: right;min-width: 400px">
                                <div class="progress-bar" role="progressbar" aria-valuenow="20"
                                     aria-valuemin="0"
                                     aria-valuemax="100" style="width: 20%;">
                                    已预约
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${order.orderState==1}">
                            <div class="progress" style="float: right;min-width: 400px">
                                <div class="progress-bar" role="progressbar" aria-valuenow="40"
                                     aria-valuemin="0"
                                     aria-valuemax="100" style="width: 40%;">
                                    生产中
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${order.orderState==2}">
                            <div class="progress" style="float: right;min-width: 400px">
                                <div class="progress-bar" role="progressbar" aria-valuenow="60"
                                     aria-valuemin="0"
                                     aria-valuemax="100" style="width: 60%;">
                                    运输中
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${order.orderState==3}">
                            <div class="progress" style="float: right;min-width: 400px">
                                <div class="progress-bar" role="progressbar" aria-valuenow="80"
                                     aria-valuemin="0"
                                     aria-valuemax="100" style="width: 80%;">
                                    结算中
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${order.orderState==4}">
                            <div class="progress" style="float: right;min-width: 400px">
                                <div class="progress-bar" role="progressbar" aria-valuenow="100"
                                     aria-valuemin="0"
                                     aria-valuemax="100" style="width: 100%;">
                                    已完成
                                </div>
                            </div>
                        </c:if>
                    </h4>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>客户信息</th>
                            <th>产品信息</th>
                            <th>约定交货时间</th>
                            <th>贸易方式</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${order.customerName}</td>
                            <td>${order.productionInfo}</td>
                            <td><fmt:formatDate value="${order.agreedDeliveryDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${order.tradeWay}</td>
                        </tr>
                        </tbody>
                        <thead>
                        <tr>
                            <th>销售单价</th>
                            <th>销售总价</th>
                            <th>预付款数目</th>
                            <th>预付款状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${order.salesUnitPrice}</td>
                            <td>${order.salesTotalPrice}</td>
                            <td>${order.advancePayment}</td>
                            <td>${order.advanceState==0?"未支付":"已支付"}</td>
                        </tr>
                        </tbody>
                        <thead>
                        <tr>
                            <th>采购单价</th>
                            <th>采购总价</th>
                            <th>供应商信息</th>
                            <th>工厂生产状态</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${order.purchaseUnitPrice}</td>
                            <td>${order.purchaseTotalPrice}</td>
                            <td>${order.supplierInfo}</td>
                            <td>${order.productState}</td>
                        </tr>
                        </tbody>
                        <thead>
                        <tr>
                            <th>订舱状态</th>
                            <th>船期</th>
                            <th>运输费用</th>
                            <th>港杂费</th>
                            <th>是否完成报关</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${order.bookingState == 0 ? '未订' : '已订' }</td>
                            <td>${order.shipment}</td>
                            <td>${order.freight}</td>
                            <td>${order.portSurcharge}</td>
                            <td>${order.declareAtCustoms == 0 ? '未完成' : '已完成' }</td>
                        </tr>
                        </tbody>
                        <thead>
                        <tr>
                            <th>尾款数目</th>
                            <th>尾款是否付清</th>
                            <th>发票状态</th>
                            <th>税前利润</th>
                            <th>税后利润</th>
                            <th>退税金额</th>
                            <th>退税是否申报</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${order.finalPayment}</td>
                            <td>${order.finalPaymentState == 0 ? '未付清' : '已付清' }</td>
                            <td>${order.invoiceState == 0 ? '未寄到' : '已寄到' }</td>
                            <td>${order.profitBeforeTax}</td>
                            <td>${order.profitAfterTax}</td>
                            <td>${order.taxReimbursement}</td>
                            <td>${order.taxReimbursementState == 0 ? '未申报' : '已申报' }</td>
                        </tr>
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
                        <a href="${pageContext.request.contextPath}/update_order/${order.id}">编辑订单</a>
                    </li>
                    <li class="list-group-item">
                        <a href="${pageContext.request.contextPath}/delete_order/${order.id}">删除订单</a>
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


