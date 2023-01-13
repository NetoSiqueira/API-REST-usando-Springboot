package med.voll.api.record.detalhamento;

import med.voll.api.model.Endereco;
import med.voll.api.model.Paciente;

public record PacienteDetalhamentoRecord(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    public PacienteDetalhamentoRecord (Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
