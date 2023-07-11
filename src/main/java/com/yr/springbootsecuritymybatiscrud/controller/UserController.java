package com.yr.springbootsecuritymybatiscrud.controller;

import com.yr.springbootsecuritymybatiscrud.entity.URole;
import com.yr.springbootsecuritymybatiscrud.entity.UUser;
import com.yr.springbootsecuritymybatiscrud.entity.UUserRole;
import com.yr.springbootsecuritymybatiscrud.service.UserService;
import com.yr.springbootsecuritymybatiscrud.utils.DateUtil;
import com.yr.springbootsecuritymybatiscrud.utils.PassWordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/getUserAll")
    public String getUserAll(Map<String,Object> map){
        List<UUser> userList = userService.getUserAll();
        map.put("userList",userList);
        return "list";
    }

    @RequestMapping("/updateUser")
    public String updateUser(Integer id,String nickname,String username,String password,HttpServletRequest request){
        UUser user = new UUser();
        user.setId(id);
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(PassWordEncoder.getEncoderPassword(password));
        user.setLastLoginTime(DateUtil.getTimestamp());

        userService.updateUser(user);//修改用户信息

        List<Integer> listRoleId = new ArrayList<>();
        String[] roles = request.getParameterValues("role");
        if(roles != null){
            for (int i = 0; i < roles.length; i++) {
                Integer roleId = Integer.valueOf(roles[i]);
                listRoleId.add(roleId);
            }
            userService.deleteUUserRole(id);//删除旧的用户角色关联关系
            userService.addUserRole(id,listRoleId);//添加新的用户角色关联
        }else {
            userService.deleteUUserRole(id);//删除旧的用户角色关联关系
        }

        return "redirect:/getUserAll";
    }

    @RequestMapping("/updateGetUser")
    public String updateGetUser(Integer id,Map<String,Object> map){
        System.out.println("进入updateGetUser");
        UUser user = userService.getUserId(id);//查出单个用户
        List<UUserRole> userRoleList = userService.getUUserRoleId(id);//查出这个用户所拥有的角色
        List<URole> role = userService.getRoleAll();//查出所有角色

        if (userRoleList != null){
            map.put("us","true");
            map.put("userRoleList",userRoleList);
        }else {
            map.put("us","false");
        }
        map.put("user",user);
        map.put("roles",role);
        return "update";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Integer id){
        System.out.println("进入deleteUser");
        userService.deleteUser(id);
        return "redirect:/getUserAll";
    }

    @RequestMapping("/addUserPage")
    public String addPage(Map<String,Object> map){
        List<URole> roleList = userService.getRoleAll();
        map.put("listRole",roleList);
        return "add";
    }

    @RequestMapping("/addUser")
    public String addUser(String nickname,String username,String password,HttpServletRequest request){
        UUser user = new UUser();
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(PassWordEncoder.getEncoderPassword(password));
        user.setCreateTime(DateUtil.getTimestamp());
        user.setLastLoginTime(DateUtil.getTimestamp());

        int id = userService.addUser(user);//添加用户信息

        List<Integer> listRoleId = new ArrayList<>();
        String[] roles = request.getParameterValues("role");
        if(roles != null){
            for (int i = 0; i < roles.length; i++) {
                Integer roleId = Integer.valueOf(roles[i]);
                listRoleId.add(roleId);
            }
            userService.addUserRole(id,listRoleId);//添加用户角色关联
        }

        return "redirect:/getUserAll";
    }

}
