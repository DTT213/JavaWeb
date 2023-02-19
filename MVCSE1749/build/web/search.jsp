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
        <h1>Search Page</h1>
        <form action="MasterController">
            Search Text<input type="text" name="txtSearchValue" value="<%= request.getParameter("txtSearchValue") %>" /><br/>
            <input type="submit" value="Search" name="btAction" />
        </form> <br/>
        <% 
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue!=null) { 
                List<LoginDTO> result = (List<LoginDTO>)
                        request.getAttribute("SEARCH_RESULT");
                if (result!=null) {
                    %>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Username</th>
                                <th>Password</th>
                                <th>Full Name</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                int count=0;
                                for (LoginDTO dto : result){
                                    %>
                            <tr>
                                <td>
                                    <%= ++count %>
                                .</td>
                                <td> 
                                    <%= dto.getUsername() %>
                                </td>
                                <td>
                                    <%= dto.getPassword() %>
                                </td>
                                <td>
                                    <%= dto.getFullname() %>
                                </td>
                                <td>
                                    <%= dto.isRole() %>
                                </td>
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
