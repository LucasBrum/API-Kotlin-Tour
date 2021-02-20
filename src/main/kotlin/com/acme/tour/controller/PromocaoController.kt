package com.acme.tour.controller

import com.acme.tour.model.Promocao
import com.acme.tour.service.PromocaoService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/promocoes")
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Promocao?> {
        var promocao = this.promocaoService.getById(id)
        var status = if (promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promocao, status)
    }

    @PostMapping
    fun create(@RequestBody promocao: Promocao): ResponseEntity<Unit> {
        this.promocaoService.create(promocao)
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        this.promocaoService.delete(id)
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            status = HttpStatus.ACCEPTED
            this.promocaoService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        this.promocaoService.update(id, promocao)
        var status = HttpStatus.NOT_FOUND
        if (this.promocaoService.getById(id) != null) {
            this.promocaoService.update(id, promocao)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }

    @GetMapping
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String): ResponseEntity<List<Promocao>> {
        var status = HttpStatus.OK
        val listaPromocoes = this.promocaoService.searchByLocal(localFilter)
        if (listaPromocoes.isEmpty()) {
            status = HttpStatus.NOT_FOUND
        }
        return ResponseEntity(listaPromocoes, status)
    }

}
