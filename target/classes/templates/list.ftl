<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Users List</title>
    <#assign security=JspTaglibs["http://www.springframework.org/security/tags"] />
</head>
<body>
    <@security.authorize access="hasRole('addUser')">
        <button><a href="addUserPage">添加用户</a></button>
    </@security.authorize>

    <@security.authorize access="hasRole('getRoleAll')">
        <button><a href="role/getRoleList">角色列表</a></button><br>
    </@security.authorize>

        <table class="table table-hover" border="1">
            <tr>
                <th>id</th>
                <th>nickname</th>
                <th>username</th>
                <th>password</th>
                <th>createTime</th>
                <th>lastLoginTime</th>
                <th>status</th>
                <@security.authorize access="hasRole('updateUser')">
                    <th></th>
                </@security.authorize>
                <@security.authorize access="hasRole('deleteUser')">
                    <th></th>
                </@security.authorize>
                </tr>
            <#list userList as user>
                <tr>
                    <td>${user.id}</td>
                    <td>${user.nickname}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.createTime}</td>
                    <td>${user.lastLoginTime}</td>
                    <td>${user.status}</td>
                    <@security.authorize access="hasRole('updateUser')">
                        <th><button><a href="updateGetUser?id=${user.id}">修改</a></button></th>
                    </@security.authorize>
                    <@security.authorize access="hasRole('deleteUser')">
                        <th><button><a href="deleteUser?id=${user.id}">删除</a></button></th>
                    </@security.authorize>
                </tr>
            </#list>
        </table><br>
    <button><a href="logout">退出登录</a></button><br>
</body>
</html>

