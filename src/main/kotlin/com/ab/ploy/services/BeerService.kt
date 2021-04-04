package com.ab.ploy.services

import com.ab.ploy.models.Beer
import com.ab.ploy.persistence.BeerRepository
import org.springframework.stereotype.Service

@Service
class BeerService(val beerRepository: BeerRepository) {
    fun getBeers() = listOf(Beer("Carlsberg", "4,8%"))
    fun createBeer(beer: Beer) = beerRepository.create(beer)
}
