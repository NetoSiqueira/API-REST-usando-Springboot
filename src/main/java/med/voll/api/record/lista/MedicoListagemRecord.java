package med.voll.api.record.lista;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;

public record MedicoListagemRecord(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public MedicoListagemRecord(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
