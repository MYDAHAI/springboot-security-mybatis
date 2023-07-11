<html>
<head>
    <title>Users List</title>
    <script src="/webjars/jquery/3.4.0/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        var us = ${us};
        var checkSelected = $("input:checkbox[name='role']");
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
    <form action="updateUser" method="POST" >
        <#--<input type="hidden"  name="_csrf" value="${_csrf.token}"/>-->
        <input type="hidden" name="id" value="${user.id}" />
        用户名：<input type="text" name="nickname" value="${user.nickname}" /><br>
        账号：  <input type="text" name="username" value="${user.username}" /><br>
        密码：  <input type="text" name="password" value="${user.password}" /><br>
               <#list roles as role><#-- checked="checked"-->
                     <input type="checkbox" name="role" value="${role.id}" />${role.name}
               </#list>
               <#if us == "true">
                   <#list userRoleList as userRole>
                       <input type="hidden" name="userRole" value="${userRole.rid}" />
                   </#list>
               </#if>
        <br><input type="submit" value="submit"/><br>
    </form>
</body>
</html>

