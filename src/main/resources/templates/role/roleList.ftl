<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
</head>
<body>
    <@security.authorize access="hasRole('addRole')">
        <button><a href="addRolePage">添加角色</a></button>
    </@security.authorize>

    <@security.authorize access="hasRole('getUserAll')">
        <button><a href="${request.contextPath }/getUserAll">用户列表</a></button>
    </@security.authorize>
    <br>
        <table class="table table-hover" border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>type</th>
                <@security.authorize access="hasRole('updateRole')">
                    <th></th>
                </@security.authorize>
                <@security.authorize access="hasRole('deleteRole')">
                    <th></th>
                </@security.authorize>
                </tr>
            <#list roleList as role>
                <tr>
                    <td>${role.id}</td>
                    <td>${role.name}</td>
                    <td>${role.type}</td>
                    <@security.authorize access="hasRole('updateRole')">
                        <th><button><a href="updateGetRole?id=${role.id}">修改</a></button></th>
                    </@security.authorize>
                    <@security.authorize access="hasRole('deleteRole')">
                        <th><button><a href="deleteRole?id=${role.id}">删除</a></button></th>
                    </@security.authorize>
                </tr>
            </#list>
        </table><br>
    <button><a href="/logout">退出登录</a></button><br>
</body>
</html>

