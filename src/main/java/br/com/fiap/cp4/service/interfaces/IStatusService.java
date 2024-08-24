package br.com.fiap.cp4.service.interfaces;

import br.com.fiap.cp4.dto.status.*;
import br.com.fiap.cp4.model.Status;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IStatusService {
    Page<DetalhesStatusDTO> listarStatus(Pageable pageable);
    DetalhesStatusDTO buscarStatus(Long id) throws EntityNotFoundException;
    Status cadastrarStatus(CadastroStatusDTO cadastroStatusDTO);
    Status atualizarStatus(Long id, AtualizacaoStatusDTO atualizacaoStatusDTO) throws EntityNotFoundException;
    void deletarStatus(Long id) throws EntityNotFoundException;
}
