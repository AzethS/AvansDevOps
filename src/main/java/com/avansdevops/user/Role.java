package com.avansdevops.user;

public enum Role {
    DEVELOPER,
    LEAD_DEVELOPER,
    SCRUM_MASTER,
    PRODUCT_OWNER,
    TESTER,
    UNKNOWN;

    public boolean isDeveloper() {
        return this == DEVELOPER || this == LEAD_DEVELOPER;
    }
}
