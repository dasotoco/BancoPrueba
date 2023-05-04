package com.banco.bancoprueba.controller;

import com.banco.bancoprueba.dto.ClienteDto;
import com.banco.bancoprueba.dto.Mensaje;
import com.banco.bancoprueba.entity.Cliente;
import com.banco.bancoprueba.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listClientes(){

        List<Cliente> clientes = clienteService.listClientes();
        return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable("id") UUID id){

        if (!clienteService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el cliente"), HttpStatus.NOT_FOUND);

        Cliente cliente = clienteService.getCliente(id).get();
        return new ResponseEntity(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody ClienteDto clienteDto){

//        if(StringUtils.isBlank(movimientoDto.getId()))
//            return new ResponseEntity(new Mensaje("El  es obligatorio"), HttpStatus.BAD_REQUEST);
        clienteService.saveCliente(Cliente.builder()
                .id(UUID.randomUUID())
                .estado(clienteDto.getEstado())
                .contrasena(clienteDto.getContrasena())
                .direccion(clienteDto.getDireccion())
                .edad(clienteDto.getEdad())
                .genero(clienteDto.getGenero())
                .identificacion(clienteDto.getIdentificacion())
                .nombre(clienteDto.getNombre())
                .telefono(clienteDto.getTelefono())
                .build());
        return new ResponseEntity(new Mensaje("Cliente creado"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable("id") UUID id, @RequestBody ClienteDto clienteDto){

        if (!clienteService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el cliente"), HttpStatus.NOT_FOUND);

//        if(StringUtils.isBlank(movimientoDto.getId()))
//            return new ResponseEntity(new Mensaje("El  es obligatorio"), HttpStatus.BAD_REQUEST);

        clienteService.saveCliente(Cliente.builder()
                .id(clienteService.getCliente(id).get().getId())
                .estado(clienteDto.getEstado())
                .contrasena(clienteDto.getContrasena())
                .direccion(clienteDto.getDireccion())
                .edad(clienteDto.getEdad())
                .genero(clienteDto.getGenero())
                .identificacion(clienteDto.getIdentificacion())
                .nombre(clienteDto.getNombre())
                .telefono(clienteDto.getTelefono())
                .build());
        return new ResponseEntity(new Mensaje("Cliente actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") UUID id){
        if (!clienteService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el cliente"), HttpStatus.NOT_FOUND);
        clienteService.deleteCliente(id);
        return new ResponseEntity(new Mensaje("Cliente eliminado"), HttpStatus.OK);
    }
}
