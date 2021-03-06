package com.github.vilfenox.anketa.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE)),
    ADMIN(Set.of(Permission.DEVELOPERS_READ, Permission.DEVELOPERS_WRITE,
                  Permission.DEVELOPERS_EDIT, Permission.DEVELOPERS_DELETE));

    private final Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissionSet() {
        return permissionSet;
    }
    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermision()))
                .collect(Collectors.toSet());
    }
}
