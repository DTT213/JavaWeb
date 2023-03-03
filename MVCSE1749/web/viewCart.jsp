<%-- 
    Document   : viewCart
    Created on : 28-Feb-2023, 13:50:25
    Author     : Toan
--%>

<%@page import="java.util.Map"%>
<%@page import="toandt.cart.CartObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <h1>Book Store</h1>

        <%
            //1. customer goes to cart place
            if (session != null) {
                //2. Customer take the cart;
                CartObj cart = (CartObj) session.getAttribute("CART");
                if (cart != null) {
                    //3. customer get items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) { %>
        <form action="MasterController">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (String key : items.keySet()) {
                    %>
                    <tr>
                        <td>
                            <%= ++count%>
                        </td>
                        <td>
                            <%= key%>
                        </td>
                        <td>
                            <%= items.get(key)%>
                        </td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= key %>"/>
                        </td>
                    </tr>
                    <%
                        }//traverse map based on key
                    %>
                    <tr>
                        <td colspan="3">
                            <a href="shopping.html"> Add more book to your cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Item" name="btAction" />
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%
                        return;
                    }
                }
            }//end session has existed
        %>

        <h2>
            No cart is existed
        </h2>
    </body>
</html>
