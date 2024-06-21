package com.avansdevops.user;

public enum Role {
    DEVELOPER,
    LEAD_DEVELOPER,
    SCRUM_MASTER,
    PRODUCT_OWNER,
    TESTER,
    UNKNOWN;

    public boolean isDeveloper() { // Complexity 3
        // +2 (2 conditions)
        return this == DEVELOPER || this == LEAD_DEVELOPER;
    }
}
