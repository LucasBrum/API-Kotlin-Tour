package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param

interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query(value = "SELECT p FROM Promocao p WHERE p.preco <= :preco and p.quantidade_dias > :dias")
    fun findByPrecoMenorque(@Param("preco") preco: Double, @Param("dias") quantidadeDias: Int): List<Promocao>

    @Query(value = "SELECT p.quantidade_dias p WHERE p.local IN :names")
    fun findByLocalInList(@Param("names") names: List<String>): List<Promocao>

}