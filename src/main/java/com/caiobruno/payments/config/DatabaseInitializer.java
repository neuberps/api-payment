package com.caiobruno.payments.config;

import com.caiobruno.payments.domain.model.Payment;
import com.caiobruno.payments.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;


@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final PaymentRepository repository;

    public DatabaseInitializer(PaymentRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();


        File file = ResourceUtils.getFile("classpath:payments.json");
        Payment[] payments = mapper.readValue(file, Payment[].class);


        for (Payment payment : payments) {
            repository.save(payment);
        }
    }

}
