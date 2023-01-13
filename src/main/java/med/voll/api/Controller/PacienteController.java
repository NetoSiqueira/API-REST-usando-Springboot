package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.model.Paciente;
import med.voll.api.record.atualizacao.PacienteAtualizacaoRecord;
import med.voll.api.record.detalhamento.PacienteDetalhamentoRecord;
import med.voll.api.record.lista.PacienteListagemRecord;
import med.voll.api.record.entidade.PacienteRecord;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacrepository;
    @PostMapping
    @Transactional
    public ResponseEntity cadastroPaciente(@RequestBody @Valid PacienteRecord dados, UriComponentsBuilder uriBuilder){
        var paciente = new Paciente(dados);
        pacrepository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();


        return ResponseEntity.created(uri).body(new PacienteDetalhamentoRecord(paciente));
    }


    @GetMapping
    public ResponseEntity<Page<PacienteListagemRecord>> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginação){
        var page= pacrepository.findAllByAtivoTrue(paginação).map(PacienteListagemRecord::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity AtualizarPaciente(@RequestBody @Valid PacienteAtualizacaoRecord dados){
        var paciente = pacrepository.getReferenceById(dados.id());
        paciente.atualizarInformacoes(dados);

      return   ResponseEntity.ok(new PacienteDetalhamentoRecord(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPaciente(@PathVariable Long id){
        var paciente = pacrepository.getReferenceById(id);
        paciente.inativar();

        return    ResponseEntity.noContent().build();
    }

}
