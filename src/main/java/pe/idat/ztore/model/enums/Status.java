package pe.idat.ztore.model.enums;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

	ACCEPTED ("Accepted"),
    TO_PAY ("To pay"),
    PAYED ("Payed");

    @Column(name = "name")
    private String name;

    Status(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
