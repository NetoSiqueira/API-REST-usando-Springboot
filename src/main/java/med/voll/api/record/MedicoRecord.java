package med.voll.api.record;

import med.voll.api.model.Especialidade;

public record MedicoRecord(String nome, String email, String crm, Especialidade especialidade, EnderecoRecord endereco) {
}
