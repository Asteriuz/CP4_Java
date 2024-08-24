package br.com.fiap.cp4.controller;

import br.com.fiap.cp4.config.TokenService;
import br.com.fiap.cp4.dto.autenticacao.AutenticacaoDTO;
import br.com.fiap.cp4.dto.autenticacao.LoginResponseDTO;
import br.com.fiap.cp4.dto.autenticacao.RegistroDTO;
import br.com.fiap.cp4.model.User;
import br.com.fiap.cp4.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO data) {
        log.info("Autenticando usuário: {}", data.login());
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        log.info("Usuário autenticado com sucesso: {}", data.login());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegistroDTO data) {
        log.info("Registrando usuário: {}", data.login());

        if (this.userRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role(), data.name());

        this.userRepository.save(newUser);

        this.userRepository.save(newUser);

        log.info("Usuário registrado com sucesso: {}", data.login());
        return ResponseEntity.ok().build();
    }

}
