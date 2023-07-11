<html>
<head>
    <title>Users List</title>
    <script src="/webjars/jquery/3.4.0/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        var us = ${us};
        var checkSelected = $("input:checkbox[name='permission']");
        var userRole = $("input:hidden[name='userRole']");
        for(var i=0; i<checkSelected.length; i++){
            if(us == true){
                for (var j = 0; j < userRole.length; j++) {
                    if(userRole[j].value == checkSelected[i].value){
                        $("input:checkbox[value='"+checkSelected[i].value+"']").prop('checked',true);
                    }
                }
            }
        }
    });
</script>
<body>
    <form action="updateRole" method="POST" >
        <#--<input type="hidden"  name="_csrf" value="${_csrf.token}"/>-->
        <input type="hidden" name="id" value="${role.id}" />
        角色名称：<input type="text" name="name" value="${role.name}" /><br>
        角色类型：<input type="text" name="type" value="${role.type}" /><br>
        角色拥有的权限：
               <#list permissionList as permission><#-- checked="checked"-->
                     <input type="checkbox" name="permission" value="${permission.id}" />${permission.name}
               </#list>
               <#if us == "true">
                   <#list rolePermissionId as permissionId>
                       <input type="hidden" name="userRole" value="${permissionId}" />
                   </#list>
               </#if>
        <br><input type="submit" value="submit"/><br>
    </form>
</body>
</html>

