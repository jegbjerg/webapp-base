package dk.freecode.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import dk.freecode.test.IntegrationTest;

public class HomeControllerTest extends IntegrationTest {

    @Test
    public void getRootUrlShowsHome() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"));
    }
}
