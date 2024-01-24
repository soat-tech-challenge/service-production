package br.com.grupo63.serviceproduction.gateway.status;

import br.com.grupo63.serviceproduction.entity.status.OrderStatus;
import br.com.grupo63.serviceproduction.gateway.repository.IJpaRepository;
import br.com.grupo63.serviceproduction.gateway.status.entity.StatusPersistenceEntity;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface StatusJpaRepository extends CrudRepository<StatusPersistenceEntity, String>, IJpaRepository<StatusPersistenceEntity> {

    List<StatusPersistenceEntity> findByDeletedFalseAndStatusIn(List<OrderStatus> status);

    Optional<StatusPersistenceEntity> findByDeletedFalseAndOrder(int order);

}
