package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BairroRepository extends JpaRepository<Bairro, Long> {
    public Bairro findByNome(final String nome);
}
