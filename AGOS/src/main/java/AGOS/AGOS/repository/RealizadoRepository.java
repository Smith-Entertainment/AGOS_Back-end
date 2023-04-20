package AGOS.AGOS.repository;

import AGOS.AGOS.entity.Realizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RealizadoRepository extends JpaRepository<Realizado, Long> {
}
