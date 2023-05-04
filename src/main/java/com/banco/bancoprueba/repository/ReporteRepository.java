package com.banco.bancoprueba.repository;

import com.banco.bancoprueba.dto.ReporteDto;
import com.banco.bancoprueba.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReporteRepository extends JpaRepository<Movimiento, UUID> {
    @Query( "SELECT new com.banco.bancoprueba.dto.ReporteDto(m.fecha, cl.nombre, cu.numeroCuenta, cu.estado, cu.tipoCuenta,  m.saldoInicial, m.valor, m.saldoDisponible) " +
            "FROM Movimiento m, Cuenta cu, Cliente cl " +
            "WHERE m.cuentaId = cu.numeroCuenta AND cu.clienteId = cl.id AND " +
            "(TO_DATE(m.fecha, 'YYYYMMDD') BETWEEN TO_DATE(:fechaInicio, 'YYYYMMDD') AND TO_DATE(:fechaFin, 'YYYYMMDD')) AND " +
            "cl.id = :clienteId" )
    List<ReporteDto> findMovimientosXCliente(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin,
                                             @Param("clienteId") UUID clienteId);
}
