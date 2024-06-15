package co.edu.cue.test.demo.service;

// PayService.java
import co.edu.cue.test.demo.mapping.dtos.PayDto;

import java.util.List;

public interface PayService {
    PayDto createPayment(PayDto paymentDto);
    PayDto getPaymentById(Long id);
    List<PayDto> getAllPayments();
    PayDto updatePayment(Long id, PayDto paymentDto);
    boolean deletePayment(Long id);
}

