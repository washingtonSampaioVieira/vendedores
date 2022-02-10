package br.com.serasa.vendedores.core.ports.out;

import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;
import br.com.serasa.vendedores.infra.persistence.entity.VendorEntity;
import br.com.serasa.vendedores.infra.persistence.repositories.crud.VendorRepository;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

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
    public VendorTO findOne(Integer id) {
        log.info("Ralizando busca de um vendedor, id: " + id);
        return modelMapper.map(vendorRepository.findAllById(Collections.singleton(id)), VendorTO.class );
    }

    @Override
    public List<VendorTO> findAll() {
        return null;
    }
}
