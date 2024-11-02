package ru.pkozlov.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MoneyTransferRequest {
    @JsonProperty("userId")
    private Long receiverUserId;
    @JsonProperty("value")
    private BigDecimal amount;
}
