package br.com.serasa.vendedores.infra.persistence.repositories.crud;

import br.com.serasa.vendedores.core.ports.out.transferobject.OperationTO;
import br.com.serasa.vendedores.infra.persistence.entity.OperationEntity;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

    OperationEntity findOneByRegion(String region);
}
