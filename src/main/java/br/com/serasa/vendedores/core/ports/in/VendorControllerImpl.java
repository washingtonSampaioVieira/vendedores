package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.VendorUseCase;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(value = "/vendedor")
public class VendorControllerImpl implements VendorController {

    @Autowired
    VendorUseCase vendorUseCase;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @PostMapping
    public ResponseEntity<VendorTO> save(@RequestBody @Valid  VendorTO vendorTO) {
        log.info("Novo vendedor recebido para cadastro, nome: " + vendorTO.getName());

        VendorModel vendorModelSaves = vendorUseCase.save(modelMapper.map(vendorTO, VendorModel.class));

        VendorTO vendorTOResponseBody = modelMapper.map(vendorModelSaves, VendorTO.class);

        log.info("Finalizado o cadastro de um novo vendedor, id: " + vendorTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorTOResponseBody);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendorTO> find(Integer id) {
        return null;
    }
}
