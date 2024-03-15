package com.caiobruno.payments.service;

import com.caiobruno.payments.domain.dto.PaymentDTO;
import com.caiobruno.payments.domain.model.Payment;
import com.caiobruno.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    public PaymentDTO created(Payment entity) {
        entity.setTransactionDate(LocalDateTime.now());
        Payment payment =  repository.save(entity);
        return new PaymentDTO(payment);
    }
    public List<PaymentDTO> findAll() {
        List<Payment> list = repository.findAll();
        return list.stream().map(PaymentDTO::new).toList();
    }

    public PaymentDTO findById(String id) {
        Payment entity = repository.findById(id).get();
        return  new PaymentDTO(entity);
    }

    public PaymentDTO update(String id, PaymentDTO paymentDTO) {

        Payment entity = repository.findById(id).get();
        entity.setIdUser(paymentDTO.getIdUser());
        entity.setIdProduct(paymentDTO.getIdProduct());
        entity.setValue(paymentDTO.getValue());
        entity.setPaymentMethod(paymentDTO.getPaymentMethod());
        entity.setTransactionDate(LocalDateTime.now());
        entity.setStatus(paymentDTO.getStatus());
        repository.save(entity);
        return new PaymentDTO(entity);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}
