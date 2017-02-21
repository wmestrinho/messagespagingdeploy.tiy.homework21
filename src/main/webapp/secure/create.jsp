<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WagnerMestrinho
  Date: 2/20/17
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">

        </div>
        <div class="col-sm-8 text-left">

            <c:if test="${msg_success}">
                <div class="alert alert-success">
                    <strong>Success!</strong>
                </div>
            </c:if>
            <h1>Create New User</h1>
            <form class="form-horizontal" action="/create" method="post">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="name">Name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" placeholder="userName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="pwd">Password:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="pwd" name="pwd" placeholder="password">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="quantity">Quantity:</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="quantity" name="quantity" placeholder="Enter quantity">
                    </div>
                </div>
                <div class="form-group">
                    <label for="picFile">Profile Image:</label>
                    <input type="file" class="form-control" id="picFile" name="picture">
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Create</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-sm-2 sidenav">

        </div>
    </div>
</div>

</body>
</html>
