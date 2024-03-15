package com.caiobruno.payments.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@Builder
@Document(collection = "payments")
public class Payment {

    private String id;
    private String idProduct;
    private String idUser;
    private Double value;
    private String paymentMethod;
    private LocalDate transactionDate;
    private String status;

}
