package br.com.serasa.vendedores.core.model;

import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorFullModel extends VendorModel {
    private List<String> states;

    public void setVendor(VendorTO vendorTO) {
        this.setAge(vendorTO.getAge());
        this.setState(vendorTO.getState());
        this.setCity(vendorTO.getCity());
        this.setName(vendorTO.getName());
        this.setPhone(vendorTO.getPhone());
        this.setRegion(vendorTO.getRegion());
        this.setCreatedAt(vendorTO.getCreatedAt());
        this.setId(vendorTO.getId());
    }
}
