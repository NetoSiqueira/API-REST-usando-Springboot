package med.voll.api.record;

import med.voll.api.model.Paciente;

public record PacienteListagemRecord(String nome, String email, String cpf) {
    public PacienteListagemRecord (Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
