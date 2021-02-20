package com.acme.tour.service.impl

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl: PromocaoService {
    companion object {
        val initialPromocoes = arrayOf(
            Promocao(1, "Maravilhosa viagem a Cancun", "Cancun", true, 7, 4999.99),
            Promocao(2, "Trila parque da Tijuca", "Rio de Janeiro", true, 2, 399.00),
            Promocao(3, "Viagem cultural em Madrid", "Madrid", true, 10, 5500.00),
            Promocao(4, "Passeio em Família", "Gramado", false, 4, 999.00)
        )
    }
    var promocoes =
        ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))

    override fun getById(id: Long): Promocao? {
        return promocoes[id]
    }

    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        delete(id)
        create(promocao)
    }

    override fun searchByLocal(localFilter: String): List<Promocao> {
        return promocoes.filter {
            it.value.local.contains(localFilter, true)
        }.map (Map.Entry<Long, Promocao>::value).toList()
    }
}