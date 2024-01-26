package br.com.grupo63.serviceproduction.presenter;

import br.com.grupo63.serviceproduction.api.controller.status.dto.AdvanceOrderStatusResponseDTO;
import br.com.grupo63.serviceproduction.controller.dto.StatusControllerDTO;
import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import br.com.grupo63.serviceproduction.entity.status.Status;

public class StatusPresenter {

    public static StatusControllerDTO toDto(Status entity) {
        return new StatusControllerDTO(entity.getId(), entity.getOrder(), entity.getStatus());
    }
}
