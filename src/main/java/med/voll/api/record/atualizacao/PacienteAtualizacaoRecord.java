package med.voll.api.record.atualizacao;

import jakarta.validation.constraints.NotNull;
import med.voll.api.record.entidade.EnderecoRecord;

public record PacienteAtualizacaoRecord(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoRecord endereco) {
}
