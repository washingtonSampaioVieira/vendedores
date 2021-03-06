package br.com.serasa.vendedores.core.ports.in.transferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OperationTO {
    @JsonIgnore
    private Integer id;
    @JsonProperty("regiao")
    private String region;
    @JsonProperty("estados")
    private List<String> states;
}
