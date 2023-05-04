package com.banco.bancoprueba.controller;

import com.banco.bancoprueba.dto.Mensaje;
import com.banco.bancoprueba.dto.MovimientoDto;
import com.banco.bancoprueba.entity.Movimiento;
import com.banco.bancoprueba.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<List<Movimiento>> listMovimientos(){

        List<Movimiento> movimientos = movimientoService.listMovimientos();
        return new ResponseEntity<List<Movimiento>>(movimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimiento(@PathVariable("id") UUID id){

        if (!movimientoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el movimiento"), HttpStatus.NOT_FOUND);

        Movimiento movimiento = movimientoService.getMovimiento(id).get();
        return new ResponseEntity(movimiento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createMovimiento(@RequestBody MovimientoDto movimientoDto){

//        if(StringUtils.isBlank(movimientoDto.getId()))
//            return new ResponseEntity(new Mensaje("El  es obligatorio"), HttpStatus.BAD_REQUEST);

        String res = movimientoService.saveMovimiento(Movimiento.builder()
                .id(UUID.randomUUID())
                .tipoMovimiento(movimientoDto.getTipoMovimiento())
                .fecha(movimientoDto.getFecha())
                .valor(movimientoDto.getValor())
                .cuentaId(movimientoDto.getCuentaId())
                .build());
        if (res.contains("Ok")) {
            return new ResponseEntity(new Mensaje(res), HttpStatus.OK);
        }
        return new ResponseEntity(new Mensaje(res), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiento(@PathVariable("id") UUID id){
        if (!movimientoService.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el movimiento"), HttpStatus.NOT_FOUND);
        movimientoService.deleteMovimiento(id);
        return new ResponseEntity(new Mensaje("Movimiento eliminado"), HttpStatus.OK);
    }
}
