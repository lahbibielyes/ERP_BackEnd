package ERP.BackEnd_ERP;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;
import ERP.BackEnd_ERP.model.Action_besoin;
import ERP.BackEnd_ERP.model.Besoin;
import ERP.BackEnd_ERP.service.Action_besoinService;
import ERP.BackEnd_ERP.service.BesoinService;
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
public class ThirdSprintControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BesoinService besoinService;

    @MockBean
    private Action_besoinService actionService;

    private final ObjectMapper objectMapper = new ObjectMapper();


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
    void findBesoinById() throws Exception {
        Besoin b = new Besoin();
        b.setId(2L);
        b.setTitle("ById");
        Mockito.when(besoinService.findBesoinById(2L)).thenReturn(b);

        mockMvc.perform(get("/api/besoin/2"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("ById"));
    }

    @Test
    void saveBesoin() throws Exception {
        Besoin b = new Besoin();
        b.setTitle("New");
        String json = objectMapper.writeValueAsString(b);

        mockMvc.perform(post("/api/besoin/add")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("besoin saved"));

        Mockito.verify(besoinService).saveBesoin(any(Besoin.class));
    }

    @Test
    void updateBesoin() throws Exception {
        Besoin b = new Besoin();
        b.setId(3L);
        b.setTitle("Upd");
        Mockito.when(besoinService.updateBesoin(eq(3L), any(Besoin.class))).thenReturn(b);
        String json = objectMapper.writeValueAsString(b);

        mockMvc.perform(put("/api/besoin/update/3")
               .contentType(MediaType.APPLICATION_JSON)
               .content(json))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.title").value("Upd"));
    }




    @Test
    void findActionById() throws Exception {
        Action_besoin a = new Action_besoin();
        a.setId(5L);
        a.setDescription("Desc");
        Mockito.when(actionService.findActionById(5L)).thenReturn(a);

        mockMvc.perform(get("/api/action-besoin/5"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.description").value("Desc"));
    }



    @Test
    void deleteAction() throws Exception {
        mockMvc.perform(delete("/api/action-besoin/delete/6"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message").value("Action_besoin deleted successfully"));

        Mockito.verify(actionService).deleteAction(6L);
    }

    @Test
    void findActionsByBesoinId() throws Exception {
        Action_besoin a1 = new Action_besoin(); a1.setId(7L);
        Mockito.when(actionService.findByBesoinId(7L)).thenReturn(List.of(a1));

        mockMvc.perform(get("/api/action-besoin/besoin/7"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("[0].id").value(7));
    }
}
