package com.yr.springbootsecuritymybatiscrud.service;

import com.yr.springbootsecuritymybatiscrud.dao.UserDao;
import com.yr.springbootsecuritymybatiscrud.entity.UPermission;
import com.yr.springbootsecuritymybatiscrud.entity.URole;
import com.yr.springbootsecuritymybatiscrud.entity.UUser;
import com.yr.springbootsecuritymybatiscrud.entity.UUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<UUser> getUserAll(){
        return userDao.getUserAll();
    }

    public UUser getUserName(String username){
        return userDao.getUserName(username);
    }

    public int addUser(UUser user){
        return userDao.addUser(user);
    }

    public List<UPermission> getUserPermission(String username){
        return userDao.getUserPermission(username);
    }

    public List<URole> getRoleAll(){
        return userDao.getRoleAll();
    }

    public void addUserRole(Integer userId,List<Integer> ListRoleId){
        for(Integer roleId : ListRoleId){
            userDao.addUserRole(userId,roleId);
        }
    }

    public void deleteUser(Integer id){
        userDao.deleteUser(id);
        if(getUUserRoleId(id) != null){
            deleteUUserRole(id);
        }
    }

    public List<UUserRole> getUUserRoleId(Integer uId){
        return userDao.getUUserRoleId(uId);
    }

    public void deleteUUserRole(Integer uId){
        userDao.deleteUUserRole(uId);
    }

    public UUser getUserId(Integer id){
        return userDao.getUserId(id);
    }

    public void updateUser(UUser user){
        userDao.updateUser(user);
    }

    public String getPermissionByUrlMethod(String url,String method){
        return userDao.getPermissionByUrlMethod(url,method);
    }

    public int addRole(URole role){
        return userDao.addRole(role);
    }

    public List<UPermission> getPermissionAll(){
        return userDao.getPermissionAll();
    }

    public void addRolePermission(Integer rid,Integer pid){
        userDao.addRolePermission(rid,pid);
    }

    public void deleteRole(Integer id){
        userDao.deleteRole(id);
    }

    public void deleteRolePermission(Integer rid){
        userDao.deleteRolePermission(rid);
    }

    public int getRolePermission(Integer rid){
        return userDao.getRolePermission(rid);
    }

    public URole getRoleId(Integer id){
        return userDao.getRoleId(id);
    }

    public List<Integer> getRolePermissionId(Integer rid){
        return userDao.getRolePermissionId(rid);
    }

    public void updateRole(URole role){
        userDao.updateRole(role);
    }
}
