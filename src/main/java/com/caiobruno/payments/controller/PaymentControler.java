package com.caiobruno.payments.controller;

import com.caiobruno.payments.domain.dto.PaymentDTO;
import com.caiobruno.payments.domain.model.Payment;
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
    PaymentService service ;

    @PostMapping
    public ResponseEntity<PaymentDTO> create(@RequestBody  PaymentDTO paymentDTO){
        Payment entity = new Payment(paymentDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(service.created(entity));
    }
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> create(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable String id ){
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDTO> update(@PathVariable String id, @RequestBody PaymentDTO paymentDTO ){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, paymentDTO));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
       return ResponseEntity.ok().build();
    }

}
