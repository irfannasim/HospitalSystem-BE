package com.sd.his.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PERMISSION")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "DESCRIPTION", length = 1024)
    private String description;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default false", nullable = false)
    private boolean isActive;

    @Column(name = "IS_DELETED", columnDefinition = "boolean default false", nullable = false)
    private boolean isDeleted;

    @Column(name = "CREATED_ON")
    private long createdOn;

    @Column(name = "UPDATED_ON")
    private long updatedOn;

    public Permission() {
    }

    public Permission(String name, String description, boolean isActive, boolean isDeleted, long createdOn, long updatedOn, List<Role> roles) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.roles = roles;
    }

    @ManyToMany(mappedBy = "permissions")
    @JsonBackReference
    private List<Role> roles;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
