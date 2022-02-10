package br.com.serasa.vendedores.core.ports.in.transferobject;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorTO {
    private Integer id;
    @NotBlank(message = "O nome do vendedor deve ser informado")
    @JsonProperty("nome")
    private String name;
    @NotBlank(message = "O telefone do vendedor deve ser informado")
    @JsonProperty("telefone")
    private String phone;
    @JsonProperty("idade")
    private String age;
    @JsonProperty("cidade")
    private String city;
    @JsonProperty("estado")
    private String state;
    @JsonProperty("regiao")
    private String region;
    @JsonIgnore
    private Date createdAt;
}
