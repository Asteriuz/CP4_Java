package br.com.fiap.cp4.dto.status;

import br.com.fiap.cp4.model.Status;

public record DetalhesStatusDTO(Long id, String name) {
    public DetalhesStatusDTO(Status status) {
        this(status.getId(), status.getName());
    }
}
