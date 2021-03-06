package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query(value = "SELECT p FROM Promocao p WHERE p.preco <= :preco and p.qtdDias > :dias")
    fun findByPrecoMenorque(@Param("preco") preco: Double, @Param("dias") quantidadeDias: Int): List<Promocao>

    @Query(value = "SELECT p.qtdDias FROM Promocao p WHERE p.local IN :names")
    fun findByLocalInList(@Param("names") names: List<String>): List<Promocao>

    @Modifying
    @Transactional
    @Query(value = "UPDATE Promocao p SET p.preco = :valor WHERE p.local = :local")
    fun updateByLocal(@Param("valor") preco: Double, @Param("local") local: String)

}