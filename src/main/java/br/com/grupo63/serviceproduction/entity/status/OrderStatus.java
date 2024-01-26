package br.com.grupo63.serviceproduction.entity.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OrderStatus {
    RECEIVED, PREPARING, READY, FINISHED;

}
