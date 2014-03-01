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
        <div class="container theme-showcase" role="main">
        <div class="jumbotron">
            <h1>Hello, world!</h1>
            <p>This is a template for a simple marketing or informational website. It includes a large callout called a jumbotron and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
            <p><a href="#" class="btn btn-primary btn-lg" role="button">Learn more &raquo;</a></p>
        </div>
            <g:layoutBody/>
        </div>
        <r:layoutResources/>
    </body>
</html>