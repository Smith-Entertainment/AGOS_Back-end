    package AGOS.AGOS.services;

    import AGOS.AGOS.DTO.PeriodoDTO;
    import AGOS.AGOS.entity.Periodo;
    import AGOS.AGOS.repository.PeriodoRepository;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;


    @Service
    public class PeriodoService {
        @Autowired
        private PeriodoRepository periodoRepository;
        @Autowired
        private ModelMapper modelMapper;
         Periodo toPeriodo(PeriodoDTO periodoDTO){
            return modelMapper.map(periodoDTO, Periodo.class);
        }
         PeriodoDTO toPeriodoDTO (Periodo periodo){
            return modelMapper.map(periodo, PeriodoDTO.class);
        }
         void isNull(Long id ){
            periodoRepository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("Periodo não encontrado"));
        }
         void validationPeriodoDTO(PeriodoDTO periodoDTO){
            int ano = periodoDTO.getAno();
            String anoString = Integer.toString(ano);
            if (!anoString.matches("[0-9]{4}")){
                throw new IllegalArgumentException("Ano inválido");
            }
        }
        public PeriodoDTO findById(Long id){
            isNull(id);
            final Periodo periodo = this.periodoRepository.findById(id).orElse(null);
            return toPeriodoDTO(periodo);
        }
        @Transactional(rollbackFor = Exception.class)
        public PeriodoDTO create (PeriodoDTO periodo){
            validationPeriodoDTO(periodo);
            return toPeriodoDTO(periodoRepository.save(toPeriodo(periodo)));
        }
        @Transactional(rollbackFor = Exception.class)
        public PeriodoDTO update (PeriodoDTO periodo){
            isNull(periodo.getId());
            validationPeriodoDTO(periodo);
            return toPeriodoDTO(periodoRepository.save(toPeriodo(periodo)));
        }
        @Transactional(rollbackFor = Exception.class)
        public void delete(Long id){
            isNull(id);
            periodoRepository.deleteById(id);
        }

    }