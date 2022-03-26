package blusalt.challenge.customerservice.models.billingservice;


import blusalt.challenge.customerservice.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundResponse {

    private ResponseStatus status;
    private String message;
}
