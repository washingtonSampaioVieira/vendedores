package br.com.serasa.vendedores.core.ports.out;

import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;

public interface OperationRepositoryPort {
    OperationTO save(OperationTO operationTO);
}
