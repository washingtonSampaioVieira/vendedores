package br.com.serasa.vendedores.core.ports.out;

import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;

import java.util.List;

public interface OperationRepositoryPort {
    OperationTO save(OperationTO operationTO);

    OperationTO findOne(Integer id);

    List<OperationTO> findAll();
}
