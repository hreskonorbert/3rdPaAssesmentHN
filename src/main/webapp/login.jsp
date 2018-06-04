<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>

<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/profile.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>

<body>

<input type="hidden" id="userId" value="${user.id}">
<div id="loginDiv" class ="content">
    <h1>Login</h1>

    <h3>Employee Login</h3>
    <input type="text" id="employeeId" placeholder="EmployeeId">

    <h3>Supplier Login<h3>
    <br>
    <input type="text" id="supplierId" placeholder="SupplierId">

    <button id="loginButton">Login</button>
</div>

<div id="profileDiv" class="content hidden">

<button id="territories">All territories</button>
<br>
<br>
<div id="employeeOnly" class="content hidden">
<button id="subordinates">subordinates</button>
<br>
<br>
<button id="myTerritories">My territories</button>
<br>
<br>
<button id="addNewTerritory">Add new territory</button>
</div>

</div>

<div id="territoriesDiv" class="content hidden">
    <table id="territoryTable">
    </table>
</div>

<div id="territoryAddingDiv" class = "content hidden">
<input type="text" id="territoryNameField" placeholder="Territory ID">
<button id="sendTerritory">Add</button>
</div>

<div id="subordinatesDiv" class="content hidden">
    <table id="subordinateTable">
    </table>
</div>

<div id="myTerritoriesDiv" class="content hidden">
    <table id="myTerritoryTable">
    </table>
</div>




</body>
</html>




