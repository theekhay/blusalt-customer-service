package blusalt.challenge.customerservice.enums;


import lombok.Getter;

@Getter
public enum ResponseStatus {

    SUCCESS("00"),
    FAILED("99");

    private String code;

    ResponseStatus(String code) {
        this.code = code;
    }
}
