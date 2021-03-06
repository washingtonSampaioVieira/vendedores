package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.ports.in.transferobject.OperationTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import org.springframework.http.ResponseEntity;

public interface OperationController {
    ResponseEntity<OperationTO> save(OperationTO operationTO);
}
