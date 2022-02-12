package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.model.VendorFullModel;
import br.com.serasa.vendedores.core.model.VendorModel;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorFullTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorResumeListOneTO;
import br.com.serasa.vendedores.core.ports.in.transferobject.VendorTO;
import br.com.serasa.vendedores.core.usecase.VendorUseCase;
import br.com.serasa.vendedores.core.usecase.exception.NoFoundRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class VendorControllerImplTest {

    @InjectMocks
    VendorControllerImpl vendorController;

    @Mock
    VendorUseCase vendorUseCase;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void startMocks() {
        openMocks(this);
    }

    @Test
    void save() {
        VendorTO vendorTO = VendorTO.builder().age(20).city("Itapevi").createdAt(new Date())
                .phone("11 97800-0000").region("suldeste").id(1).build();

        VendorModel vendorModel = VendorModel.builder().id(20).city("Itapevi").build();

        when(modelMapper.map(vendorTO, VendorModel.class)).thenReturn(new VendorModel());
        when(vendorUseCase.save(any())).thenReturn(vendorModel);
        when(modelMapper.map(vendorModel, VendorTO.class)).thenReturn(vendorTO);

        ResponseEntity<VendorTO> responseEntity = vendorController.save(vendorTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(vendorTO.getRegion(), responseEntity.getBody().getRegion());
        assertEquals(vendorTO.getPhone(), responseEntity.getBody().getPhone());
        assertEquals(vendorTO.getId(), responseEntity.getBody().getId());
    }

    @Test
    void find() throws NoFoundRegistry {

        VendorResumeListOneTO vendorResumeListOneTO = VendorResumeListOneTO.builder().region("suldeste")
                .states(List.of("SP", "RJ", "MG", "ES")).createdAt(new Date()).build();

        when(vendorUseCase.findOne(any())).thenReturn(new VendorFullModel());
        when(modelMapper.map(any(), eq(VendorResumeListOneTO.class))).thenReturn(vendorResumeListOneTO);

        ResponseEntity<VendorResumeListOneTO> responseEntity = vendorController.find(1);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vendorResumeListOneTO.getRegion(), responseEntity.getBody().getRegion());
        assertEquals(vendorResumeListOneTO.getCreatedAt(), responseEntity.getBody().getCreatedAt());
    }

    @Test
    void findThrowsNoContent() throws NoFoundRegistry {

        VendorResumeListOneTO vendorResumeListOneTO = VendorResumeListOneTO.builder().region("suldeste")
                .states(List.of("SP", "RJ", "MG", "ES")).createdAt(new Date()).build();

        when(vendorUseCase.findOne(any())).thenThrow(new NoFoundRegistry());

        ResponseEntity<VendorResumeListOneTO> responseEntity = vendorController.find(1);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void findAll() throws NoFoundRegistry {

        List<VendorFullTO> vendorFullTOS = new ArrayList<>();
        vendorFullTOS.add(new VendorFullTO("José", "11 97800-4747", 1, "Itapevi", "SP", List.of("SP", "RJ", "MG", "ES")));
        vendorFullTOS.add(new VendorFullTO("José", "11 97800-4747", 1, "Itapevi", "SP", List.of("SP", "RJ", "MG", "ES")));

        when(vendorUseCase.findAll()).thenReturn(new ArrayList<>());
        when(modelMapper.map(any(), eq(new TypeToken<List<VendorFullTO>>() {
        }.getType()))).thenReturn(vendorFullTOS);

        ResponseEntity<List<VendorFullTO>> responseEntity = vendorController.findAll();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vendorFullTOS.size(), 2);
        assertEquals(vendorFullTOS.get(0).getName(), responseEntity.getBody().get(0).getName());
        assertEquals(vendorFullTOS.get(0).getAge(), responseEntity.getBody().get(0).getAge());
        assertEquals(vendorFullTOS.get(0).getStates().get(0), responseEntity.getBody().get(0).getStates().get(0));
    }

    @Test
    void findAllThrowsNoContent() throws NoFoundRegistry {

        VendorResumeListOneTO vendorResumeListOneTO = VendorResumeListOneTO.builder().region("suldeste")
                .states(List.of("SP", "RJ", "MG", "ES")).createdAt(new Date()).build();

        when(vendorUseCase.findAll()).thenThrow(new NoFoundRegistry());

        ResponseEntity<List<VendorFullTO>> responseEntity = vendorController.findAll();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }
}