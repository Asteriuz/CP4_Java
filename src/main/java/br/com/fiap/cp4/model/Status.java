package br.com.fiap.cp4.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.cp4.dto.status.AtualizacaoStatusDTO;
import br.com.fiap.cp4.dto.status.CadastroStatusDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP4_STATUS")
@EntityListeners(AuditingEntityListener.class)
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CP4_STATUS_SEQ")
    @SequenceGenerator(name = "CP4_STATUS_SEQ", sequenceName = "CP4_STATUS_SEQ", allocationSize = 50)
    private Long id;
    private String name;

    public Status(CadastroStatusDTO statusDTO) {
        this.name = statusDTO.name();
    }

    public void atualizar(AtualizacaoStatusDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
    }
}
