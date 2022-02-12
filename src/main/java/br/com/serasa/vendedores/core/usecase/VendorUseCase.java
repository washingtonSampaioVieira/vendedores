package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.VendorFullModel;
import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.usecase.exception.NoFoundRegistry;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface VendorUseCase {
    VendorModel save(VendorModel  vendorModel);
    VendorFullModel findOne(Integer id) throws NoFoundRegistry;
    List<VendorModel> findAll();
}
