package ERP.BackEnd_ERP;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ERP.BackEnd_ERP.model.Besoin;
import ERP.BackEnd_ERP.model.Company;
import ERP.BackEnd_ERP.service.BesoinService;
import ERP.BackEnd_ERP.service.CompanyService;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class FourthSprintTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BesoinService besoinService;


    @MockBean
    private CompanyService companyService;


    @Test
    void findAllBesoins() throws Exception {
        Besoin b = new Besoin();
        b.setId(1L);
        b.setTitle("Test");
        Mockito.when(besoinService.findAllBesoin()).thenReturn(List.of(b));

        mockMvc.perform(get("/api/besoin/all"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("[0].title").value("Test"));
    }


    @Test
    void findAllCompanies() throws Exception {
        Company c = new Company();
        c.setId(20L);
        c.setName("Test");
        Mockito.when(companyService.findAllCompanies()).thenReturn(List.of(c));

        mockMvc.perform(get("/api/company/all"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("[0].name").value("Test"));
    }
    
}
