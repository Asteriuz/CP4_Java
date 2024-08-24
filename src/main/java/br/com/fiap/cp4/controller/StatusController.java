package br.com.fiap.cp4.controller;

import org.springframework.beans.factory.annotation.Autowired;


import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import br.com.fiap.cp4.dto.status.AtualizacaoStatusDTO;
import br.com.fiap.cp4.dto.status.CadastroStatusDTO;
import br.com.fiap.cp4.dto.status.DetalhesStatusDTO;
import br.com.fiap.cp4.model.Status;
import br.com.fiap.cp4.service.StatusService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/public/status")
public class StatusController {

    @Autowired
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesStatusDTO>> list(Pageable pageable) {
        Page<DetalhesStatusDTO> paginaStatuss = statusService.listarStatus(pageable);
        return ResponseEntity.ok(paginaStatuss);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesStatusDTO> search(@PathVariable Long id) {
        try {
            DetalhesStatusDTO status = statusService.buscarStatus(id);
            return ResponseEntity.ok(status);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesStatusDTO> register(@RequestBody @Valid CadastroStatusDTO statusDTO, UriComponentsBuilder uriBuilder) {
        Status status = statusService.cadastrarStatus(statusDTO);
        DetalhesStatusDTO detalhesStatusDTO = new DetalhesStatusDTO(status);
        return ResponseEntity.created(uriBuilder.path("/statuss/{id}").buildAndExpand(status.getId()).toUri()).body(detalhesStatusDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesStatusDTO> update(@PathVariable Long id, @RequestBody @Valid AtualizacaoStatusDTO statusDTO) {
        try {
            Status status = statusService.atualizarStatus(id, statusDTO);
            return ResponseEntity.ok(new DetalhesStatusDTO(status));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            statusService.deletarStatus(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
