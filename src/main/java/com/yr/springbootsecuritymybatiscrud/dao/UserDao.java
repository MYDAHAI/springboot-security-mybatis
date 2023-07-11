package com.yr.springbootsecuritymybatiscrud.dao;

import com.yr.springbootsecuritymybatiscrud.entity.UPermission;
import com.yr.springbootsecuritymybatiscrud.entity.URole;
import com.yr.springbootsecuritymybatiscrud.entity.UUser;
import com.yr.springbootsecuritymybatiscrud.entity.UUserRole;
import com.yr.springbootsecuritymybatiscrud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    //查询所有用户信息
    public List<UUser> getUserAll(){
        return userMapper.getUserAll();
    }

    //根据用户名查出该用户信息
    public UUser getUserName(String username){
        return userMapper.getUserName(username);
    }

    //添加用户
    public int addUser(UUser user){
        userMapper.addUser(user);
        return user.getId();
    }

    //根据用户名查询拥有的所有权限
    public List<UPermission> getUserPermission(String username){
        return userMapper.getUserPermission(username);
    }

    //查出所有角色
    public List<URole> getRoleAll(){
        return userMapper.getRoleAll();
    }

    //添加用户与角色的关联关系
    public void addUserRole(Integer userId,Integer roleId){
        userMapper.addUserRole(userId,roleId);
    }

    //删除用户
    public void deleteUser(Integer id){
        userMapper.deleteUser(id);
    }

    //查询用户是否有角色
    public List<UUserRole> getUUserRoleId(Integer uId){
        System.out.println(userMapper.getUUserRoleId(uId));
        return userMapper.getUUserRoleId(uId);
    }

    //删除用户与角色关系
    public void deleteUUserRole(Integer uId){
        userMapper.deleteUUserRole(uId);
    }

    //根据id查询单个用户信息
    public UUser getUserId(Integer id){
        return userMapper.getUserId(id);
    }

    //修改用户信息
    public void updateUser(UUser user){
        userMapper.updateUser(user);
    }

    //根据url与请求类型得到请求的访问权限mark
    public String getPermissionByUrlMethod(String url,String method){
        return userMapper.getPermissionByUrlMethod(url,method);
    }

    //添加角色
    public int addRole(URole role){
        userMapper.addRole(role);
        return role.getId();
    }

    //查询所有权限
    public List<UPermission> getPermissionAll(){
        return userMapper.getPermissionAll();
    }

    //添加角色与权限关联
    public void addRolePermission(Integer rid,Integer pid){
        userMapper.addRolePermission(rid,pid);
    }

    //删除角色
    public void deleteRole(Integer id){
        userMapper.deleteRole(id);
    }

    //删除角色与权限关联
    public void deleteRolePermission(Integer rid){
        userMapper.deleteRolePermission(rid);
    }

    //查询角色是否有权限
    public int getRolePermission(Integer rid){
        return userMapper.getRolePermission(rid);
    }

    //根据id查询单个角色信息
    public URole getRoleId(Integer id){
        return userMapper.getRoleId(id);
    }

    //查出角色所拥有的权限id
    public List<Integer> getRolePermissionId(Integer rid){
        return userMapper.getRolePermissionId(rid);
    }

    //修改角色信息
    public void updateRole(URole role){
        userMapper.updateRole(role);
    }
}
