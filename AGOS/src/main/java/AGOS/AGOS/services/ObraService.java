package AGOS.AGOS.services;

import AGOS.AGOS.entity.Obra;
import org.springframework.stereotype.Service;

@Service
public class ObraService {

        public void validaCadastro(Obra obra) {
            if (obra.getTitulo() == null) {
                throw new IllegalArgumentException("O campo 'titulo' é obrigatório.");
            }

            if (obra.getObjetivo() == null) {
                throw new IllegalArgumentException("O campo 'objetivo' é obrigatório.");
            }

            if (obra.getLicitacao() == null) {
                throw new IllegalArgumentException("O campo 'licitacao' é obrigatório.");
            }

            if (obra.getDataCertame() == null) {
                throw new IllegalArgumentException("O campo 'dataCertame' é obrigatório.");
            }

            if (obra.getValorEdital() == 0.0) {
                throw new IllegalArgumentException("O campo 'valorEdital' é obrigatório.");
            }

            if (obra.getBairro() == null) {
                throw new IllegalArgumentException("O campo 'bairro' é obrigatório.");
            }

            if (obra.getRua() == null) {
                throw new IllegalArgumentException("O campo 'rua' é obrigatório.");
            }

            if (obra.getNumero() == 0) {
                throw new IllegalArgumentException("O campo 'numero' é obrigatório.");
            }

            if (obra.getValorContratado() == 0.0) {
                throw new IllegalArgumentException("O campo 'valorContratado' é obrigatório.");
            }

            if (obra.getDataInicio() == null) {
                throw new IllegalArgumentException("O campo 'dataInicio' é obrigatório.");
            }

            if (obra.getNumeroContrato() == 0) {
                throw new IllegalArgumentException("O campo 'numeroContrato' é obrigatório.");
            }

            if (obra.getEmpresaContratada() == null) {
                throw new IllegalArgumentException("O campo 'empresaContratada' é obrigatório.");
            }

            if (obra.getTipoObra() == null) {
                throw new IllegalArgumentException("O campo 'tipoObra' é obrigatório.");
            }
        }


}
