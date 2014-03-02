<%--
  Created by IntelliJ IDEA.
  User: errr
  Date: 3/1/14
  Time: 3:57 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<g:layoutTitle/>
<r:layoutResources/>
</head>
    <body>
        <g:render template="/layouts/navbar" />
        <div class="page-header">&nbsp;</div>
        <div class="container theme-showcase" role="main">
            <div class="jumbotron">
                <g:layoutBody/>
                <r:layoutResources/>
            </div>
        </div>
        <g:render template="/layouts/footer" />
    </body>
</html>