package com.caiobruno.payments.service;

import com.caiobruno.payments.domain.dto.PaymentDTO;
import com.caiobruno.payments.domain.model.Payment;
import com.caiobruno.payments.exceptions.PaymentNotFoundException;
import com.caiobruno.payments.exceptions.ServiceException;
import com.caiobruno.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    public PaymentDTO created(Payment entity) throws ServiceException {
        entity.setTransactionDate(LocalDateTime.now());
        Payment payment =  repository.save(entity);
        return new PaymentDTO(payment);
    }
    public List<PaymentDTO> findAll() throws ServiceException {
        List<Payment> list = repository.findAll();
        if (list.isEmpty()){

            throw new PaymentNotFoundException("Error ");
        }else {
            return list.stream().map(PaymentDTO::new).toList();
        }

    }

    public PaymentDTO findById(String id) throws ServiceException {
        return repository.findById(id)
                .map(PaymentDTO::new)
                .orElseThrow(() -> new PaymentNotFoundException("Client not found with ID: " + id));
    }

    public PaymentDTO update(String id, PaymentDTO paymentDTO) throws ServiceException{

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

    public void delete(String id) throws ServiceException{
        if (!repository.existsById(id)) {
            throw new PaymentNotFoundException("Client not found with ID: " + id);
        }
        repository.deleteById(id);
    }
}
