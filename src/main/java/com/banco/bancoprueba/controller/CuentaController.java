package com.banco.bancoprueba.controller;


import com.banco.bancoprueba.dto.CuentaDto;
import com.banco.bancoprueba.dto.Mensaje;
import com.banco.bancoprueba.entity.Cuenta;
import com.banco.bancoprueba.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> listCuentas(){

        List<Cuenta> cuentas = cuentaService.listCuentas();
        return new ResponseEntity<List<Cuenta>>(cuentas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuenta(@PathVariable("id") String numCuenta){

        if (!cuentaService.existsById(numCuenta))
            return new ResponseEntity(new Mensaje("No existe la cuenta"), HttpStatus.NOT_FOUND);

        Cuenta cuenta = cuentaService.getCuenta(numCuenta).get();
        return new ResponseEntity(cuenta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCuenta(@RequestBody CuentaDto cuentaDto){

        cuentaService.saveCuenta(Cuenta.builder()
                .numeroCuenta(cuentaDto.getNumeroCuenta())
                .tipoCuenta(cuentaDto.getTipoCuenta())
                .estado(cuentaDto.getEstado())
                .saldo(cuentaDto.getSaldo())
                .clienteId(cuentaDto.getIdCliente())
                .build());
        return new ResponseEntity(new Mensaje("Cuenta creada"), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCuenta(@PathVariable("id") String cuenta, @RequestBody CuentaDto cuentaDto){

        if (!cuentaService.existsById(cuenta))
            return new ResponseEntity(new Mensaje("No existe la cuenta"), HttpStatus.NOT_FOUND);

        cuentaService.saveCuenta(Cuenta.builder()
                .numeroCuenta(cuentaService.getCuenta(cuenta).get().getNumeroCuenta())
                .tipoCuenta(cuentaDto.getTipoCuenta())
                .estado(cuentaDto.getEstado())
                .saldo(cuentaDto.getSaldo())
                .clienteId(cuentaDto.getIdCliente())
                .build());
        return new ResponseEntity(new Mensaje("Cuenta actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuenta(@PathVariable("id") String cuenta){
        if (!cuentaService.existsById(cuenta))
            return new ResponseEntity(new Mensaje("No existe la cuenta"), HttpStatus.NOT_FOUND);
        cuentaService.deleteCuenta(cuenta);
        return new ResponseEntity(new Mensaje("Cuenta eliminada"), HttpStatus.OK);
    }
}
