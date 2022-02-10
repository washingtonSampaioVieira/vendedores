package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import org.springframework.http.ResponseEntity;

public interface VendorController {
    ResponseEntity<VendorTO> save(VendorTO vendorTO);
    ResponseEntity<VendorTO> find(Integer id);
}
