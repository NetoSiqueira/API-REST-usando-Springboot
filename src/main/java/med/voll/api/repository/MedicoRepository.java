package med.voll.api.repository;


import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;

public interface MedicoRepository  extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginação);
}
