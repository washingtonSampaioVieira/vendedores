package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.ports.in.transferobject.OperationTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping(value = "/atuacao")
public class OperationControllerImpl implements OperationController {

    @Autowired
    ModelMapper modelMapper;


    @Override
    public ResponseEntity<OperationTO> save(VendorTO vendorTO) {
        return null;
    }

    @Override
    public ResponseEntity<OperationTO> find(Integer id) {
        return null;
    }
}
