package com.banco.bancoprueba.controller;

import com.banco.bancoprueba.dto.ClienteDto;
import com.banco.bancoprueba.entity.Cliente;
import com.banco.bancoprueba.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService clienteService;

    @Test
    void shouldCreateCliente() throws Exception {
        ClienteDto cliente = ClienteDto.builder()
                .contrasena(1234)
                .estado(true    )
                .direccion("Casa")
                .edad(32)
                .genero("Masculino")
                .identificacion("445879")
                .nombre("Daniel")
                .telefono("58744796")
                .build();

        mockMvc.perform(post("/clientes").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void shouldReturnCliente() throws Exception {
        UUID id = UUID.randomUUID();
        Cliente cliente = Cliente.builder()
                .id(id)
                .contrasena(1234)
                .estado(true)
                .direccion("Casa")
                .edad(32)
                .genero("Masculino")
                .identificacion("445879")
                .nombre("Daniel")
                .telefono("58744796")
                .build();

        when(clienteService.existsById(id)).thenReturn(Boolean.TRUE);
        when(clienteService.getCliente(id)).thenReturn(Optional.of(cliente));
        mockMvc.perform(get("/clientes/{id}", id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.contrasena").value(cliente.getContrasena()))
                .andExpect(jsonPath("$.estado").value(cliente.getEstado()))
                .andExpect(jsonPath("$.direccion").value(cliente.getDireccion()))
                .andDo(print());
    }
    @Test
    void shouldReturnNotFoundCliente() throws Exception {
        UUID id = UUID.randomUUID();

        when(clienteService.existsById(id)).thenReturn(Boolean.FALSE);
        mockMvc.perform(get("/clientes/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldReturnListOfClientes() throws Exception {
        Cliente cliente1 = Cliente.builder()
                .id(UUID.randomUUID())
                .contrasena(1234)
                .estado(true)
                .direccion("Casa")
                .edad(32)
                .genero("Masculino")
                .identificacion("445879")
                .nombre("Daniel")
                .telefono("58744796")
                .build();
        Cliente cliente2 = Cliente.builder()
                .id(UUID.randomUUID())
                .contrasena(4321)
                .estado(true)
                .direccion("Aqui")
                .edad(25)
                .genero("Femenino")
                .identificacion("98756")
                .nombre("Camila")
                .telefono("97741236")
                .build();
        List<Cliente> clientes = new ArrayList<>(Arrays.asList(cliente1, cliente2));

        when(clienteService.listClientes()).thenReturn(clientes);
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(clientes.size()))
                .andDo(print());
    }
    @Test
    void shouldDeleteCliente() throws Exception {
        UUID id = UUID.randomUUID();

        when(clienteService.existsById(id)).thenReturn(Boolean.TRUE);
        doNothing().when(clienteService).deleteCliente(id);
        mockMvc.perform(delete("/clientes/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void shouldDeleteNotFoundCliente() throws Exception {
        UUID id = UUID.randomUUID();

        when(clienteService.existsById(id)).thenReturn(Boolean.FALSE);
        mockMvc.perform(delete("/clientes/{id}", id))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void shouldUpdateCliente() throws Exception {
        UUID id = UUID.randomUUID();
        Cliente cliente = Cliente.builder()
                .id(id)
                .contrasena(4321)
                .estado(true)
                .direccion("Aqui")
                .edad(25)
                .genero("Femenino")
                .identificacion("98756")
                .nombre("Camila")
                .telefono("97741236")
                .build();
        ClienteDto clienteUpdate = ClienteDto.builder()
                .contrasena(1234)
                .estado(true    )
                .direccion("Casa")
                .edad(32)
                .genero("Masculino")
                .identificacion("445879")
                .nombre("Daniel")
                .telefono("58744796")
                .build();

        when(clienteService.existsById(id)).thenReturn(Boolean.TRUE);
        when(clienteService.getCliente(id)).thenReturn(Optional.of(cliente));
        mockMvc.perform(put("/clientes/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteUpdate)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    void shouldUpdateNotFoundCliente() throws Exception {
        UUID id = UUID.randomUUID();

        ClienteDto clienteUpdate = ClienteDto.builder()
                .contrasena(1234)
                .estado(true    )
                .direccion("Casa")
                .edad(32)
                .genero("Masculino")
                .identificacion("445879")
                .nombre("Daniel")
                .telefono("58744796")
                .build();

        when(clienteService.existsById(id)).thenReturn(Boolean.FALSE);
        mockMvc.perform(put("/clientes/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteUpdate)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
}
