package med.voll.api.Controller;

import med.voll.api.record.PacienteRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @PostMapping
    public void cadastroPaciente(@RequestBody PacienteRecord dados){
        System.out.println(dados);
    }
}
