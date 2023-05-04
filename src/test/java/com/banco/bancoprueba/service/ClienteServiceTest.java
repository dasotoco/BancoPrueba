package com.banco.bancoprueba.service;

import com.banco.bancoprueba.entity.Cliente;
import com.banco.bancoprueba.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @InjectMocks
    ClienteService clienteService;

    private Cliente cliente;


    @BeforeEach
    public void setup(){
        UUID id = UUID.randomUUID();
        cliente = Cliente.builder()
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
    }

    @Test
    public void whenGetCliente_thenReturnCliente(){
        given(clienteRepository.findById(any())).willReturn(Optional.of(cliente));

        System.out.println(clienteRepository);
        System.out.println(clienteService);

        Cliente getCliente = clienteService.getCliente(cliente.getId()).get();

        assertThat(getCliente).isNotNull();
    }
    @Test
    public void whenExistsCliente_thenReturnTrue(){
        given(clienteRepository.existsById(any())).willReturn(Boolean.TRUE);

        System.out.println(clienteRepository);
        System.out.println(clienteService);

        Boolean existsCliente = clienteService.existsById(cliente.getId());

        assertThat(existsCliente).isTrue();
    }
    @Test
    public void whenGetAllClientes_thenReturnClientesList(){

        Cliente cliente1 = Cliente.builder()
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

        given(clienteRepository.findAll()).willReturn(List.of(cliente,cliente1));

        List<Cliente> employeeList = clienteService.listClientes();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }
    @Test
    public void whenDeleteCliente_thenReturnOk(){
        doNothing().when(clienteRepository).deleteById(any());

        clienteService.deleteCliente(cliente.getId());

        verify(clienteRepository, times(1)).deleteById(cliente.getId());
    }
//    @Test
//    public void whenSaveCliente_thenReturnOk(){
//        given(clienteRepository.save(any())).willReturn(Optional.of(cliente));
//
//        System.out.println(clienteRepository);
//        System.out.println(clienteService);
//
//        clienteService.saveCliente(cliente);
//
//        verify(clienteRepository, times(1)).save(cliente);
//    }
}
