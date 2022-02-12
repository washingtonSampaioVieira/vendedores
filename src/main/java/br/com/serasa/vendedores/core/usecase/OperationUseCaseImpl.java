package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.OperationModel;
import br.com.serasa.vendedores.core.ports.out.OperationRepositoryPort;
import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Log4j2
@Component
public class OperationUseCaseImpl implements OperationUseCase {

    @Autowired
    OperationRepositoryPort operationRepositoryPort;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public OperationModel save(OperationModel operationModel) {
        OperationTO operationTO = operationRepositoryPort.save(modelMapper.map(operationModel, OperationTO.class));
        return modelMapper.map(operationTO, OperationModel.class);
    }

    @Override
    public OperationModel findOne(Integer id) {
        return null;
    }

    @Override
    public List<OperationModel> findAll() {
        return null;
    }

    @Override
    public OperationTO findByRegion() {
        return null;
    }
}
