package AGOS.agos.services;


import AGOS.agos.dto.ValorDTO;
import AGOS.agos.entity.Valor;
import AGOS.agos.repository.ValorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ValorService {
    @Autowired
    private ValorRepository valorRepository;
    @Autowired
    private ModelMapper modelMapper;
    private ValorDTO toValorDTO(Valor valor){
        return modelMapper.map(valor, ValorDTO.class);
    }
    private Valor toValor(ValorDTO valorDTO){
        return modelMapper.map(valorDTO, Valor.class);
    }
    void isNull(Long id){
        valorRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Valores não encontrado"));
    }
    void validationValorDTO(ValorDTO valorDTO){
        if (valorDTO.getItem() == null){
            throw new IllegalArgumentException("Item inválido!");
        } else if (valorDTO.getPeriodo() == null){
            throw new IllegalArgumentException("Periodo inválido!");
        } else if (valorDTO.getPrevistoFisico() == null){
            throw new IllegalArgumentException("Valor Previsto Fisico inválido!");
        }else if (valorDTO.getRealizadoFisico() == null){
            throw new IllegalArgumentException("Valor Realizado Fisico inválido!");
        }else if (valorDTO.getPrevistoFinanceiro() == null){
            throw new IllegalArgumentException("Valor Previsto Finaceiro inválido!");
        }else if (valorDTO.getRealizadoFinanceiro() == null){
            throw new IllegalArgumentException("Valor Realizado Finaceiro inválido!");
        }
    }
    public ValorDTO findById(Long id){
        isNull(id);
        Valor valor = valorRepository.findById(id).orElse(null);
        return toValorDTO(valor);
    }
    @Transactional(rollbackFor = Exception.class)
    public ValorDTO create(ValorDTO valorDTO){
        validationValorDTO(valorDTO);
        return toValorDTO(valorRepository.save(toValor(valorDTO)));
    }
    @Transactional(rollbackFor = Exception.class)
    public ValorDTO update(ValorDTO valorDTO){
        isNull(valorDTO.getId());
        validationValorDTO(valorDTO);
        return toValorDTO(valorRepository.save(toValor(valorDTO)));
    }
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        isNull(id);
        valorRepository.deleteById(id);
    }
}


