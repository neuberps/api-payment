package com.caiobruno.payments.domain.dto;

import com.caiobruno.payments.domain.enums.PaymentMethod;
import com.caiobruno.payments.domain.enums.StatusPayment;
import com.caiobruno.payments.domain.model.Payment;
import com.mongodb.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentDTO {

    private String id;

    @NotBlank @NonNull
    private String idProduct;

    @NotBlank @NonNull
    private String idUser;

    @NotBlank @NonNull
    private BigDecimal value;

    @NotBlank @NonNull
    private PaymentMethod paymentMethod;

    @NonNull
    private LocalDateTime transactionDate;

    @NotBlank @NonNull
    private StatusPayment status;

    public PaymentDTO (Payment entity){
        BeanUtils.copyProperties(entity, this);
    }
}
