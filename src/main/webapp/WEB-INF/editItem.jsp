<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="../error.jsp" isErrorPage="false" %>

<t:fourthingsplus_template>
    <jsp:attribute name="header">
         Welcome to the logged in area
    </jsp:attribute>

    <jsp:body>


        <h3>Edit item name</h3>

        <form method="post">
            <input style="width: 300px" type="text" name="name" value="${requestScope.item.name}"/>
            <button formaction="updateitem" name="item_id" value="${requestScope.item.id}">Update item</button>
        </form>


    </jsp:body>

</t:fourthingsplus_template>