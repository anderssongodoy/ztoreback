package pe.idat.ztore.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;

public enum Role {

    ROLE_USER ("User"),
    ROLE_ADMIN ("Admin");

    @Column(name = "name")
    private String name;
    Role(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
