package br.com.grupo63.serviceproduction.gateway.status;

import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import br.com.grupo63.serviceproduction.entity.status.Status;
import br.com.grupo63.serviceproduction.gateway.status.entity.StatusPersistenceEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StatusJpaAdapter implements IStatusGateway {

    private final StatusJpaRepository repository;

    private static final List<OrderStatus> UNFINISHED_STATUS = new ArrayList<>() {{
        add(OrderStatus.PREPARING);
        add(OrderStatus.READY);
        add(OrderStatus.RECEIVED);
    }};

    @Override
    public List<Status> findByStatusNotFinishedAndDeletedOrderByCreationDate() {
        return repository.findByDeletedFalseAndStatusIn(UNFINISHED_STATUS)
                .stream()
                .sorted((entity1, entity2) -> entity2.getLastUpdateDate().compareTo(entity1.getLastUpdateDate()))
                .map(StatusPersistenceEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Status> findByDeletedFalseAndOrder(int order) {
        return repository.findByDeletedFalseAndOrder(order)
                .map(StatusPersistenceEntity::toModel);
    }

    @Override
    public List<Status> findByDeletedFalse() {
        return repository.findByDeletedFalse()
                .stream()
                .map(StatusPersistenceEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Status saveAndFlush(Status status) {
        StatusPersistenceEntity entity = new StatusPersistenceEntity(status);

        entity = repository.save(entity);

        return entity.toModel();
    }

    @Override
    public Optional<Status> findByIdAndDeletedFalse(Long id) {
        return repository.findByIdAndDeletedFalse(id)
                .map(StatusPersistenceEntity::toModel);
    }
}
