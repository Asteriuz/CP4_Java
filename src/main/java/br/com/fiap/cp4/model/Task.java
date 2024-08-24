package br.com.fiap.cp4.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.fiap.cp4.dto.task.AtualizacaoTaskDTO;
import br.com.fiap.cp4.dto.task.CadastroTaskDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CP4_TASK")
@EntityListeners(AuditingEntityListener.class)
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CP4_TASK_SEQ")
    @SequenceGenerator(name="CP4_TASK_SEQ", sequenceName="CP4_TASK_SEQ", allocationSize=50)
    private Long id;
    private String title;
    private String description;
    private String status;
    private String deadline;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Task(CadastroTaskDTO taskDTO) {
        this.title = taskDTO.title();
        this.description = taskDTO.description();
        this.status = taskDTO.status();
        this.deadline = taskDTO.deadline();
    }

    public void atualizar(AtualizacaoTaskDTO dto) {
        if (dto.title() != null) {
            this.title = dto.title();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
        if (dto.status() != null) {
            this.status = dto.status();
        }
        if (dto.deadline() != null) {
            this.deadline = dto.deadline();
        }
    }
}
