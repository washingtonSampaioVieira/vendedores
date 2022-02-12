package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.ports.in.transferobject.VendorFullTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorResumeListOneTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VendorController {
    ResponseEntity<VendorTO> save(VendorTO vendorTO);
    ResponseEntity<VendorResumeListOneTO> find(Integer id);
    ResponseEntity<List<VendorFullTO>> findAll();
}
