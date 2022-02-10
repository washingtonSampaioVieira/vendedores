package br.com.serasa.vendedores.infra.persistence.repositories.crud;

import br.com.serasa.vendedores.infra.persistence.entity.VendorEntity;
import org.springframework.data.repository.CrudRepository;

public interface VendorRepository extends CrudRepository<VendorEntity, Integer> {
}
