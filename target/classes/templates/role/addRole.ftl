<html>
<head>
    <title>Role List</title>
</head>
<body>
    <form action="addRole" method="POST" >
       <#-- <input type="hidden"  name="_csrf" value="${_csrf.token}"/>-->
        角色名称：<input type="text" name="name" /><br>
        角色类型：  <input type="text" name="type" /><br>
        选择角色权限：
               <#list permissionList as permission><#-- checked="checked"-->
                   <input type="checkbox" name="permission" value="${permission.id}" />${permission.name}
               </#list>
        <br><input type="submit" value="submit"/><br>
    </form>
</body>
</html>

