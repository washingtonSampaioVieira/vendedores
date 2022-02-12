package br.com.serasa.vendedores.core.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorModel {
    private Integer id;
    private String name;
    private String phone;
    private Integer age;
    private String city;
    private String state;
    private String region;
    private Date createdAt;
}
