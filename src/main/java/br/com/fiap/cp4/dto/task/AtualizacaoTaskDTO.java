package br.com.fiap.cp4.dto.task;

public record AtualizacaoTaskDTO(
        Long id,
        String title,
        String description,
        String status,
        String deadline) {

}
