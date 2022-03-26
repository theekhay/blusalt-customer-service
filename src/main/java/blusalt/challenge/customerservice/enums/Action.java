package blusalt.challenge.customerservice.enums;


import lombok.Getter;

@Getter
public enum Action {
    DR("DR", "Debit"),
    CR("CR", "Credit");

    private String code;
    private String description;

    Action(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
