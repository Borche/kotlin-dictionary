/* Copyright © 2021 */
package com.ab.ploy.rest

import com.ab.ploy.models.Beer
import com.ab.ploy.services.BeerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/beers")
class BeerController(val beerService: BeerService) {

    @GetMapping fun getBeers(): MutableCollection<Beer>? = beerService.getBeers()

    @PostMapping
    fun createBeer(@RequestBody beer: Beer): Beer {
        return beerService.createBeer(beer)
    }

    @PostMapping("/dummy")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun dummyBeer(@RequestBody beer: Beer): Beer {
        return Beer("Dummy", "4,2%")
    }
}
