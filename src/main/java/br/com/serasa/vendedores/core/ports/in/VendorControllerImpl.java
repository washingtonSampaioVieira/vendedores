package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.model.VendorFullModel;
import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorFullTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorResumeListOneTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.VendorUseCase;
import br.com.serasa.vendedores.core.usecase.exception.NoFoundRegistry;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<VendorTO> save(@RequestBody @Validated VendorTO vendorTO) {
        log.info("Novo vendedor recebido para cadastro, nome: " + vendorTO.getName());

        VendorModel vendorModelSaves = vendorUseCase.save(modelMapper.map(vendorTO, VendorModel.class));

        VendorTO vendorTOResponseBody = modelMapper.map(vendorModelSaves, VendorTO.class);

        log.info("Finalizado o cadastro de um novo vendedor, id: " + vendorTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vendorTOResponseBody);
    }

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<VendorResumeListOneTO> find(@PathVariable Integer id) {
        VendorFullModel vendorFullModel = null;
        try {
            vendorFullModel = vendorUseCase.findOne(id);
        } catch (NoFoundRegistry noFoundRegistry) {
            log.info("Nem um registro encontrado com o id: " + id);
            return ResponseEntity.noContent().build();
        }

        VendorResumeListOneTO vendorReturn = modelMapper.map(vendorFullModel, VendorResumeListOneTO.class);
        return ResponseEntity.ok(vendorReturn);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<VendorFullTO>> findAll() {
        try {
            List<VendorFullModel> vendorFullModels = vendorUseCase.findAll();

            List<VendorFullTO> vendorFullTOS = modelMapper.map(vendorFullModels, new TypeToken<List<VendorFullTO>>() {
            }.getType());

            return ResponseEntity.ok(vendorFullTOS);
        } catch (NoFoundRegistry noFoundRegistry) {
            return ResponseEntity.noContent().build();
        }
    }

}
