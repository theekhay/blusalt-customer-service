package blusalt.challenge.customerservice.models.billingservice;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FundRequest {

    private long customerId;
    private BigDecimal amount;
    private String currency;
    private String clientReference;
    private String channel;

}
