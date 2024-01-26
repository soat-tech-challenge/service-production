package br.com.grupo63.serviceproduction.controller.dto;

import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class StatusControllerDTO {

    private String id;
    private int order;
    private OrderStatus status;

}
