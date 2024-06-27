package com.flightreservation.model.enums;

public enum Role {
    ADMIN,
    USER;

    public static Role fromString(String roleStr) {
        return Role.valueOfIgnoreCase(roleStr);
    }

    public static Role valueOfIgnoreCase(String value) {
        for (Role role : values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in enum Role");
    }

}
