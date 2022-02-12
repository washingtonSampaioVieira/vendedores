package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.model.OperationModel;
import br.com.serasa.vendedores.core.ports.in.transferobject.OperationTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.OperationUseCase;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/atuacao")
public class OperationControllerImpl implements OperationController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OperationUseCase operationUseCase;

    @Override
    @PostMapping
    public ResponseEntity<OperationTO> save(@RequestBody OperationTO operationTO) {
        OperationModel operationModelSaves = operationUseCase.save(modelMapper.map(operationTO, OperationModel.class));

        OperationTO operationTOReturn = modelMapper.map(operationModelSaves, OperationTO.class);
        log.info("Cadastro de nova operação consluido, ID criado: " + operationTOReturn.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(operationTOReturn);
    }

    @Override
    public ResponseEntity<OperationTO> find(Integer id) {
        return null;
    }
}
