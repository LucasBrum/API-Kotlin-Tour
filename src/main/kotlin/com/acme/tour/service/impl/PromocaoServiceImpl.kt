package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.repository.PromocaoRepository
import com.acme.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl: PromocaoService {

    @Autowired
    lateinit var promocaoRepository: PromocaoRepository

    override fun getById(id: Long): Promocao? {
        return promocaoRepository.findById(id).orElseGet(null)
    }

    override fun create(promocao: Promocao) {
        this.promocaoRepository.save(promocao)
    }

    override fun delete(id: Long) {
        this.promocaoRepository.deleteById(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        create(promocao)
    }

    override fun searchByLocal(localFilter: String): List<Promocao> =
        listOf()

    override fun getAll(): List<Promocao> {
        return this.promocaoRepository.findAll().toList()
    }

    override fun count(): Long =
        this.promocaoRepository.count()

}