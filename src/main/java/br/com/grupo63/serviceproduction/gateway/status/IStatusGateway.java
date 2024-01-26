package br.com.grupo63.serviceproduction.gateway.status;

import br.com.grupo63.serviceproduction.entity.status.Status;
import br.com.grupo63.techchallenge.common.gateway.IPersistenceEntityGateway;

import java.util.List;
import java.util.Optional;

public interface IStatusGateway extends IPersistenceEntityGateway<Status> {
    List<Status> findByStatusNotFinishedAndDeletedOrderByCreationDate();

    Optional<Status> findByDeletedFalseAndOrder(int order);
}
