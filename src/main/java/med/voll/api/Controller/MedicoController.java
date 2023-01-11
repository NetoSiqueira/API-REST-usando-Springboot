package med.voll.api.Controller;


import jakarta.validation.Valid;
import med.voll.api.model.Medico;
import med.voll.api.record.atualizacao.MedicoAtualizacaoRecord;
import med.voll.api.record.lista.MedicoListagemRecord;
import med.voll.api.record.entidade.MedicoRecord;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medrepository;

    @PostMapping
    @Transactional
    public void cadastrarMedicos(@RequestBody @Valid MedicoRecord dados){
        medrepository.save(new Medico(dados));
    }
    @GetMapping
    public Page<MedicoListagemRecord> listarMedicos(@PageableDefault(size = 10, sort = {"email"}) Pageable paginação){
        return medrepository.findAllByAtivoTrue(paginação).map(MedicoListagemRecord::new);
    }
    @PutMapping
    @Transactional
    public void atualizarMedico(@RequestBody @Valid MedicoAtualizacaoRecord dados){
        var medico = medrepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletarMedico(@PathVariable Long id){
        var medico = medrepository.getReferenceById(id);
        medico.inativar();
    }
}
