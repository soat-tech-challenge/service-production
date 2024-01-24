package br.com.grupo63.serviceproduction.controller.dto;

import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusControllerDTO extends AbstractControllerDTO {

    private String id;
    private int order;

    @Schema(defaultValue = "Recebido")
    private OrderStatus status;

}
