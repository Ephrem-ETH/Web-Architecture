<%-- 
    Document   : index
    Created on : Dec 6, 2020, 10:22:12 PM
    Author     : Ephrem
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>See Balance Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Welcome to our banking system!</h1>
        <form action="RemoteClientServlet" name="welcome" method="post" >
            What do you want to do?
            <br>
            <br/>
            <input id="btn_getMoney" type="submit" name="btn_see" value="See Balance">
            <span> </span>
            <input  id="btn_deposit" type="submit" name="btn_deposit" value="Deposit">
            <span> </span>
            <input id="btn_withdraw" type="submit" name="btn_withdraw" value="Withdraw">
        </form>
        <p> Your total balance is : ${balance}</p>
    </body>
</html>
