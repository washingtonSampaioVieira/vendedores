package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.VendorModel;

import java.util.List;

public interface VendorUseCase {
    VendorModel save(VendorModel  vendorModel);
    VendorModel findOne(Integer id);
    List<VendorModel> findAll();
}
