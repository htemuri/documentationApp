package com.example.demo.scim.group;

import com.example.demo.scim.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Object saveGroup(Group group, @AuthenticationPrincipal User user) {
        List<Long> groupMembers = group.getMembers();
        if (!groupMembers.contains(user.getId())) {
            groupMembers.add(user.getId());
        }
        return groupRepository.save(new Group(
                group.getId(),
                group.getGroupName(),
                groupMembers
        ));
    }

    public Optional<Group> findGroupById(Long id) {
        Optional<Group> response = groupRepository.getGroupById(id);
        if (response.isPresent()) {
            return response;
        } else {
            throw new UsernameNotFoundException("Group with Id: "+id+" not found.");
        }
    }

    public List<Group> listAllGroups() {
        return groupRepository.findAll();
    }

    @Transactional
    public String deleteGroupById(@AuthenticationPrincipal User user, Long id) {
        Optional<Group> group = groupRepository.getGroupById(id);
        if (group.isEmpty()) {
            throw new UsernameNotFoundException("Group with ID: "+id+" not found");
        }
        List<Long> members = group.get().getMembers();
        if ((user.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ADMIN")))
                || (members.contains(user.getId()))) {
            String status = "Deleted";
            Optional<Object> statusDeleted = groupRepository.deleteUserById(id);
            return status;
        } else {
            throw new AuthorizationServiceException("Unauthorized");
        }
    }
}
