package br.com.fiap.cp4.dto.autenticacao;

import br.com.fiap.cp4.model.enums.UserRole;

public record RegistroDTO(String login, String password, UserRole role, String name) {
}
