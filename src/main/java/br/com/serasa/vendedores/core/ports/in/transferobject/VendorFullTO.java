package br.com.serasa.vendedores.core.ports.in.transferobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorFullTO {
    @JsonProperty("nome")
    private String name;
    @JsonProperty("telfone")
    private String phone;
    @JsonProperty("idade")
    private Integer age;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("estados")
    private List<String> states;


}
