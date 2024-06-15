package co.edu.cue.test.demo.service.Impl;

import co.edu.cue.test.demo.Repositories.PayRepository;
import co.edu.cue.test.demo.mapping.dtos.PayDto;
import co.edu.cue.test.demo.model.Pay;
import co.edu.cue.test.demo.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para gestionar pagos.
 */
@Service
public class PayServiceImpl implements PayService {

    private PayRepository payRepository;


    /**
     * Crea un nuevo pago.
     *
     * @param payDto DTO del pago a crear.
     * @return DTO del pago creado.
     *
     * Este método toma un objeto PayDto, lo convierte en una entidad Pay,
     * guarda la entidad en el repositorio y devuelve un DTO con los datos del pago guardado.
     */
    @Override
    public PayDto createPayment(PayDto payDto) {
        Pay pay = new Pay();
        pay.setMounter(payDto.mounter());

        Pay savedPay = payRepository.save(pay);
        return new PayDto(savedPay.getId(), savedPay.getMounter());
    }

    /**
     * Obtiene un pago por su ID.
     *
     * @param id ID del pago a buscar.
     * @return DTO del pago encontrado, o null si no se encuentra.
     *
     * Este método busca un pago en el repositorio por su ID y, si lo encuentra,
     * devuelve un DTO con los datos del pago. Si no lo encuentra, devuelve null.
     */
    @Override
    public PayDto getPaymentById(Long id) {
        Optional<Pay> pay = payRepository.findById(id);
        return pay.map(p -> new PayDto(p.getId(), p.getMounter())).orElse(null);
    }

    /**
     * Obtiene todos los pagos.
     *
     * @return Lista de DTOs de todos los pagos.
     *
     * Este método obtiene todas las entidades Pay del repositorio, las convierte en DTOs
     * y devuelve una lista de estos DTOs.
     */
    @Override
    public List<PayDto> getAllPayments() {
        return payRepository.findAll().stream()
                .map(p -> new PayDto(p.getId(), p.getMounter()))
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un pago existente.
     *
     * @param id ID del pago a actualizar.
     * @param payDto DTO del pago con los nuevos datos.
     * @return DTO del pago actualizado, o null si no se encuentra.
     *
     * Este método busca un pago por su ID. Si lo encuentra, actualiza los datos del pago
     * con la información proporcionada en el DTO, guarda la entidad actualizada en el repositorio
     * y devuelve un DTO con los datos del pago actualizado. Si no lo encuentra, devuelve null.
     */
    @Override
    public PayDto updatePayment(Long id, PayDto payDto) {
        Optional<Pay> optionalPay = payRepository.findById(id);
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            pay.setMounter(payDto.mounter());
            Pay updatedPay = payRepository.save(pay);
            return new PayDto(updatedPay.getId(), updatedPay.getMounter());
        }
        return null;
    }

    /**
     * Elimina un pago por su ID.
     *
     * @param id ID del pago a eliminar.
     * @return true si el pago fue eliminado, false si no se encuentra.
     *
     * Este método verifica si un pago existe por su ID. Si existe, lo elimina del repositorio
     * y devuelve true. Si no existe, devuelve false.
     */
    @Override
    public boolean deletePayment(Long id) {
        if (payRepository.existsById(id)) {
            payRepository.deleteById(id);
            return true;
        }
        return false;
    }
}





