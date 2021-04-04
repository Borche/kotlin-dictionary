package com.ab.ploy.rest

import com.ab.ploy.models.Beer
import com.ab.ploy.services.BeerService
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("/beers")
class BeerController(val beerService: BeerService) {

    @GetMapping
    fun getBeers(): List<Beer> = beerService.getBeers()

    @PostMapping
    fun createBeer(@RequestBody beer: Beer): Beer {
        println(beer)
        return beerService.createBeer(beer)
    }
}