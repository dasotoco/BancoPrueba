package com.banco.bancoprueba.controller;

import com.banco.bancoprueba.dto.Mensaje;
import com.banco.bancoprueba.dto.MovimientoDto;
import com.banco.bancoprueba.dto.ReporteDto;
import com.banco.bancoprueba.entity.Movimiento;
import com.banco.bancoprueba.service.MovimientoService;
import com.banco.bancoprueba.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    ReporteService reporteService;

    @GetMapping("/movimientosxcliente")
    public ResponseEntity<Movimiento> getMovimiento(@RequestParam String fechaInicio, @RequestParam String fechaFin,
                                                    @RequestParam UUID clienteId){

        List<ReporteDto> reporte = reporteService.findMovimientosXCliente(fechaInicio, fechaFin, clienteId);
        return new ResponseEntity(reporte, HttpStatus.OK);
    }

}
