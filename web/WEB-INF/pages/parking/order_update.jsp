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

    <title>更新订单——奥坦科石油设备有限公司资源管理系统</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheets/theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css">

    <script src="${pageContext.request.contextPath}/lib/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/lib/datepicker/javascript/zebra_datepicker.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/datepicker/css/default.css" type="text/css">

    <script type="text/javascript">
        $(document).ready(function () {
            $("#deliverDate").Zebra_DatePicker();
        });
    </script>

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

        .error {
            margin-left: 2px;
            color: red;
            font-size: 8px;
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

        <h1 class="page-title">修改订单</h1>

    </div>

    <ul class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/scan_order">主页</a> <span class="divider">/</span></li>
        <li class="active">修改订单</li>
    </ul>

    <div class="container-fluid">
        <div class="row-fluid">

            <div class="panel panel-default">
                <form:form action="${pageContext.request.contextPath}/save_order" method="post"
                           modelAttribute="order">
                    <form:hidden path="id"/>
                    <div class="panel-heading" style="background-color: lightgray;font-family:'Microsoft YaHei UI'">
                        <h4 class="panel-title">
                            <span>订单详情</span>

                            <div style="float: right">
                                <span>订单状态：</span>
                                <form:select path="orderState">
                                    <form:option value="0">已预约</form:option>
                                    <form:option value="1">生产中</form:option>
                                    <form:option value="2">运输中</form:option>
                                    <form:option value="3">结算中</form:option>
                                    <form:option value="4">已完成</form:option>
                                </form:select>
                            </div>

                        </h4>
                    </div>
                    <div class="panel-body">


                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>客户信息</th>
                                <th>产品信息</th>
                                <th>约定交货时间<form:errors path="agreedDeliveryDate" cssClass="error"/> </th>
                                <th>贸易方式</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><form:input path="customerName"/></td>
                                <td><form:input path="productionInfo"/></td>
                                <td><form:input id="deliverDate" path="agreedDeliveryDate"/></td>
                                <td><form:input path="tradeWay"/></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>销售单价<form:errors path="salesUnitPrice" cssClass="error"/> </th>
                                <th>销售总价<form:errors path="salesTotalPrice" cssClass="error"/></th>
                                <th>预付款数目<form:errors path="advancePayment" cssClass="error"/></th>
                                <th>预付款状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><form:input path="salesUnitPrice"/></td>
                                <td><form:input path="salesTotalPrice"/></td>
                                <td><form:input path="advancePayment"/></td>
                                <td><form:select path="advanceState">
                                    <form:option value="0">未支付</form:option>
                                    <form:option value="1">已支付</form:option>
                                </form:select></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>采购单价<form:errors path="purchaseUnitPrice" cssClass="error"/></th>
                                <th>采购总价<form:errors path="purchaseTotalPrice" cssClass="error"/></th>
                                <th>供应商信息</th>
                                <th>工厂生产状态</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><form:input path="purchaseUnitPrice"/></td>
                                <td><form:input path="purchaseTotalPrice"/></td>
                                <td><form:input path="supplierInfo"/></td>
                                <td><form:input path="productState"/></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>订舱状态</th>
                                <th>船期</th>
                                <th>运输费用<form:errors path="freight" cssClass="error"/></th>
                                <th>港杂费<form:errors path="portSurcharge" cssClass="error"/></th>
                                <th>是否完成报关</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><form:select path="bookingState">
                                    <form:option value="0">未订</form:option>
                                    <form:option value="1">已订</form:option>
                                </form:select></td>
                                <td><form:input path="shipment"/></td>
                                <td><form:input path="freight"/></td>
                                <td><form:input path="portSurcharge"/></td>
                                <td><form:select path="declareAtCustoms">
                                    <form:option value="0">未完成</form:option>
                                    <form:option value="1">已完成</form:option>
                                </form:select></td>
                            </tr>
                            </tbody>
                            <thead>
                            <tr>
                                <th>尾款数目<form:errors path="finalPayment" cssClass="error"/></th>
                                <th>尾款是否付清</th>
                                <th>发票状态</th>
                                <th>税前利润<form:errors path="profitBeforeTax" cssClass="error"/></th>
                                <th>税后利润<form:errors path="profitAfterTax" cssClass="error"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><form:input path="finalPayment"/></td>
                                <td><form:select path="finalPaymentState">
                                    <form:option value="0">未付清</form:option>
                                    <form:option value="1">已付清</form:option>
                                </form:select></td>
                                <td><form:select path="invoiceState">
                                    <form:option value="0">未寄到</form:option>
                                    <form:option value="1">已寄到</form:option>
                                </form:select></td>
                                <td><form:input path="profitBeforeTax"/></td>
                                <td><form:input path="profitAfterTax"/></td>

                            </tr>
                            <thead>
                            <tr>
                                <th>退税金额<form:errors path="taxReimbursement" cssClass="error"/></th>
                                <th>退税是否申报</th>
                            </tr>
                            </thead>
                            <tr>
                                <td><form:input path="taxReimbursement"/></td>
                                <td><form:select path="taxReimbursementState">
                                    <form:option value="0">未申报</form:option>
                                    <form:option value="1">已申报</form:option>
                                </form:select></td>
                            </tr>
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-primary" style="width: 150px;float: right">提交</button>

                    </div>
                </form:form>
            </div>
        </div>


        <footer style="position:relative;bottom:0px;width: 100%">
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
                        <a href="#">管理账户</a>
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
                        <a href="${pageContext.request.contextPath}/create_order">编辑订单</a>
                    </li>
                    <li class="list-group-item">
                        <a href="${pageContext.request.contextPath}/create_order">删除订单</a>
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


