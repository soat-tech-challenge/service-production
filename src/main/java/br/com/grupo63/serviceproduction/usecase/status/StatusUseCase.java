package br.com.grupo63.serviceproduction.usecase.status;

import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import br.com.grupo63.serviceproduction.entity.status.Status;
import br.com.grupo63.serviceproduction.exception.NotFoundException;
import br.com.grupo63.serviceproduction.exception.ValidationException;
import br.com.grupo63.serviceproduction.gateway.status.IStatusGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusUseCase implements IStatusUseCase {

    private final IStatusGateway gateway;

    @Override
    public OrderStatus advanceStatus(int order) throws ValidationException, NotFoundException {
        // GET /public/order/{orderId}
        // Order order = orderGateway.getById(orderId);
        Status status = gateway.findByDeletedFalseAndOrder(order).orElse(new Status(order));
        status.advanceStatus();
        return gateway.saveAndFlush(status).getStatus();
    }

    @Override
    public List<Status> listUnfinishedOrders() {
        return gateway.findByStatusNotFinishedAndDeletedOrderByCreationDate();
    }

}
