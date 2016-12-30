package com.lms.controller;

import com.lms.entity.Group;
import com.lms.entity.GroupOfUser;
import com.lms.entity.User;
import com.lms.service.GroupService;
import com.lms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuwantha on 12/28/16.
 */

@Controller
public class GroupController {
    @Autowired
    GroupService groupService;

    @Autowired
    UserService userService;

    @RequestMapping("/users/groups")
    public String userGroups(Model model, Principal principal){
        List<User> users=userService.getAllUsers();
        User user = userService.getUserByName(principal.getName());
        List<Group> groups = groupService.getGroups(user.getUserId());

        model.addAttribute("user",user);
        model.addAttribute("users",users);
        model.addAttribute("groups",groups);
        model.addAttribute("usersOfGroup",null);
        model.addAttribute("groupName",null);
        model.addAttribute("groupCount",groups.size());
        model.addAttribute("flag",null);


        return "usergroups";
    }

    @RequestMapping(value = "/users/groups/create/{groupName}")
    public String createUserGroup(Model model,@PathVariable("groupName") String groupName, Principal principal){
        List<User> users=userService.getAllUsers();
        User user = userService.getUserByName(principal.getName());
        Group group= new Group();
        group.setGroupLederId(user.getUserId());
        group.setGroupName(groupName);
        System.out.println("before added group");
        groupService.addNewGroup(group, new ArrayList<GroupOfUser>());
        System.out.println("group is added");
        List<Group> groups = groupService.getGroups(user.getUserId());
        List<User> usersOfGroup = groupService.getUsersOfGroup(group.getGroupId());
        model.addAttribute("user",user);
        model.addAttribute("users",users);
        model.addAttribute("groups",groups);
        model.addAttribute("groupName",groupName);
        model.addAttribute("usersOfGroup",usersOfGroup);
        model.addAttribute("groupCount",groups.size());
        model.addAttribute("flag","created");

        return "usergroups";
    }

    @RequestMapping("/users/groups/{groupName}")
    public String userGroups(Model model,@PathVariable("groupName") String groupName, Principal principal){
        List<User> users=userService.getAllUsers();
        User user = userService.getUserByName(principal.getName());
        List<Group> groups = groupService.getGroups(user.getUserId());
        Group group = groupService.getGroup(groupName);
        List<User> usersOfGroup = groupService.getUsersOfGroup(group.getGroupId());

        model.addAttribute("user",user);
        model.addAttribute("users",users);
        model.addAttribute("groups",groups);
        model.addAttribute("groupName",groupName);
        model.addAttribute("usersOfGroup",usersOfGroup);
        model.addAttribute("groupCount",groups.size());
        model.addAttribute("flag",null);

        return "usergroups";
    }
}
