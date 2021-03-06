package com.acme.tour.controller

import com.acme.tour.exception.PromocaoNotFoundException
import com.acme.tour.model.ErrorMessage
import com.acme.tour.model.Promocao
import com.acme.tour.model.RespostaJson
import com.acme.tour.service.PromocaoService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/promocoes")
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/menorQue{preco}/{quantidadeDias}")
    fun getAllMenores(@PathVariable preco: Double, @PathVariable quantidadeDias: Int) = this.promocaoService.getAllByPrecoMenorQue(preco, quantidadeDias)


    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val promocao = this.promocaoService.getById(id)

        return if (promocao != null)
            return ResponseEntity(promocao, HttpStatus.OK)
        else
            return ResponseEntity(ErrorMessage("Promocao nao localizada", "Promocao ${id} nao localizada"), HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun create(@RequestBody promocao: Promocao): ResponseEntity<RespostaJson> {
        this.promocaoService.create(promocao)
        val respostaJson = RespostaJson("OK", Date())
        return ResponseEntity(respostaJson, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> { //Unit é uma classe do Kotlin para retornar vazio
        this.promocaoService.update(id, promocao)
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            this.promocaoService.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping
    fun getAll(@RequestParam(required = false, defaultValue = "0") start: Int,
               @RequestParam(required = false, defaultValue = "3") size: Int): ResponseEntity<List<Promocao>> {
        val listPromocoes = this.promocaoService.getAll(start, size)

        val status = if(listPromocoes.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(listPromocoes, status)
    }

    @GetMapping("/count")
    fun count(): ResponseEntity<Map<String,Long>> =
        ResponseEntity.ok().body(mapOf("count" to this.promocaoService.count()))

    @GetMapping("/ordenados")
    fun ordenados() = this.promocaoService.getAllSortedByLocal()

}
