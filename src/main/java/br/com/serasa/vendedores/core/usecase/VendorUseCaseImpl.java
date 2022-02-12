package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.VendorFullModel;
import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.out.VendorRepositoryPort;
import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.exception.NoFoundRegistry;
import br.com.serasa.vendedores.infra.persistence.entity.OperationEntity;
import br.com.serasa.vendedores.infra.persistence.repositories.crud.OperationRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return modelMapper.map(vendorRepositoryPort.save(vendorTO), VendorModel.class);
    }

    @Override
    public VendorFullModel findOne(Integer id) throws NoFoundRegistry {
        log.info("Buscando vendedor completo pelo id: " + id);
        VendorTO vendorTO = vendorRepositoryPort.findOne(id);
        if (vendorTO == null) throw new NoFoundRegistry();

        VendorFullModel vendorFullModel = new VendorFullModel();
        vendorFullModel.setVendor(vendorTO);
        Optional<OperationEntity> operationEntity = operationRepository.findFirstByRegion(vendorTO.getRegion());
        if (operationEntity.isPresent()) {
            log.info("Buscando atuação do vendedor, encontrado a seguinte atuação: " + operationEntity.get().getRegion());
            vendorFullModel.setStates(operationEntity.get().getStates());
        }
        return vendorFullModel;
    }

    @Override
    public List<VendorFullModel> findAll() throws NoFoundRegistry {
        log.info("Iniciando busca de todos vendedores");
        List<VendorTO> vendorTOs = vendorRepositoryPort.findAll();
        if(vendorTOs.size() == 0){
            throw new NoFoundRegistry();
        }

        List<VendorFullModel> vendorFullModels = findOperationFomVendo(vendorTOs);

        return vendorFullModels;
    }

    private List<VendorFullModel> findOperationFomVendo(List<VendorTO> vendorTOS) {
        log.info("Iniciando formatação de vendedores, adicionando locais de atuação");
        List<VendorFullModel> vendorFullModelsComplete = new ArrayList<>();
        for (VendorTO vendorTO : vendorTOS) {
            VendorFullModel vendorFullModel = new VendorFullModel();
            vendorFullModel.setVendor(vendorTO);

            Optional<OperationEntity> operationEntity = operationRepository.findFirstByRegion(vendorTO.getRegion());

            if (operationEntity.isPresent()) vendorFullModel.setStates(operationEntity.get().getStates());

            vendorFullModelsComplete.add(vendorFullModel);
        }
        return vendorFullModelsComplete;
    }
}
