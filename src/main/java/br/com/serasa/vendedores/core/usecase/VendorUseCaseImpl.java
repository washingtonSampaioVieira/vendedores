package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.out.VendorRepositoryPort;
import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class VendorUseCaseImpl implements VendorUseCase {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    VendorRepositoryPort vendorRepositoryPort;

    @Override
    public VendorModel save(VendorModel vendorModel) {
        log.info("Enviando para porta de saida o cadastro de um novo vendedor, nome: " + vendorModel.getName());
        VendorTO vendorTO = modelMapper.map(vendorModel, VendorTO.class);
        return  modelMapper.map(vendorRepositoryPort.save(vendorTO), VendorModel.class);
    }

    @Override
    public VendorModel findOne(Integer id) {
        return null;
    }

    @Override
    public List<VendorModel> findAll() {
        return null;
    }
}
