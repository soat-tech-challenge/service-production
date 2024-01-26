package br.com.grupo63.serviceproduction.usecase.status;

import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import br.com.grupo63.serviceproduction.entity.status.Status;
import br.com.grupo63.techchallenge.common.exception.NotFoundException;
import br.com.grupo63.techchallenge.common.exception.ValidationException;

import java.util.List;

public interface IStatusUseCase {

    OrderStatus advanceStatus(int order) throws ValidationException, NotFoundException;

    List<Status> listUnfinishedOrders();

}
