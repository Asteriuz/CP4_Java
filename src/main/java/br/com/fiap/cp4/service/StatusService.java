package br.com.fiap.cp4.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.cp4.dto.status.AtualizacaoStatusDTO;
import br.com.fiap.cp4.dto.status.CadastroStatusDTO;
import br.com.fiap.cp4.dto.status.DetalhesStatusDTO;
import br.com.fiap.cp4.model.Status;
import br.com.fiap.cp4.repository.StatusRepository;
import br.com.fiap.cp4.service.interfaces.IStatusService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class StatusService implements IStatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public Page<DetalhesStatusDTO> listarStatus(Pageable pageable) {
        return statusRepository.findAll(pageable).map(DetalhesStatusDTO::new);
    }

    @Override
    public DetalhesStatusDTO buscarStatus(Long id) throws EntityNotFoundException {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado com ID: " + id));
        return new DetalhesStatusDTO(status);
    }

    @Override
    public Status cadastrarStatus(CadastroStatusDTO cadastroStatusDTO) {
        Status status = new Status(cadastroStatusDTO);
        return statusRepository.save(status);
    }

    @Override
    public Status atualizarStatus(Long id, AtualizacaoStatusDTO atualizacaoStatusDTO) throws EntityNotFoundException {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado com ID: " + id));
        status.atualizar(atualizacaoStatusDTO);
        return statusRepository.save(status);
    }

    @Override
    public void deletarStatus(Long id) throws EntityNotFoundException {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Status não encontrado com ID: " + id));
        statusRepository.delete(status);
    }

}
