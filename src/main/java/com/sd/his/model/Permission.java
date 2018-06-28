package com.sd.his.model;

import com.sd.his.request.RoleAndPermissionCreateRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "PERMISSION")
public class Permission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_SYS_DEFAULT", columnDefinition = "boolean default true", nullable = false)
    private boolean sysDefault;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true", nullable = false)
    private boolean active;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean deleted;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    @OneToMany(targetEntity = UserPermission.class, mappedBy = "permission", fetch = FetchType.LAZY)
    private List<UserPermission> users;

    @OneToMany(targetEntity = RolePermission.class, mappedBy = "permission", fetch = FetchType.LAZY)
    private List<RolePermission> roles;

    public Permission() {
    }

    public Permission(RoleAndPermissionCreateRequest permission) {
        this.name = permission.getName();
        this.description = permission.getDescription();
        this.active = permission.isActive();
        this.createdOn = permission.getCreatedOn();
        this.updatedOn=permission.getUpdatedOn();
    }

    public boolean isSysDefault() {
        return sysDefault;
    }

    public void setSysDefault(boolean sysDefault) {
        this.sysDefault = sysDefault;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(long createdOn) {
        this.createdOn = createdOn;
    }

    public long getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(long updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<UserPermission> getUsers() {
        return users;
    }

    public void setUsers(List<UserPermission> users) {
        this.users = users;
    }

    public List<RolePermission> getRoles() {
        return roles;
    }

    public void setRoles(List<RolePermission> roles) {
        this.roles = roles;
    }
}
