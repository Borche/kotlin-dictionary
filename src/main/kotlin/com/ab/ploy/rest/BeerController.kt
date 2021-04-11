/* Copyright © 2021 */
package com.ab.ploy.rest

import com.ab.ploy.models.Beer
import com.ab.ploy.services.BeerService
import com.faunadb.client.errors.BadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
  fun dummyBeer(@RequestBody beer: Beer): Beer {
    return Beer("Dummy", "4,2%")
  }

  @DeleteMapping("/{name}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  fun deleteBeer(@PathVariable name: String) {
    beerService.deleteBeer(name)
  }

  /*
   * -- Exception handling --
   */

  @ExceptionHandler(BadRequestException::class)
  fun instanceNotUnique(e: BadRequestException): ResponseEntity<ErrorResponse> {
    if (e.message?.contains("unique") == true)
        return ResponseEntity.badRequest().body(ErrorResponse("Beer must be unique"))
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
  }
}
