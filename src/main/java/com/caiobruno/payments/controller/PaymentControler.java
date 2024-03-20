package com.caiobruno.payments.controller;

import com.caiobruno.payments.domain.dto.PaymentDTO;
import com.caiobruno.payments.domain.enums.StatusPayment;
import com.caiobruno.payments.domain.model.Payment;
import com.caiobruno.payments.exceptions.ServiceException;
import com.caiobruno.payments.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentControler {

    @Autowired
    PaymentService service;

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody @Valid Payment entity) {
        try {
            PaymentDTO paymentDTO = service.created(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(paymentDTO);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> findAll() {
        try {
            List<PaymentDTO> payment = service.findAll();
            if (payment.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(payment);
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable String id) {
        try {
            PaymentDTO client = service.findById(id);
            return ResponseEntity.ok(client);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable String id,@Valid @RequestBody  PaymentDTO paymentDTO) {
        try {
            PaymentDTO updatedClient = service.update(id, paymentDTO);
            return ResponseEntity.ok(updatedClient);
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        try {
            service.delete(id);
            return ResponseEntity.ok().build();
        } catch (ServiceException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/statuspayment/{code}")
    public ResponseEntity<List<PaymentDTO>> findByStatus(@PathVariable Integer code) {
        try{
            List<PaymentDTO> categories = service.findByStatus(code);
            if(categories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/methodpayment/{code}")
    public ResponseEntity<List<PaymentDTO>> findByMethod(@PathVariable Integer code) {
        try{
            List<PaymentDTO> categories = service.findByMethod(code);
            if(categories.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
