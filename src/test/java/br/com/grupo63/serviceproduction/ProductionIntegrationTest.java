package br.com.grupo63.serviceproduction;

import br.com.grupo63.serviceproduction.api.controller.status.StatusAPIController;
import br.com.grupo63.serviceproduction.api.controller.status.dto.AdvanceOrderStatusResponseDTO;
import br.com.grupo63.serviceproduction.controller.StatusController;
import br.com.grupo63.serviceproduction.controller.dto.StatusControllerDTO;
import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import br.com.grupo63.serviceproduction.gateway.status.StatusJpaAdapter;
import br.com.grupo63.serviceproduction.gateway.status.StatusJpaRepository;
import br.com.grupo63.serviceproduction.gateway.status.entity.StatusPersistenceEntity;
import br.com.grupo63.serviceproduction.usecase.status.StatusUseCase;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductionIntegrationTest {
    @Mock
    private StatusJpaRepository statusJpaRepository;

    @InjectMocks
    private StatusJpaAdapter statusJpaAdapter;
    private StatusUseCase statusUseCase;
    private StatusController statusController;
    private StatusAPIController statusAPIController;

    private final StatusPersistenceEntity defaultStatusPersistenceEntity = new StatusPersistenceEntity(UUID.randomUUID().toString(), false, 1, OrderStatus.RECEIVED, LocalDateTime.now());

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        statusUseCase = new StatusUseCase(statusJpaAdapter);
        statusController = new StatusController(statusUseCase);
        statusAPIController = new StatusAPIController(statusController);
    }

    @SneakyThrows
    @Test
    public void testListUnfinishedOrders_EndToEnd() {
        when(statusJpaRepository.findByDeletedFalseAndStatusIn(any())).thenReturn(List.of(defaultStatusPersistenceEntity));

        ResponseEntity<List<StatusControllerDTO>> response = statusAPIController.listUnfinishedOrders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().size(), 1);
        verify(statusJpaRepository, times(1)).findByDeletedFalseAndStatusIn(any());
    }

    @SneakyThrows
    @Test
    public void testAdvanceStatus_EndToEnd() {
        when(statusJpaRepository.findByDeletedFalseAndOrder(defaultStatusPersistenceEntity.getOrder())).thenReturn(Optional.empty());
        when(statusJpaRepository.save(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.RECEIVED, any()))).thenReturn(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.RECEIVED, LocalDateTime.now()));

        ResponseEntity<AdvanceOrderStatusResponseDTO> response = statusAPIController.advanceOrderStatusFromOrderId(defaultStatusPersistenceEntity.getOrder());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getNewStatus(), OrderStatus.RECEIVED);
        verify(statusJpaRepository, times(1)).findByDeletedFalseAndOrder(anyInt());
        verify(statusJpaRepository, times(1)).save(any());
    }

    @SneakyThrows
    @Test
    public void testAdvanceStatusPreparing_EndToEnd() {
        when(statusJpaRepository.findByDeletedFalseAndOrder(defaultStatusPersistenceEntity.getOrder())).thenReturn(Optional.of(defaultStatusPersistenceEntity));
        when(statusJpaRepository.save(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.PREPARING, any()))).thenReturn(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.PREPARING, LocalDateTime.now()));

        ResponseEntity<AdvanceOrderStatusResponseDTO> response = statusAPIController.advanceOrderStatusFromOrderId(defaultStatusPersistenceEntity.getOrder());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getNewStatus(), OrderStatus.PREPARING);
        verify(statusJpaRepository, times(1)).findByDeletedFalseAndOrder(anyInt());
        verify(statusJpaRepository, times(1)).save(any());
    }

    @SneakyThrows
    @Test
    public void testAdvanceStatusReady_EndToEnd() {
        when(statusJpaRepository.findByDeletedFalseAndOrder(defaultStatusPersistenceEntity.getOrder())).thenReturn(Optional.of(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.PREPARING, LocalDateTime.now())));
        when(statusJpaRepository.save(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.READY, any()))).thenReturn(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.READY, LocalDateTime.now()));

        ResponseEntity<AdvanceOrderStatusResponseDTO> response = statusAPIController.advanceOrderStatusFromOrderId(defaultStatusPersistenceEntity.getOrder());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getNewStatus(), OrderStatus.READY);
        verify(statusJpaRepository, times(1)).findByDeletedFalseAndOrder(anyInt());
        verify(statusJpaRepository, times(1)).save(any());
    }

    @SneakyThrows
    @Test
    public void testAdvanceStatusFinished_EndToEnd() {
        when(statusJpaRepository.findByDeletedFalseAndOrder(defaultStatusPersistenceEntity.getOrder())).thenReturn(Optional.of(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.READY, LocalDateTime.now())));
        when(statusJpaRepository.save(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.FINISHED, any()))).thenReturn(new StatusPersistenceEntity(defaultStatusPersistenceEntity.getId(), false, defaultStatusPersistenceEntity.getOrder(), OrderStatus.FINISHED, LocalDateTime.now()));

        ResponseEntity<AdvanceOrderStatusResponseDTO> response = statusAPIController.advanceOrderStatusFromOrderId(defaultStatusPersistenceEntity.getOrder());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(response.getBody().getNewStatus(), OrderStatus.FINISHED);
        verify(statusJpaRepository, times(1)).findByDeletedFalseAndOrder(anyInt());
        verify(statusJpaRepository, times(1)).save(any());
    }
}
