package blusalt.challenge.customerservice.models;

import blusalt.challenge.customerservice.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class ResponseModel<T> {

    private ResponseStatus status;
    private String message;
    private T data;

    public ResponseModel(ResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}