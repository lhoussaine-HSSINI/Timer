<%@ page import="MVC.Dao.operationE" %>
<%@ page import="MVC.Model.ExpirationEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="MVC.Dao.All_op_timer" %>
<%@ page import="java.text.ParseException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</head>

<%
    operationE op = new operationE();
    op.start();
    List<ExpirationEntity> list_experation = op.getAll_Expiration_date();

%>

<body>

<br/>


<h2>ajouter temp</h2>
<form action="ControllerExpiration" method="post">
    <label>
        <input type="number" name="dateExpiration">
    </label>
    <input type="submit" value="add">
</form>

<h2>all Expiration date</h2>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">DateExpiration</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(ExpirationEntity e : list_experation) {
        %>

        <tr scope="row">
            <td><%=e.getId()%></td>
            <td><%=e.getDateExpiration()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>


</body>
</html>