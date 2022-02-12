package br.com.serasa.vendedores.core.ports.in;

import br.com.serasa.vendedores.core.model.OperationModel;
import br.com.serasa.vendedores.core.ports.in.transferobject.OperationTO;
import br.com.serasa.vendedores.core.usecase.OperationUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class OperationControllerImplTest {

    @InjectMocks
    OperationControllerImpl operationController;

    @Mock
    ModelMapper modelMapper;

    @Mock
    OperationUseCase operationUseCase;


    @BeforeEach
    void startMocks() {
        openMocks(this);
    }

    @Test
    void save() {
        OperationTO operationTO = new OperationTO();
        OperationTO operationTOResponse = new OperationTO();
        operationTOResponse.setRegion("sudeste");
        operationTOResponse.setStates(List.of("SP", "RJ", "MG", "ES"));
        operationTOResponse.setId(1);

        OperationModel operationModel = new OperationModel(1, "sudeste", List.of("SP", "RJ", "MG", "ES"));

        when(modelMapper.map(operationTO, OperationModel.class)).thenReturn(operationModel);
        when(operationUseCase.save(operationModel)).thenReturn(new OperationModel());
        when(modelMapper.map(any(), eq(OperationTO.class))).thenReturn(operationTOResponse);

        ResponseEntity<OperationTO> responseEntityOperation = operationController.save(operationTO);

        assertNotNull(responseEntityOperation);
        assertEquals(HttpStatus.CREATED, responseEntityOperation.getStatusCode());
        assertEquals(operationTOResponse.getRegion(), responseEntityOperation.getBody().getRegion());
        assertEquals(operationTOResponse.getStates().get(0), responseEntityOperation.getBody().getStates().get(0));

    }

}