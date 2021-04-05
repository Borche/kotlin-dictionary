/* Copyright © 2021 */
package com.ab.ploy.rest

import com.ab.ploy.models.Beer
import com.ab.ploy.services.BeerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/beers")
class BeerController(val beerService: BeerService) {

    @GetMapping fun getBeers(): MutableCollection<Beer>? = beerService.getBeers()

    @PostMapping
    fun createBeer(@RequestBody beer: Beer): Beer {
        println(beer)
        return beerService.createBeer(beer)
    }
}
