package com.caiobruno.payments.domain.model;


import com.caiobruno.payments.domain.dto.PaymentDTO;
import com.caiobruno.payments.domain.enums.PaymentMethod;
import com.caiobruno.payments.domain.enums.StatusPayment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
@Document(collection = "payments")
public class Payment {

    private String id;
    private String idProduct;
    private String idUser;
    private BigDecimal value;
    private Integer paymentMethod;
    private String created;
    private String updated;
    private Integer status;

    public Payment (PaymentDTO paymentDTO){
        BeanUtils.copyProperties(paymentDTO, this);
    }
    public Payment() {
        super();
    }

}
