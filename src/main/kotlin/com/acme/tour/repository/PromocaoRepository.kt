package com.acme.tour.repository

import com.acme.tour.model.Promocao
import org.springframework.data.jpa.repository.JpaRepository

interface PromocaoRepository: JpaRepository<Promocao, Long> {

}