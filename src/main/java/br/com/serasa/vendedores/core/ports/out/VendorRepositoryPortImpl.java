package br.com.serasa.vendedores.core.ports.out;

import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.exception.NoFoundRegistry;
import br.com.serasa.vendedores.infra.persistence.entity.VendorEntity;
import br.com.serasa.vendedores.infra.persistence.repositories.crud.VendorRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Log4j2
@Component
public class VendorRepositoryPortImpl implements VendorRepositoryPort {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    VendorRepository vendorRepository;

    @Override
    public VendorTO save(VendorTO vendorTO) {
        log.info("Criação de um registro de vendedor, nome: " + vendorTO.getName());
        var vendorEntity = modelMapper.map(vendorTO, VendorEntity.class);
        return modelMapper.map(vendorRepository.save(vendorEntity),VendorTO.class);
    }

    @Override
    public VendorTO findOne(Integer id) throws NoFoundRegistry {
        log.info("Ralizando busca de um vendedor, id: " + id);
        Optional<VendorEntity> vendorEntity = vendorRepository.findById(id);
        if(vendorEntity.isPresent()){
            return modelMapper.map(vendorEntity.get(), VendorTO.class );
        }
        throw new NoFoundRegistry();
    }

    @Override
    public List<VendorTO> findAll() {
    log.info("Ralizando busca de todos os vendedores");
        List<VendorEntity> vendorEntity = vendorRepository.findAll();
        List<VendorTO> vendorTOList = modelMapper.map(vendorEntity, new TypeToken<List<VendorTO>>() {}.getType());
        return vendorTOList;
    }
}
