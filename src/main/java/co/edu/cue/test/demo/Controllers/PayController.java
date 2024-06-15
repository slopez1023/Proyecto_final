package co.edu.cue.test.demo.Controllers;

import co.edu.cue.test.demo.mapping.dtos.PayDto;
import co.edu.cue.test.demo.service.Impl.PayServiceImpl;
import co.edu.cue.test.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping
    public ResponseEntity<PayDto> createPayment(@RequestBody PayDto payDto) {
        PayDto createdPayment = payService.createPayment(payDto);
        return ResponseEntity.ok(createdPayment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PayDto> getPaymentById(@PathVariable Long id) {
        PayDto payment = payService.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    @GetMapping
    public ResponseEntity<List<PayDto>> getAllPayments() {
        List<PayDto> payments = payService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PayDto> updatePayment(@PathVariable Long id, @RequestBody PayDto payDto) {
        PayDto updatedPayment = payService.updatePayment(id, payDto);
        if (updatedPayment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        boolean isDeleted = payService.deletePayment(id);
        if (!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}