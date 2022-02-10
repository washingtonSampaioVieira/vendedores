package br.com.serasa.vendedores.core.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationModel {
    private Integer id;
    private String region;
    private List<String> states;
}
