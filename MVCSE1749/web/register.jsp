<%-- 
    Document   : register
    Created on : 03-Mar-2023, 14:37:51
    Author     : Toan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Create new account</h1>
        <form action="MasterController" method="POST">
            Username* <input type="text" name="txtUsername" value="" size="20" /> (6 - 20 chars)<br/>
            Password* <input type="password" name="txtPassword" value="" size="30" /> (6 - 30 chars)<br/>
            Confirm* <input type="password" name="txtConfirm" value="" size="30" /><br/>
            Full Name* <input type="text" name="txtFullname" value="" size="50" />(2 - 50 chars)<br/>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
