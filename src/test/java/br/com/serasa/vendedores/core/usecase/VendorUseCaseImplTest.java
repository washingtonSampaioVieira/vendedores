package br.com.serasa.vendedores.core.usecase;

import br.com.serasa.vendedores.core.model.VendorFullModel;
import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.out.VendorRepositoryPort;
import br.com.serasa.vendedores.core.ports.out.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.exception.NoFoundRegistry;
import br.com.serasa.vendedores.infra.persistence.entity.OperationEntity;
import br.com.serasa.vendedores.infra.persistence.repositories.crud.OperationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VendorUseCaseImplTest {

    @InjectMocks
    VendorUseCaseImpl vendorUseCase;

    @Mock
    ModelMapper modelMapper;

    @Mock
    VendorRepositoryPort vendorRepositoryPort;

    @Mock
    OperationRepository operationRepository;

    @BeforeEach
    void startMocks() {
        openMocks(this);
    }

    @Test
    void save() {
        VendorModel vendorModelMock = VendorModel.builder().age(20).name("Washington Sampaio").city("Itapevi").id(1).build();
        when(modelMapper.map(any(), eq(VendorTO.class))).thenReturn(new VendorTO());
        when(vendorRepositoryPort.save(any())).thenReturn(new VendorTO());

        when(modelMapper.map(any(), eq(VendorModel.class))).thenReturn(vendorModelMock);

        VendorModel vendorModelResult = vendorUseCase.save(vendorModelMock);

        assertNotNull(vendorModelResult);
        assertEquals(vendorModelMock.getAge(), vendorModelResult.getAge());
        assertEquals(vendorModelMock.getName(), vendorModelResult.getName());
        assertEquals(vendorModelMock.getId(), vendorModelResult.getId());

    }

    @Test
    void findOne() throws NoFoundRegistry {
        ArrayList<String> states = new ArrayList<>();
        states.add("SP");

        OperationEntity operationEntity = new OperationEntity();
        operationEntity.setStates(states);

        when(vendorRepositoryPort.findOne(any())).thenReturn(VendorTO.builder().region("suldeste").build());
        when(operationRepository.findFirstByRegion("suldeste")).thenReturn(Optional.of(operationEntity));

        VendorFullModel vendorFullModel = vendorUseCase.findOne(1);

        assertNotNull(vendorFullModel);

    }

    @Test
    void findOneNoFoundRegistry() throws NoFoundRegistry {
        when(vendorRepositoryPort.findOne(any())).thenReturn(null);
        assertThrows(NoFoundRegistry.class, () -> vendorUseCase.findOne(1));
    }

    @Test
    void findAll() throws NoFoundRegistry {
        Optional<OperationEntity> operationEntity = Optional.of(new OperationEntity());
        ArrayList<String> states = new ArrayList<>();
        states.add("SP");
        states.add("MG");
        operationEntity.get().setStates(states);


        when(vendorRepositoryPort.findAll()).thenReturn(List.of(VendorTO.builder().region("sul").build()));
        when(operationRepository.findFirstByRegion(any())).thenReturn(operationEntity);

        List<VendorFullModel> vendorFullModels = vendorUseCase.findAll();
        assertNotNull(vendorFullModels);
        assertEquals("sul", vendorFullModels.get(0).getRegion());
        assertEquals(1, vendorFullModels.size());
    }

    @Test
    void findAllNoFoundRegistry()  {
        List<VendorTO> vendorTOList = new ArrayList<>();
        when(vendorRepositoryPort.findAll()).thenReturn(vendorTOList);

        assertThrows(NoFoundRegistry.class, ()-> vendorUseCase.findAll());
    }
}











