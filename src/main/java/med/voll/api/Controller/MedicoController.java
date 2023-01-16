package med.voll.api.Controller;


import jakarta.validation.Valid;
import med.voll.api.model.Medico;
import med.voll.api.record.atualizacao.MedicoAtualizacaoRecord;
import med.voll.api.record.detalhamento.MedicoDetalhamentoRecord;
import med.voll.api.record.lista.MedicoListagemRecord;
import med.voll.api.record.entidade.MedicoRecord;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medrepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedicos(@RequestBody @Valid MedicoRecord dados, UriComponentsBuilder uriBuilder){
       var medico = new Medico(dados);
        medrepository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicoDetalhamentoRecord(medico));
    }
    @GetMapping
    public ResponseEntity<Page<MedicoListagemRecord>> listarMedicos(@PageableDefault(size = 10, sort = {"email"}) Pageable paginação){
        var page = medrepository.findAllByAtivoTrue(paginação).map(MedicoListagemRecord::new);

        return ResponseEntity.ok(page);
    }
    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedico(@RequestBody @Valid MedicoAtualizacaoRecord dados){
        var medico = medrepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new MedicoDetalhamentoRecord(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarMedico(@PathVariable Long id){
        var medico = medrepository.getReferenceById(id);
        medico.inativar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){
        var medico = medrepository.getReferenceById(id);

        return ResponseEntity.ok(new MedicoDetalhamentoRecord(medico));
    }
}
