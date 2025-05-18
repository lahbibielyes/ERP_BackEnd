package ERP.BackEnd_ERP;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import java.util.Optional;

import ERP.BackEnd_ERP.model.Action_crm;

import ERP.BackEnd_ERP.model.Company;

import ERP.BackEnd_ERP.service.Action_crmService;

import ERP.BackEnd_ERP.service.CompanyService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class SecondSprintControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @MockBean
    private Action_crmService actionService;

    private final ObjectMapper objectMapper = new ObjectMapper();

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

    @Test
    void findCompanyById() throws Exception {
        Company c = new Company();
        c.setId(22L);
        c.setName("ById");
        Mockito.when(companyService.findCompanyById(22L)).thenReturn(Optional.of(c));

        mockMvc.perform(get("/api/company/22"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("ById"));
    }

    @Test
    void saveCompany() throws Exception {
        Company c = new Company();
        c.setName("New");
        String json = objectMapper.writeValueAsString(c);

        mockMvc.perform(post("/api/company/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("company saved"));

        Mockito.verify(companyService).saveCompany(any( Company.class));
    }

    @Test
    void updateCompany() throws Exception {
        Company c = new Company();
        c.setId(3L);
        c.setName("Upd");
        Mockito.when(companyService.updateCompany(eq(3L), any(Company.class))).thenReturn(c);
        String json = objectMapper.writeValueAsString(c);

        mockMvc.perform(put("/api/company/update/3")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("Upd"));
    }

    @Test
    void findActionById() throws Exception {
        Action_crm a = new Action_crm();
        a.setId(5L);
        a.setDescription("Desc");
        Mockito.when(actionService.findActionById(5L)).thenReturn(a);

        mockMvc.perform(get("/api/action-crm/5"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("Desc"));
    }



    @Test
    void deleteAction() throws Exception {
        mockMvc.perform(delete("/api/action-crm/delete/6"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("Action_crm deleted successfully"));

        Mockito.verify(actionService).deleteAction(6L);
    }

   
    @Test
    void findActionsByContactId() throws Exception {
        Action_crm a1 = new Action_crm(); a1.setId(7L);
        Mockito.when(actionService.findByContactId(7L)).thenReturn(List.of(a1));

        mockMvc.perform(get("/api/action-crm/contact/7"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("[0].id").value(7));
    }



}