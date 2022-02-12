package br.com.serasa.vendedores.infra.persistence.repositories.crud;

import br.com.serasa.vendedores.infra.persistence.entity.OperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<OperationEntity, Integer> {

    Optional<OperationEntity> findFirstByRegion(String region);

}
