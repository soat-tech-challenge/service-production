package br.com.grupo63.serviceproduction.controller;

import br.com.grupo63.serviceproduction.controller.dto.StatusControllerDTO;
import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import br.com.grupo63.serviceproduction.exception.NotFoundException;
import br.com.grupo63.serviceproduction.exception.ValidationException;
import br.com.grupo63.serviceproduction.presenter.StatusPresenter;
import br.com.grupo63.serviceproduction.usecase.status.StatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusController {

    private final StatusUseCase statusUseCase;

    public OrderStatus advanceStatus(int orderId) throws NotFoundException, ValidationException {
        return statusUseCase.advanceStatus(orderId);
    }

    public List<StatusControllerDTO> listUnfinishedOrders() {
        return statusUseCase.listUnfinishedOrders().stream().map(StatusPresenter::toDto).toList();
    }

}
