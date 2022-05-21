package com.example.demo.permissions;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Table(name = "users_roles")
public class UserRoles {
    private long userId;
    private Long roleId;

    public UserRoles() {
    }

    public UserRoles(long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoles userRoles = (UserRoles) o;
        return userId == userRoles.userId && roleId.equals(userRoles.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
