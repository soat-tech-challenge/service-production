package br.com.grupo63.serviceproduction.api.controller.status;

import br.com.grupo63.serviceproduction.api.controller.status.dto.AdvanceOrderStatusResponseDTO;
import br.com.grupo63.serviceproduction.controller.StatusController;
import br.com.grupo63.serviceproduction.controller.dto.StatusControllerDTO;
import br.com.grupo63.techchallenge.common.api.controller.AbstractAPIController;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Status", description = "Gerencia o estado dos pedidos.")
@RequiredArgsConstructor
@RestController
@RequestMapping("/status")
public class StatusAPIController extends AbstractAPIController {

    private final StatusController controller;

    @Operation(
            tags = "5ª chamada - Fluxo principal - Acompanhamento e entrega",
            summary = "Listar pedidos pagos porém não finalizados",
            description = "Seria utilizado para acompanha os pedido Recebidos, Em preparação e Prontos")
    @GetMapping("/queue")
    public ResponseEntity<List<StatusControllerDTO>> listUnfinishedOrders() {
        return ResponseEntity.ok(controller.listUnfinishedOrders());
    }

    @Operation(
            tags = "5ª chamada - Fluxo principal - Acompanhamento e entrega",
            summary = "Avança com o status do pedido",
            description = "Após o pedido ser recebido, esse endpoint seria utilizado pelo funcionário para avançar o status do pedido")
    @PostMapping("/advance-status")
    public ResponseEntity<AdvanceOrderStatusResponseDTO> advanceOrderStatusFromOrderId(@Parameter(description = "Id do pedido.") @RequestParam int orderId) throws NotFoundException, ValidationException {
        return ResponseEntity.ok(new AdvanceOrderStatusResponseDTO(controller.advanceStatus(orderId)));
    }

}
