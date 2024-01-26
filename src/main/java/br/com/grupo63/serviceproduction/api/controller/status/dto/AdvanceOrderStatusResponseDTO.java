package br.com.grupo63.serviceproduction.api.controller.status.dto;

import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class AdvanceOrderStatusResponseDTO {

    OrderStatus newStatus;

}
