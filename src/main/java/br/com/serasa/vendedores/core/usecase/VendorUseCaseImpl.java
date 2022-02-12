package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.VendorFullModel;
import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.out.VendorRepositoryPort;
import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;
import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.exception.NoFoundRegistry;
import br.com.serasa.vendedores.infra.persistence.entity.OperationEntity;
import br.com.serasa.vendedores.infra.persistence.repositories.crud.OperationRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
public class VendorUseCaseImpl implements VendorUseCase {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    VendorRepositoryPort vendorRepositoryPort;

    @Autowired
    OperationRepository operationRepository;

    @Override
    public VendorModel save(VendorModel vendorModel) {
        log.info("Enviando para porta de saida o cadastro de um novo vendedor, nome: " + vendorModel.getName());
        VendorTO vendorTO = modelMapper.map(vendorModel, VendorTO.class);
        return  modelMapper.map(vendorRepositoryPort.save(vendorTO), VendorModel.class);
    }

    @Override
    public VendorFullModel findOne(Integer id) throws NoFoundRegistry {
        VendorTO vendorTO = vendorRepositoryPort.findOne(id);
        log.info(id);
        log.info(vendorTO);
        if(vendorTO == null) throw new NoFoundRegistry();

        OperationEntity operationEntity = operationRepository.findOneByRegion(vendorTO.getRegion());


        VendorFullModel vendorFullModel = new VendorFullModel();
        vendorFullModel.setStates(Arrays.asList(operationEntity.getStates()));
        vendorFullModel.setVendor(vendorTO);

        return vendorFullModel;
    }

    @Override
    public List<VendorModel> findAll() {
        //TODO: Usar query parecida com essa:
        //  SELECT v.*, p.states FROM TB_VENDOR as v inner join TB_OPERATION as p on v.region = p.region
        return null;
    }
}
