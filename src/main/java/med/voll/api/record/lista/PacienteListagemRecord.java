package med.voll.api.record.lista;

import med.voll.api.model.Paciente;

public record PacienteListagemRecord(Long id,String nome, String email, String cpf) {
    public PacienteListagemRecord (Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
