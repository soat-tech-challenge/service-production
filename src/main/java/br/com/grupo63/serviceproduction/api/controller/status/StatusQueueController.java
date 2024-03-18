package br.com.grupo63.serviceproduction.api.controller.status;

import br.com.grupo63.serviceproduction.controller.StatusController;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;
import com.amazonaws.services.sqs.model.Message;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor

@Component
public class StatusQueueController {
    private final StatusController controller;

    @SqsListener(value = "approvedPayments.fifo")
    public void processMessage(int orderId) throws ValidationException, NotFoundException {
        controller.advanceStatus(orderId);
    }
}
