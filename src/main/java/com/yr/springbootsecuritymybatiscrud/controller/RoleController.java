package com.yr.springbootsecuritymybatiscrud.controller;

import com.yr.springbootsecuritymybatiscrud.entity.UPermission;
import com.yr.springbootsecuritymybatiscrud.entity.URole;
import com.yr.springbootsecuritymybatiscrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getRoleList")
    public String roleList(Map<String,Object> map){
        List<URole> roleList = userService.getRoleAll();
        map.put("roleList",roleList);
        return "role/roleList";
    }

    @RequestMapping("/addRolePage")
    public String addRolePage(Map<String,Object> map){
        List<UPermission> permissionList = userService.getPermissionAll();
        map.put("permissionList",permissionList);
        return "role/addRole";
    }

    @RequestMapping("/addRole")
    public String addRole(String name, String type, HttpServletRequest request){
        URole role = new URole();
        role.setName(name);
        role.setType(type);
        int roleId = userService.addRole(role);//添加角色，返回角色id

        List<Integer> listPermissionId = new ArrayList<>();
        String[] permission = request.getParameterValues("permission");
        if(permission != null){
            for (int i = 0; i < permission.length; i++) {
                Integer permissionId = Integer.valueOf(permission[i]);
                listPermissionId.add(permissionId);
            }

            for (int i = 0; i < listPermissionId.size(); i++) {
                userService.addRolePermission(roleId,listPermissionId.get(i));//添加角色与权限关联
            }
        }
        return "redirect:/role/getRoleList";
    }

    @RequestMapping("/deleteRole")
    public String deleteRole(Integer id){
        userService.deleteRole(id);//删除角色
        int sum = userService.getRolePermission(id);//查询角色是否有权限
        if(sum > 0){
            userService.deleteRolePermission(id);//删除角色与权限关联关系
        }
        return "redirect:/role/getRoleList";
    }

    @RequestMapping("/updateGetRole")
    public String updateGetRole(Integer id,Map<String,Object> map){
        URole role = userService.getRoleId(id);//查出单个角色信息
        List<UPermission> permissionList = userService.getPermissionAll();//查出所有权限信息
        List<Integer> rolePermissionId = userService.getRolePermissionId(id);//查出角色所拥有的权限id

        if (rolePermissionId != null){
            map.put("us","true");
            map.put("rolePermissionId",rolePermissionId);
        }else {
            map.put("us","false");
        }

        map.put("permissionList",permissionList);
        map.put("role",role);
        return "role/updateRole";
    }

    @RequestMapping("/updateRole")
    public String updateRole(URole role,HttpServletRequest request){
        userService.updateRole(role);//修改角色信息
        userService.deleteRolePermission(role.getId());//删除角色与权限旧关联
        String[] permissionsId = request.getParameterValues("permission");
        List<Integer> listPermissionId = new ArrayList<>();
        if(permissionsId != null){
            for (int i = 0; i < permissionsId.length; i++) {
                Integer permissionId = Integer.valueOf(permissionsId[i]);
                listPermissionId.add(permissionId);
            }

            for (int i = 0; i < listPermissionId.size(); i++) {
                userService.addRolePermission(role.getId(),listPermissionId.get(i));//添加角色与权限关联
            }
        }

        return "redirect:/role/getRoleList";
    }

}
