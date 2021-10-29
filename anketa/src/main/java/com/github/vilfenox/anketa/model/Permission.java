package com.github.vilfenox.anketa.model;

public enum Permission {
   DEVELOPERS_READ("developers:read"),
   DEVELOPERS_WRITE("developers:write");

    private final String permision;

    Permission(String permision) {
        this.permision = permision;
    }
    public String getPermision(){
        return permision;
    }
}
