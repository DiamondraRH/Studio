<%--
  Created by IntelliJ IDEA.
  User: tsiry
  Date: 13/03/2023
  Time: 09:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:layout>
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Planning</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col col-lg-12">
                    <div class="card">
                        <div class="card-body">
                            <div id='calendar'></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script type="text/javascript">
        var events=${events};
    </script>
    <script src='<c:url value="/resources/dist/js/fullcalendar/dist/index.global.js" />'></script>
    <script src='<c:url value="/resources/dist/js/fullcalendar/script/planning.js" />'></script>
</z:layout>