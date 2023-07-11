<html>
<head>
    <title>Users List</title>
</head>
<body>
    <form action="addUser" method="POST" >
       <#-- <input type="hidden"  name="_csrf" value="${_csrf.token}"/>-->
        用户名：<input type="text" name="nickname" /><br>
        账号：  <input type="text" name="username" /><br>
        密码：  <input type="text" name="password" /><br>
               <#list listRole as role><#-- checked="checked"-->
                   <input type="checkbox" name="role" value="${role.id}" />${role.name}
               </#list>
        <br><input type="submit" value="submit"/><br>
    </form>
</body>
</html>

