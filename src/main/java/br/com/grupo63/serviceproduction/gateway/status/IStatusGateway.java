package br.com.grupo63.serviceproduction.gateway.status;

import br.com.grupo63.serviceproduction.entity.status.Status;

import java.util.List;
import java.util.Optional;

public interface IStatusGateway {

    Status saveAndFlush(Status entity);

    List<Status> findByStatusNotFinishedAndDeletedOrderByCreationDate();

    Optional<Status> findByDeletedFalseAndOrder(int order);
}
