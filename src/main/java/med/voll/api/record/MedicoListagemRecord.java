package med.voll.api.record;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public record MedicoListagemRecord(String nome, String email, String crm, Especialidade especialidade) {
    public MedicoListagemRecord(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
