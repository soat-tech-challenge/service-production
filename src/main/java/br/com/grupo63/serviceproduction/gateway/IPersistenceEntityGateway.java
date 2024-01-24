package br.com.grupo63.serviceproduction.gateway;

import java.util.List;
import java.util.Optional;

public interface IPersistenceEntityGateway<T> {

    List<T> findByDeletedFalse();

    T saveAndFlush(T entity);

    Optional<T> findByIdAndDeletedFalse(Long id);

}
