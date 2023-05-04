package com.banco.bancoprueba.service;


import com.banco.bancoprueba.dto.ReporteDto;
import com.banco.bancoprueba.entity.Cuenta;
import com.banco.bancoprueba.entity.Movimiento;
import com.banco.bancoprueba.repository.ReporteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ReporteService {

    @Autowired
    ReporteRepository reporteRepository;

    public List<ReporteDto> findMovimientosXCliente(String fechaInicio, String fechaFin, UUID clienteId){
        return reporteRepository.findMovimientosXCliente(fechaInicio, fechaFin, clienteId);
    }
}
