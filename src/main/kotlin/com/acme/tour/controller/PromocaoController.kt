package com.acme.tour.controller

import com.acme.tour.model.Promocao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping("/promocoes")
class PromocaoController {

    @Autowired
    lateinit var promocoes: ConcurrentHashMap<Long, Promocao>

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = promocoes[id]

    @PostMapping
    fun create(@RequestBody promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        promocoes.remove(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) {
        promocoes.remove(promocao.id)
        promocoes[promocao.id] = promocao
    }

    @GetMapping
    fun getAll(@RequestParam(required = false, defaultValue = "") localFilter: String) =
        promocoes.filter {
            it.value.local.contains(localFilter, true)
        }.map (Map.Entry<Long, Promocao>::value).toList()




}