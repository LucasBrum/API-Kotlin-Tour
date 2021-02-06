package com.acme.tour

import com.acme.tour.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TourApplication {
	companion object  {
		val initialPromocoes = arrayOf(
			Promocao(1, "Maravilhosa viagem a Cancun", "Cancun", true, 7, 4999.99),
			Promocao(2, "Trila parque da Tijuca", "Rio de Janeiro", true, 2, 399.00),
			Promocao(3, "Viagem cultural em Madrid", "Madrid", true, 10, 5500.00),
			Promocao(4, "Passeio em Família", "Gramado", false, 4, 999.00)
		)
	}
	@Bean
	fun promocoes(): ConcurrentHashMap<Long, Promocao> {
		return ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))
	}
}

fun main(args: Array<String>) {
	runApplication<TourApplication>(*args)
}
