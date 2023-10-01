package AGOS.agos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

    @Entity
    @Table(name = "tb_periodo", schema = "public")
    public class Periodo {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Getter @Setter
        @Column(name = "id", nullable = false, unique = true)
        private Long id;

        @Getter @Setter
        @Enumerated(EnumType.STRING)
        @Column(name = "nome_mes")
        private Meses mes;

        @Getter @Setter
        @Column(name = "ano", nullable = false)
        private int ano;




}
