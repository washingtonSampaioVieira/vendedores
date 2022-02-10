package br.com.serasa.vendedores.infra.persistence.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_operation")
@Builder
public class OperationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "region")
    private String region;

    @Column(name = "states")
    private String[] states;

}
