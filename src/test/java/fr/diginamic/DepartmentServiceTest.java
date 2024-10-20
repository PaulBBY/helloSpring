package fr.diginamic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import fr.diginamic.hello.dto.DepartmentDto;
import fr.diginamic.hello.exceptions.CustomEmptyDepartmentExceptions;
import fr.diginamic.hello.exceptions.CustomExceptions;
import fr.diginamic.hello.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentService service;

    @Mock
    private DepartmentService mockService;

    private DepartmentDto insertTest;

    @BeforeEach
    void setUp() {
        insertTest = new DepartmentDto();
        insertTest.setDepartmentCode("34");
        insertTest.setDepartmentName("Test");
    }

    @Test
    public void testAjouterVilleNomExistant() throws CustomExceptions, CustomEmptyDepartmentExceptions {
        when(mockService.insertDepartment(insertTest)).thenThrow(new CustomExceptions("The code of the department must be unique !"));

        assertThrows(CustomExceptions.class, () -> {
            service.insertDepartment(insertTest);
        });
    }
}