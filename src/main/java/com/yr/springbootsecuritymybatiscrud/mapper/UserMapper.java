package com.yr.springbootsecuritymybatiscrud.mapper;

import com.yr.springbootsecuritymybatiscrud.entity.UPermission;
import com.yr.springbootsecuritymybatiscrud.entity.URole;
import com.yr.springbootsecuritymybatiscrud.entity.UUser;
import com.yr.springbootsecuritymybatiscrud.entity.UUserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UUser> getUserAll();

    UUser getUserName(String username);

    int addUser(UUser user);

    List<UPermission> getUserPermission(String username);

    List<URole> getRoleAll();

    void addUserRole(Integer userId,Integer roleId);

    void deleteUser(Integer id);

    List<UUserRole> getUUserRoleId(Integer uId);

    void deleteUUserRole(Integer uId);

    UUser getUserId(Integer id);

    void updateUser(UUser user);

    String getPermissionByUrlMethod(String url,String method);

    int addRole(URole role);

    List<UPermission> getPermissionAll();

    void addRolePermission(Integer rid,Integer pid);

    void deleteRole(Integer id);

    void deleteRolePermission(Integer rid);

    int getRolePermission(Integer rid);

    URole getRoleId(Integer id);

    List<Integer> getRolePermissionId(Integer rid);

    void updateRole(URole role);

}
