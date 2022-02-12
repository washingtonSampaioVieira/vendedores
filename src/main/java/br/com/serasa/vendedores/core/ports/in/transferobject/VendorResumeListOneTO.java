package br.com.serasa.vendedores.core.ports.in.transferobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResumeListOneTO {
    @JsonProperty("regiao")
    private String region;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("dataInclusao")
    private Date createdAt;
    @JsonProperty("estados")
    private List<String> states;
}
