package com.banco.bancoprueba.service;


import com.banco.bancoprueba.entity.Cuenta;
import com.banco.bancoprueba.entity.Movimiento;
import com.banco.bancoprueba.repository.MovimientoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;
    @Autowired
    CuentaService cuentaService;

    public List<Movimiento> listMovimientos() { return movimientoRepository.findAll(); }
    public Optional<Movimiento> getMovimiento(UUID id) { return movimientoRepository.findById(id); }
    public void deleteMovimiento(UUID id) { movimientoRepository.deleteById(id); }
    public boolean existsById(UUID id){ return movimientoRepository.existsById(id); }

    public String saveMovimiento(Movimiento movimiento) {
        String res;
        Optional<Cuenta> cuenta = cuentaService.getCuenta(movimiento.getCuentaId());
        if (cuenta.isEmpty()) {
            res = "La cuenta no existe";
        } else if ("Debito".equals(movimiento.getTipoMovimiento()) &&
                (cuenta.get().getSaldo() - movimiento.getValor() < 0.0)) {
            res = "Saldo insuficiente";
        } else {
            movimiento.setSaldoInicial(cuenta.get().getSaldo());
            cuenta.get().setSaldo("Debito".equals(movimiento.getTipoMovimiento()) ?
                    cuenta.get().getSaldo() - movimiento.getValor() :
                    cuenta.get().getSaldo() + movimiento.getValor());
            cuentaService.saveCuenta(cuenta.get());
            movimiento.setSaldoDisponible(cuenta.get().getSaldo());
            movimientoRepository.save(movimiento);
            res = "Movimiento creado, saldo disponible: ".concat(movimiento.getSaldoDisponible().toString());
        }
        return res;
    }

}
