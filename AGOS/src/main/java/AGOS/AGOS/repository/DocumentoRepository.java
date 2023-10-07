package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository <Documento, Long> {

    Optional<Documento> findByNome(String nome);
}
