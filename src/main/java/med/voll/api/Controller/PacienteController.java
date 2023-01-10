package med.voll.api.Controller;

import jakarta.validation.Valid;
import med.voll.api.model.Paciente;
import med.voll.api.record.PacienteListagemRecord;
import med.voll.api.record.PacienteRecord;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacrepository;
    @PostMapping
    @Transactional
    public void cadastroPaciente(@RequestBody @Valid PacienteRecord dados){

        pacrepository.save(new Paciente(dados) );
    }


    @GetMapping
    public Page<PacienteListagemRecord> listarPacientes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginação){
        return pacrepository.findAll(paginação).map(PacienteListagemRecord::new);
    }

}
