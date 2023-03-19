<%--
  Created by IntelliJ IDEA.
  User: Diamondra
  Date: 10/03/2023
  Time: 10:11
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
                    <h1>Simple Tables</h1>
                </div>
                <div class="col-sm-6">
                    <ol class="breadcrumb float-sm-right">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item active">Simple Tables</li>
                    </ol>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="container-fluid">
            <!-- /.row -->
            <div class="row">
                <div class="col-12">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="card-title">Tous les projets</h3>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body table-responsive p-0">
                            <table class="table table-bordered table-hover text-nowrap">
                                <thead>
                                <tr>
                                    <th>Projet</th>
                                    <th>Début de production</th>
                                    <th>Fin de production</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${projets}" var="projet">
                                <tr>
                                    <td>${projet.titre}</td>
                                    <td>${projet.debutProduction}</td>
                                    <td>${projet.finProduction}</td>
                                    <td><a href="<c:url value="/scenes/schedule"/>"><button type="button" class="btn btn-block btn-info">Planifier</button></a></td>
                                    <td><a href="<c:url value="/projets/planning/${projet.idProjet}"/> "><button type="button" class="btn btn-block btn-success">Voir planning</button></a></td>
                                    <td><a href=""><button type="button" class="btn btn-block btn-secondary">Planifier</button></a></td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                    </div>
                    <!-- /.card -->
                </div>
            </div>
            <!-- /.row -->
        </div><!-- /.container-fluid -->
    </section>
</z:layout>