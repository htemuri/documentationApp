package com.example.demo.scim.group;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;
    @ElementCollection(targetClass = Long.class)
    private List<Long> members = new ArrayList<>();

    public Group() {
    }

    public Group(Long id, String groupName, List<Long> members) {
        this.id = id;
        this.groupName = groupName;
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getMembers() {
        return members;
    }

    public void setMembers(List<Long> members) {
        this.members = members;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", user_ids=" + members +
                '}';
    }
}
