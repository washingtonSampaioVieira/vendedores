package br.com.serasa.vendedores.core.ports.out;

import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;
import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;

import java.util.List;

public interface VendorRepositoryPort {
    VendorTO save(VendorTO vendorTO);

    VendorTO findOne(Integer id);

    List<VendorTO> findAll();
}
