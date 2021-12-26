package clinicaodontologicadrrojas.controller;

import clinicaodontologicadrrojas.entities.*;
import clinicaodontologicadrrojas.utilities.Json;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TurnoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void guardarTurno() throws Exception {
        Odontologo odontologo = new Odontologo(null,
                "Juan David",
                "Rojas Cañizales",
                new Usuario(null,  "juanrojas@email", "123456", new Rol("ODONTOLOGO")),
                null, null, null, null, 669845588L, null, null);
        Paciente paciente = new Paciente();
        paciente.setNombre("Juan David");
        paciente.setApellido("Rojas Cañizales");
        paciente.setDomicilio(new Domicilio(null, "Central", 2, "bogota", "cundinamarca"));
        paciente.setUsuario(new Usuario(null,"juanrojas@email", "123456788", new Rol("PACIENTE")));
        this.guardarPaciente(paciente);
        this.guardarOdontologo(odontologo);
        paciente.setId(1L);
        odontologo.setId(1L);
        Turno turno = new Turno(null, new Date(),paciente, odontologo);
        mockMvc.perform(MockMvcRequestBuilders.post("/turno/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(turno)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();

    }

    @Test
    void buscarTurnos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/turno/list")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void eliminarTurno() {
    }

    @Test
    void actualizarTurno() throws Exception {
        this.guardarTurno();
        Odontologo odontologo = new Odontologo(null,
                "Juan David actualizado",
                "Rojas Cañizales ",
                new Usuario(null,  "juanrojasactualizado@email", "123456", new Rol("ODONTOLOGO")),
                null, null, null, null, 669845588L, null, null);
        Paciente paciente = new Paciente();
        paciente.setNombre("Juan David actualizado");
        paciente.setApellido("Rojas Cañizales");
        paciente.setDomicilio(new Domicilio(null, "Central", 2, "bogota", "cundinamarca"));
        paciente.setUsuario(new Usuario(null,"juanrojasactualizado@email", "123456788", new Rol("PACIENTE")));
        this.guardarPaciente(paciente);
        this.guardarOdontologo(odontologo);
        paciente.setId(1L);
        odontologo.setId(1L);
        Turno turno = new Turno(1L, new Date(),paciente, odontologo);
        mockMvc.perform(MockMvcRequestBuilders.put("/turno/settings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(turno)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void buscarTurno() {
    }

    @Test
    void guardarPaciente(Paciente paciente) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/paciente/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(paciente)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    void guardarOdontologo(Odontologo odontologo) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/odontologo/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Json.asJsonString(odontologo)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }
}