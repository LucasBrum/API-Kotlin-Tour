package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query(value = "SELECT p FROM Promocao p WHERE p.preco <= 9000")
    fun findByPrecoMenorque9000(): List<Promocao>

}