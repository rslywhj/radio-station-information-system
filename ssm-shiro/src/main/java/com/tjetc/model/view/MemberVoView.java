package com.tjetc.model.view;

import java.util.List;

public class MemberVoView {
    private String username;
    private List<String> permissionNames;

    public MemberVoView() {
    }

    public MemberVoView(String username, List<String> permissionNames) {
        this.username = username;
        this.permissionNames = permissionNames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getPermissionNames() {
        return permissionNames;
    }

    public void setPermissionNames(List<String> permissionNames) {
        this.permissionNames = permissionNames;
    }
}
