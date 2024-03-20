package com.caiobruno.payments.domain.dto;

import com.caiobruno.payments.domain.enums.PaymentMethod;
import com.caiobruno.payments.domain.enums.StatusPayment;
import com.caiobruno.payments.domain.model.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentDTO {

    private String id;

    @NotBlank
    private String idProduct;

    @NotBlank
    private String idUser;

    @NotNull
    private BigDecimal value;


    private Integer paymentMethod;

    private String created;
    private String updated;


    private Integer status;

    public PaymentDTO (Payment entity){
        BeanUtils.copyProperties(entity, this);
    }

}
