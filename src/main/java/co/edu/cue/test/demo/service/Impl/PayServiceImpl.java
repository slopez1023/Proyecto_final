package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.PayRepository;
import co.edu.cue.test.demo.mapping.dtos.PayDto;
import co.edu.cue.test.demo.mapping.mappers.PayMapper;
import co.edu.cue.test.demo.model.Pay;
import co.edu.cue.test.demo.service.PayService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PayServiceImpl implements PayService {

    private PayRepository payRepository;

    @Override
    public PayDto createPayment(PayDto paymentDto){
        Pay pay = PayMapper.mapFromDTO(paymentDto);
        Pay savedPay = payRepository.save(pay);
        return PayMapper.mapFrom(savedPay);
    }

    @Override
    public PayDto getPaymentById(Long id){
        Optional<Pay> pay = payRepository.findById(id);
        return pay.map(PayMapper::mapFrom).orElse(null);
    }

    @Override
    public List<PayDto> getAllPayments(){
        return payRepository.findAll().stream()
                .map(PayMapper::mapFrom)
                .collect(Collectors.toList());
    }

    @Override
    public PayDto updatePayment(Long id, PayDto paymentDto){
        if (!payRepository.existsById(id)){
            return null;
        }
        Pay pay = PayMapper.mapFromDTO(paymentDto);
        pay.setId(id);
        Pay updatedPay = payRepository.save(pay);
        return PayMapper.mapFrom(updatedPay);
    }

    @Override
    public boolean deletePayment(Long id){
        if (!payRepository.existsById(id)){
            return false;
        }
        payRepository.deleteById(id);
        return true;
    }

}
