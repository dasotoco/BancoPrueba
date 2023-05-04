package com.banco.bancoprueba.repository;

import com.banco.bancoprueba.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, String> {
    @Query("SELECT saldo FROM Cuenta WHERE numeroCuenta = :numCuenta")
    Double findSaldoByNumeroCuenta(@Param("numCuenta") String numCuenta);
}
