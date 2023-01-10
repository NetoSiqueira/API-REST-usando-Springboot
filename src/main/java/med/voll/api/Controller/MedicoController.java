package med.voll.api.Controller;


import jakarta.validation.Valid;
import med.voll.api.model.Endereco;
import med.voll.api.model.Medico;
import med.voll.api.record.MedicoRecord;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
