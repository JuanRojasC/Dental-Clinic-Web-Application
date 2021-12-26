package clinicaodontologicadrrojas.controller;

import clinicaodontologicadrrojas.entities.Domicilio;
import clinicaodontologicadrrojas.entities.Paciente;
import clinicaodontologicadrrojas.entities.Rol;
import clinicaodontologicadrrojas.entities.Usuario;
import clinicaodontologicadrrojas.utilities.Json;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void guardarPaciente() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setNombre("Juan David");
        paciente.setApellido("Rojas Cañizales");
        paciente.setDomicilio(new Domicilio(null, "Central", 2, "bogota", "cundinamarca"));
        paciente.setUsuario(new Usuario(null,"juanrojas@email", "123456788", new Rol("PACIENTE")));
        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(paciente)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
    }

    @Test
    void buscarPacientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/paciente/list")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void actualizarPaciente() throws Exception {
        this.guardarPaciente();
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Juan David");
        paciente.setApellido("Rojas Cañizales");
        paciente.setDomicilio(new Domicilio(null, "Central", 2, "Bogota", "Cundinamarca"));
        paciente.setUsuario(new Usuario(null,"juanrojasactualizado@email", "123456788", new Rol("PACIENTE")));
        mockMvc.perform(MockMvcRequestBuilders.put("/paciente/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(paciente)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
    }

    @Test
    void buscarPaciente() throws Exception {
        this.guardarPaciente();
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders.get("/paciente/{id}",1)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertFalse((response.getResponse()).getContentAsString().isEmpty());

    }

    @Test
    void eliminarPaciente() throws Exception {
        this.guardarPaciente();
        mockMvc.perform(MockMvcRequestBuilders.delete("/paciente/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

}