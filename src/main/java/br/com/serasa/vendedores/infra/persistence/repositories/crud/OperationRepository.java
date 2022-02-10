package br.com.serasa.vendedores.infra.persistence.repositories.crud;

import br.com.serasa.vendedores.infra.persistence.entity.OperationEntity;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface OperationRepository extends CrudRepository<OperationEntity, CriteriaBuilder.In> {
}
