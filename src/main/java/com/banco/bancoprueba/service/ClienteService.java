package com.banco.bancoprueba.service;

import com.banco.bancoprueba.entity.Cliente;
import com.banco.bancoprueba.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> listClientes(){
        return  clienteRepository.findAll();
    }
    public Optional<Cliente> getCliente(UUID id){
        return  clienteRepository.findById(id);
    }
    public void saveCliente(Cliente cliente){
        clienteRepository.save(cliente);
    }
    public void deleteCliente(UUID id){
        clienteRepository.deleteById(id);
    }
    public boolean existsById(UUID id){ return clienteRepository.existsById(id); }

}
