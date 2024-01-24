package br.com.grupo63.serviceproduction.entity.status;


import br.com.grupo63.serviceproduction.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

import static java.util.Map.entry;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status {
    String id;
    boolean deleted;
    int order;
    OrderStatus status;

    private final Map<OrderStatus, OrderStatus> nextOrderMap = Map.ofEntries(
            entry(OrderStatus.RECEIVED, OrderStatus.PREPARING),
            entry(OrderStatus.PREPARING, OrderStatus.READY),
            entry(OrderStatus.READY, OrderStatus.FINISHED));

    public Status(int order) {
        this.order = order;
    }

    public void advanceStatus() throws ValidationException {
        if (this.getStatus() == OrderStatus.FINISHED) {
            throw new ValidationException("order.advanceStatus.title", "order.advanceStatus.finished");
        } else if (this.getStatus() == null) {
            this.setStatus(OrderStatus.RECEIVED);
        } else {
            this.setStatus(nextOrderMap.get(this.getStatus()));
        }
    }
}
