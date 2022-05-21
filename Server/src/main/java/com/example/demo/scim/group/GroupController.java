package com.example.demo.scim.group;

import com.example.demo.scim.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping(path = "groups")
    public Object createGroup(@RequestBody Group group, @AuthenticationPrincipal User user) {
        return groupService.saveGroup(group, user);
    }

    @GetMapping(path = "groups")
    public List<Group> listAllGroups() {
        return groupService.listAllGroups();
    }

    @GetMapping(path = "groups/{id}")
    public Object getGroupById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(groupService.findGroupById(id), HttpStatus.OK);
        } catch (
                UsernameNotFoundException e
        ) {
            return e.getMessage();
        }
    }

    @DeleteMapping(path = "groups/{id}")
    public Object deleteGroupById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        try {
            return new ResponseEntity<>(groupService.deleteGroupById(user, id), HttpStatus.OK);
        } catch (
                UsernameNotFoundException | AuthorizationServiceException e
        ) {
            return e.getMessage();
        }
    }
}
