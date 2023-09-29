package com.thanksen.osahaneat.unit;

public class DataGenerate {
    private String username;
    private String roleName;

    public String getUsername() {
        return username;
    }

    public String getRoleName() {
        return roleName;
    }

    public DataGenerate(String username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }
}
