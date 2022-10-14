package com.springsecurity.jwtsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@Builder
@Table(name = "USER_ROLE_PERMISSIONS")
@AllArgsConstructor
@NoArgsConstructor
public class UserRolePermission {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "PERMISSION_ID")
    private Long permissionId;

    @Transient
    private String roleName;

    @Transient
    private String permissionName;

    public UserRolePermission(Long id, String roleName, String permissionName) {
        this.id = id;
        this.roleName = roleName;
        this.permissionName = permissionName;
    }
}
