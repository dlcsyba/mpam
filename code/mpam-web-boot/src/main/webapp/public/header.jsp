<%--
  Created by IntelliJ IDEA.
  User: dlcsyba
  Date: 2018/1/10
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<!-- 获取当前项目的路径，如：http://localhost:8080/项目名称。 -->
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<link href="plugins/sweetalert/dist/sweetalert.css" rel="stylesheet" type="text/css">
<link href="plugins/morris.js/morris.css" rel="stylesheet" type="text/css">
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/core.css" rel="stylesheet" type="text/css">
<link href="css/icons.css" rel="stylesheet" type="text/css">
<link href="css/components.css" rel="stylesheet" type="text/css">
<link href="css/pages.css" rel="stylesheet" type="text/css">
<link href="css/menu.css" rel="stylesheet" type="text/css">
<link href="css/responsive.css" rel="stylesheet" type="text/css">

<script src="js/modernizr.min.js"></script>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>

<!-- jQuery  -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/detect.js"></script>
<script src="js/fastclick.js"></script>
<script src="js/jquery.slimscroll.js"></script>
<script src="js/jquery.blockUI.js"></script>
<script src="js/waves.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/jquery.nicescroll.js"></script>
<script src="js/jquery.scrollTo.min.js"></script>

<script src="js/jquery.app.js"></script>
<script src="plugins/jquery-ui/jquery-ui.min.js"></script>

<!-- jQuery  -->
<script src="plugins/moment/moment.js"></script>

<!-- jQuery  -->
<script src="plugins/waypoints/lib/jquery.waypoints.js"></script>
<script src="plugins/counterup/jquery.counterup.min.js"></script>

<!-- jQuery  -->
<script src="plugins/sweetalert/dist/sweetalert.min.js"></script>

<!-- flot Chart -->
<script src="plugins/flot-chart/jquery.flot.js"></script>
<script src="plugins/flot-chart/jquery.flot.time.js"></script>
<script src="plugins/flot-chart/jquery.flot.tooltip.min.js"></script>
<script src="plugins/flot-chart/jquery.flot.resize.js"></script>
<script src="plugins/flot-chart/jquery.flot.pie.js"></script>
<script src="plugins/flot-chart/jquery.flot.selection.js"></script>
<script src="plugins/flot-chart/jquery.flot.stack.js"></script>
<script src="plugins/flot-chart/jquery.flot.crosshair.js"></script>

<!--Morris Chart-->
<script src="plugins/raphael/raphael-min.js"></script>
<script src="plugins/morris.js/morris.min.js"></script>
