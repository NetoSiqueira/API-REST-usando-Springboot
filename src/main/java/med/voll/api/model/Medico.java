package med.voll.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.record.atualizacao.MedicoAtualizacaoRecord;
import med.voll.api.record.entidade.MedicoRecord;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    private String telefone;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(MedicoRecord dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(MedicoAtualizacaoRecord dados) {
       if (dados.nome() != null){
            this.nome = dados.nome();
        }
       if (dados.telefone() != null){
           this.telefone = dados.telefone();
       }
       if (dados.endereco() != null){
           this.endereco.atualizarInformacoes(dados.endereco());
       }
    }
}
