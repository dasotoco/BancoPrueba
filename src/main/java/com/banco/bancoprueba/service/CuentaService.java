package com.banco.bancoprueba.service;

import com.banco.bancoprueba.entity.Cuenta;
import com.banco.bancoprueba.repository.CuentaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    public List<Cuenta> listCuentas(){
        return  cuentaRepository.findAll();
    }
    public Optional<Cuenta> getCuenta(String cuenta){
        return  cuentaRepository.findById(cuenta);
    }
    public void saveCuenta(Cuenta cuenta){ cuentaRepository.save(cuenta); }
    public void deleteCuenta(String cuenta){
        cuentaRepository.deleteById(cuenta);
    }
    public boolean existsById(String cuenta){ return cuentaRepository.existsById(cuenta); }
    public Double findSaldoByNumeroCuentambers(String cuenta) {
        return cuentaRepository.findSaldoByNumeroCuenta(cuenta);
    }
}
