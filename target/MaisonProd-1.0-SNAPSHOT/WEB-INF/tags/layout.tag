<%@tag body-content="scriptless" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminLTE 3 | Dashboard</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value="/resources/plugins/fontawesome-free/css/all.min.css"/>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Tempusdominus Bootstrap 4 -->
    <link rel="stylesheet" href="<c:url value="/resources/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css"/>">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value="/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css"/>">
    <!-- JQVMap -->
    <link rel="stylesheet" href="<c:url value="/resources/plugins/jqvmap/jqvmap.min.css"/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value="/resources/dist/css/adminlte.min.css"/>">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="<c:url value="/resources/plugins/overlayScrollbars/css/OverlayScrollbars.min.css"/>">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="<c:url value="/resources/plugins/daterangepicker/daterangepicker.css"/>">
    <!-- summernote -->
    <link rel="stylesheet" href="<c:url value="/resources/plugins/summernote/summernote-bs4.min.css"/>">
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">

    <!-- Preloader -->
    <div class="preloader flex-column justify-content-center align-items-center">
        <img class="animation__shake" src="<c:url value="/resources/dist/img/AdminLTELogo.png"/>" alt="AdminLTELogo" height="60" width="60">
    </div>

    <jsp:include page="/WEB-INF/tags/header.jsp" />

    <jsp:include page="/WEB-INF/tags/navbar.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <jsp:doBody/>
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
        All rights reserved.
        <div class="float-right d-none d-sm-inline-block">
            <b>Version</b> 3.2.0
        </div>
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="<c:url value="/resources/plugins/jquery/jquery.min.js" />"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<c:url value="/resources/plugins/jquery-ui/jquery-ui.min.js" />"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="<c:url value="/resources/plugins/bootstrap/js/bootstrap.bundle.min.js" />"></script>
<!-- ChartJS -->
<script src="<c:url value="/resources/plugins/chart.js/Chart.min.js" />"></script>
<!-- Sparkline -->
<script src="<c:url value="/resources/plugins/sparklines/sparkline.js" />"></script>
<!-- JQVMap -->
<script src="<c:url value="/resources/plugins/jqvmap/jquery.vmap.min.js" />"></script>
<script src="<c:url value="/resources/plugins/jqvmap/maps/jquery.vmap.usa.js" />"></script>
<!-- jQuery Knob Chart -->
<script src="<c:url value="/resources/plugins/jquery-knob/jquery.knob.min.js" />"></script>
<!-- daterangepicker -->
<script src="<c:url value="/resources/plugins/moment/moment.min.js" />"></script>
<script src="<c:url value="/resources/plugins/daterangepicker/daterangepicker.js" />"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="<c:url value="/resources/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js" />"></script>
<!-- Summernote -->
<script src="<c:url value="/resources/plugins/summernote/summernote-bs4.min.js" />"></script>
<!-- overlayScrollbars -->
<script src="<c:url value="/resources/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js" />"></script>
<!-- AdminLTE App -->
<script src="<c:url value="/resources/dist/js/adminlte.js"/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value="/resources/dist/js/demo.js"/>"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<c:url value="/resources/dist/js/pages/dashboard.js"/>"></script>
</body>
</html>
