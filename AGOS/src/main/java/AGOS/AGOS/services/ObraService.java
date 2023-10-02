package AGOS.AGOS.services;

import AGOS.AGOS.DTO.ObraDTO;
import AGOS.AGOS.entity.Meses;
import AGOS.AGOS.entity.Obra;
import AGOS.AGOS.entity.Periodo;
import AGOS.AGOS.repository.ObraRepository;
import AGOS.AGOS.repository.PeriodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service
public class ObraService {

    @Autowired
    public PeriodoRepository periodoRepository;

    @Autowired
    public ObraRepository obraRepository;

    @Transactional
    public void createObra(ObraDTO obraDTO) {
        validateObraDTO(obraDTO);

        Obra obra = DTOToEntity(obraDTO);
        obraRepository.save(obra);

    }

    @Transactional
    public void updateObra(Long obraId, ObraDTO obraDTO) {
        validateObraDTO(obraDTO);

        Obra existingObra = obraRepository.findById(obraId)
                .orElseThrow(() -> new IllegalArgumentException("Obra não encontrada"));

        updateObraFromDTO(existingObra, obraDTO);
        obraRepository.save(existingObra);
    }

    private void validateObraDTO(ObraDTO obraDTO) {
        assertNotBlank(obraDTO.getTitulo(), "Título não pode estar em branco");
        assertNotBlank(obraDTO.getBairro(), "Bairro não pode estar em branco");
        assertNotBlank(obraDTO.getRua(), "Rua não pode estar em branco");
        assertNotBlank(obraDTO.getLicitacao(), "Licitacao não pode estar em branco");
        assertNotNull(obraDTO.getValorEdital(), "Licitacao não pode estar em branco");
        assertNotNull(obraDTO.getTipoObra(), "Tipo obra nao pode ser nulo");
    }

    private void assertNotBlank(String value, String message) {
        assert !value.isBlank() : message;
    }

    private Obra DTOToEntity(ObraDTO obraDTO) {
        Obra obra = new Obra();
        obra.setTitulo(obraDTO.getTitulo());
        obra.setFoto(obraDTO.getFoto());
        obra.setLicitacao(obraDTO.getLicitacao());
        obra.setDataCertame(obraDTO.getDataCertame());
        obra.setValorEdital(obraDTO.getValorEdital());
        obra.setBairro(obraDTO.getBairro());
        obra.setRua(obraDTO.getRua());
        obra.setNumeroEndereco(obraDTO.getNumero());
        obra.setValorContratado(obraDTO.getValorContratado());
        obra.setDataInicio(obraDTO.getDataInicio());
        obra.setDataTermino(obraDTO.getDataTermino());
        obra.setNumeroContrato(obraDTO.getNumeroContrato());
        obra.setEmpresaContratada(obraDTO.getEmpresaContratada());
        obra.setSituacao(obraDTO.getSituacao());
        obra.setTipoObra(obraDTO.getTipoObra());
        return obra;
    }

    private void updateObraFromDTO(Obra obra, ObraDTO obraDTO) {
        obra.setTitulo(obraDTO.getTitulo());
        obra.setFoto(obraDTO.getFoto());
        obra.setLicitacao(obraDTO.getLicitacao());
        obra.setDataCertame(obraDTO.getDataCertame());
        obra.setValorEdital(obraDTO.getValorEdital());
        obra.setBairro(obraDTO.getBairro());
        obra.setRua(obraDTO.getRua());
        obra.setNumeroEndereco(obraDTO.getNumero());
        obra.setValorContratado(obraDTO.getValorContratado());
        obra.setDataInicio(obraDTO.getDataInicio());
        obra.setDataTermino(obraDTO.getDataTermino());
        obra.setNumeroContrato(obraDTO.getNumeroContrato());
        obra.setEmpresaContratada(obraDTO.getEmpresaContratada());
        obra.setSituacao(obraDTO.getSituacao());
        obra.setTipoObra(obraDTO.getTipoObra());
    }

    public void atualizarPeriodosObra(Obra obra) {
        final Obra obraId = obraRepository.findById(obra.getId()).orElse(null);
        LocalDate dataInicio = obra.getDataInicio();
        LocalDate dataTermino = obra.getDataTermino();


        List<Periodo> periodos = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"));

        YearMonth mesAnoInicio = YearMonth.from(dataInicio);
        YearMonth mesAnoTermino = YearMonth.from(dataTermino);

        while (!mesAnoInicio.isAfter(mesAnoTermino)) {
            Periodo periodo = new Periodo();
            periodo.setMes(Meses.valueOf(mesAnoInicio.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR")).toUpperCase()));
            periodo.setAno(mesAnoInicio.getYear());
            periodos.add(periodo);
            periodoRepository.save(periodo);
            mesAnoInicio = mesAnoInicio.plusMonths(1);
        }
        obraRepository.save(obra);
    }

}