<%-- 
    Document   : search
    Created on : 16-Feb-2023, 15:57:50
    Author     : Toan
--%>

<%@page import="toandt.login.LoginDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <%
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                Cookie lastCookie = cookies[cookies.length - 1];
                String username = lastCookie.getName();
        %>
        <font color="red">
        Welcome, <%= username%>
        </font>
        <%
            }
        %>
        <h1>Search Page</h1>
        <form action="MasterController">
            Search Text<input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue")%>" />
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>
        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) {
                List<LoginDTO> result = (List<LoginDTO>) request.getAttribute("SEARCH_RESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Full Name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 0;
                    for (LoginDTO dto : result) {
                        String urlRewritting = "MasterController"
                                + "?btAction=Delete"
                                + "&pk=" + dto.getUsername()
                                + "&lastSearchValue=" + searchValue;
                %>
                <tr>
            <form action="MasterController">
                <td>
                    <%= ++count%>
                    .</td>
                <td> 
                    <%= dto.getUsername()%>
                    <input type="hidden" name="pk" value="<%= dto.getUsername()%>" />
                </td>
                <td>
                    <input type="text" name="password" value="<%= dto.getPassword()%>" />
                </td>
                <td>
                    <%= dto.getFullname()%>
                </td>
                <td>
                    <input type="checkbox" name="chkAdmin" value="Admin" <%if (dto.isRole())%>checked="checked"<%%>/>
                </td>
                <td>
                    <a href="<%=urlRewritting%>">Delete</a>
                </td>
                <td>
                    <input type="hidden" name="lastSearchValue" value="<%=searchValue%>" />
                    <input type="submit" value="Update" name="btAction" />
                </td>
            </form>
        </tr>
        <%
            }//end traverse dto
        %>
    </tbody>
</table>

<%
} else {
%> 
<h2>
    No record is matched!!!
</h2>
<%
        }
    } //end search form has not trigger

%>
</body>
</html>
