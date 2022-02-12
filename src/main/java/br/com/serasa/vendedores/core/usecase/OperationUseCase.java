package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.OperationModel;
import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;

import java.util.List;


public interface OperationUseCase {
    OperationModel save(OperationModel operationModel);
    OperationModel findOne(Integer id);
    List<OperationModel> findAll();

    OperationTO findByRegion();
}
