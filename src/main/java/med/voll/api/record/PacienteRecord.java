package med.voll.api.record;

public record PacienteRecord(String nome, String email, String telefone, String cpf, EnderecoRecord enderecoRecord) {
}
