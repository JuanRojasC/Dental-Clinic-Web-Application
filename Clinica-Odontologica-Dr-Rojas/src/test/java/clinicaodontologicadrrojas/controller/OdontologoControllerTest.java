package clinicaodontologicadrrojas.controller;

import clinicaodontologicadrrojas.entities.*;
import clinicaodontologicadrrojas.utilities.Json;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void guardarOdontologo() throws Exception {
        Odontologo odontologo = new Odontologo(null,
                "Juan David",
                "Rojas Cañizales",
                new Usuario(null,  "juanrojas@email", "123456", new Rol("ODONTOLOGO")),
                null, null, null, null, 669845588L, null, null);
        mockMvc.perform(MockMvcRequestBuilders.post("/odontologo/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(odontologo)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
    }

    @Test
    void buscarOdontologos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/odontologo/list")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void actualizarOdontologo() throws Exception {
        this.guardarOdontologo();
        Odontologo odontologo = new Odontologo(1L,
                "Juan David",
                "Rojas Canizales",
                new Usuario(1L,  "juanrojasactualizado@email", "123456", new Rol("ODONTOLOGO")),
                "10/02/2021", null, null, null, 669845588L, null, null);
        mockMvc.perform(MockMvcRequestBuilders.put("/odontologo/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(odontologo)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
    }

    @Test
    void buscarOdontologo() throws Exception {
        this.guardarOdontologo();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/odontologo/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse((response.getResponse()).getContentAsString().isEmpty());
    }

    @Test
    void eliminarOdontologo() throws Exception {
        this.guardarOdontologo();
        mockMvc.perform(MockMvcRequestBuilders.delete("/odontologo/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

}