package med.voll.api.Controller;


import jakarta.validation.Valid;
import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;
import med.voll.api.record.MedicoListagemRecord;
import med.voll.api.record.MedicoRecord;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return medrepository.findAll(paginação).map(MedicoListagemRecord::new);
    }
}
