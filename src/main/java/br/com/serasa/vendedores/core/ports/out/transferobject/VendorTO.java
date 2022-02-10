package br.com.serasa.vendedores.core.ports.out.transferobject;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorTO {
    private Integer id;
    private String name;
    private String phone;
    private String age;
    private String city;
    private String state;
    private String region;
    private Date createdAt;
}
