<%@ page import="application.models.Scene" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="z" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<z:layout>
    <section class="content-header">
        <div class="container-fluid">
            <div class="row mb-2">
                <div class="col-sm-6">
                    <h1>Valider un planning</h1>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <section class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-6">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">Le planning</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <form action="${pageContext.request.contextPath}/validerPlanning/" method="post">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Scenes</th>
                                    <th>Plateau</th>
                                    <th>Debut tournage</th>
                                    <th>Fin tournage</th>
                                </tr>
                                </thead>

                                <tbody>
                                <c:forEach items="${scenes}" var="scene" varStatus="loop">
                                    <tr>
                                        <td>
                                            <div>
                                                <label class="flex items-center dark:text-gray-400">
                                                    <input
                                                            id="check-${loop.index}"
                                                            type="checkbox"
                                                            checked
                                                            value="${scene.idScene};${scene.debutTournage};${scene.finTournage}"
                                                            name="scenes"
                                                            class="text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray"
                                                    />
                                                    <span class="ml-2 text-sm font-semibold">
                                                            ${scene.titre}
                                                    </span>
                                                </label>
                                            </div>
                                        </td>
                                        <td>
                                                ${scene.plateau.lieu} (N°${scene.plateau.numero})
                                        </td>
                                        <td>
                                                ${scene.debutTournage}
                                        </td>
                                        <td>
                                                ${scene.finTournage}
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                                <div class="w-full overflow-hidden rounded-lg shadow-xs">
                                    <div class="w-full overflow-x-auto">

                                    </div>
                                </div>
                                <div class="py-4">
                                    <button type="submit"
                                            class="btn btn-block btn-primary btn-sm"
                                    >
                                        Valider
                                    </button>
                                    <button type="button" class="btn btn-block btn-success btn-sm"
                                            onclick="checkAll()"
                                    >
                                        Sélectionner tout
                                    </button>
                                    <button type="button" class="btn btn-block btn-danger btn-sm"
                                            onclick="uncheckAll()"
                                    >
                                        Déselectionner tout
                                    </button>
                                </div>
                            </form>
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>

                <div class="col-lg-6">
                    <div class="card">
                            <%--<div class="card-header">
                                <h3 class="card-title">Le planning</h3>
                            </div>--%>
                        <!-- /.card-header -->
                        <div class="card-body">
                            <div id="mini-calendar" <%--style="width: 600px;margin: 0 20px 20px 0;"--%>></div>
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>
            </div>
        </div>
    </section>


    <script type="text/javascript">
        <%--var nbScenes=${scenes.size()};--%>
        var events =
        ${events}
    </script>
    <script src='<c:url value="/resources/dist/js/fullcalendar/dist/index.global.js" />'></script>
    <script src='<c:url value="/resources/dist/js/fullcalendar/script/mini-calendar.js" />'></script>
</z:layout>