<%--
  Created by IntelliJ IDEA.
  User: dlcsyba
  Date: 2018/1/15
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/public/header.jsp" %>

    <!--calendar css-->
    <link href="plugins/fullcalendar/dist/fullcalendar.css" rel="stylesheet">
    <link href="plugins/select2/dist/css/select2.css" rel="stylesheet" type="text/css">

    <script src="plugins/select2/dist/js/select2.min.js" type="text/javascript"></script>
    <script src="plugins/fullcalendar/dist/fullcalendar.min.js"></script>
    <script src="pages/jquery.fullcalendar.js"></script>

</head>
<body class="fixed-left">
    <%@include file="/public/topBar.html" %>
    <%@include file="/public/leftSidebar.html" %>

    <!-- ============================================================== -->
    <!-- Start right Content here -->
    <!-- ============================================================== -->
    <div class="content-page">
        <!-- Start content -->
        <div class="content">
            <div class="container">

                <!-- Page-Title -->
                <div class="row">
                    <div class="col-sm-12">
                        <h4 class="pull-left page-title">Calendar</h4>
                        <ol class="breadcrumb pull-right">
                            <li><a href="#">Moltran</a></li>
                            <li class="active">Calendar</li>
                        </ol>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">

                        <div class="row">

                            <div class="col-md-9">
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <div id="calendar"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> <!-- end col -->

                            <div class="col-md-3">
                                <div class="widget">
                                    <div class="widget-body">
                                        <div class="row">
                                            <div class="col-md-12 col-sm-12 col-xs-12">
                                                <a href="#" data-toggle="modal" data-target="#add-category" class="btn btn-lg btn-primary btn-block waves-effect waves-light">
                                                    <i class="fa fa-plus"></i> Create New
                                                </a>
                                                <div id="external-events" class="m-t-20">
                                                    <br>
                                                    <p>Drag and drop your event or click in the calendar</p>
                                                    <div class="external-event bg-inverse" data-class="bg-inverse" style="position: relative">
                                                        <i class="fa fa-move"></i>My Event One
                                                    </div>
                                                    <div class="external-event bg-danger" data-class="bg-danger" style="position: relative">
                                                        <i class="fa fa-move"></i>My Event Two
                                                    </div>
                                                    <div class="external-event bg-primary" data-class="bg-primary" style="position: relative">
                                                        <i class="fa fa-move"></i>My Event Three
                                                    </div>
                                                    <div class="external-event bg-purple" data-class="bg-purple" style="position: relative">
                                                        <i class="fa fa-move"></i>My Event Four
                                                    </div>
                                                </div>

                                                <!-- checkbox -->
                                                <div class="checkbox checkbox-primary m-t-40">
                                                    <input id="drop-remove" type="checkbox">
                                                    <label for="drop-remove">
                                                        Remove after drop
                                                    </label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div> <!-- end col-->
                        </div>  <!-- end row -->

                        <!-- BEGIN MODAL -->
                        <div class="modal fade none-border" id="event-modal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"><strong>Add Event</strong></h4>
                                    </div>
                                    <div class="modal-body"></div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-success save-event waves-effect waves-light">Create event</button>
                                        <button type="button" class="btn btn-danger delete-event waves-effect waves-light" data-dismiss="modal">Delete</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal Add Category -->
                        <div class="modal fade none-border" id="add-category">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title"><strong>Add</strong> a category</h4>
                                    </div>
                                    <div class="modal-body">
                                        <form role="form">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label class="control-label">Category Name</label>
                                                    <input class="form-control form-white" placeholder="Enter name" type="text" name="category-name">
                                                </div>
                                                <div class="col-md-6">
                                                    <label class="control-label">Choose Category Color</label>
                                                    <select class="form-control form-white" data-placeholder="Choose a color..." name="category-color">
                                                        <option value="success">Success</option>
                                                        <option value="danger">Danger</option>
                                                        <option value="primary">Primary</option>
                                                        <option value="warning">Warning</option>
                                                        <option value="inverse">Inverse</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-danger waves-effect waves-light save-category" data-dismiss="modal">Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- END MODAL -->
                    </div>
                    <!-- end col-12 -->
                </div> <!-- end row -->

            </div> <!-- container -->

        </div> <!-- content -->

        <footer class="footer text-right">
            2015 © Moltran.
        </footer>

    </div>
    <!-- ============================================================== -->
    <!-- End Right content here -->
    <!-- ============================================================== -->

    <%@include file="/public/rightSidebar.html" %>

    <script type="text/javascript">
        var resizefunc = [];
        $(document).ready(function($) {
            $.CalendarApp.init();
        });
    </script>
</body>

</html>
