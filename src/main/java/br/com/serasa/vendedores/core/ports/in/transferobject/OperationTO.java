package br.com.serasa.vendedores.core.ports.in.transferobject;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationTO {
    private Integer id;
    private String region;
    private List<String> states;
}
