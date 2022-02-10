package br.com.serasa.vendedores.core.ports.out;

import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;
import br.com.serasa.vendedores.infra.persistence.entity.OperationEntity;
import br.com.serasa.vendedores.infra.persistence.repositories.crud.OperationRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class OperationRepositoryPortImpl implements OperationRepositoryPort {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OperationRepository operationRepository;

    @Override
    public OperationTO save(OperationTO operationTO) {
        log.info("Criando um novo registro de atuação, região: " + operationTO.getRegion());
        OperationEntity operationEntity = modelMapper.map(operationTO, OperationEntity.class);

        return modelMapper.map(operationRepository.save(operationEntity), OperationTO.class);
    }

    @Override
    public OperationTO findOne(Integer id) {
        return null;
    }

    @Override
    public List<OperationTO> findAll() {
        return null;
    }
}
