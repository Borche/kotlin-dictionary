/* Copyright © 2021 */
package com.ab.ploy.services

import com.ab.ploy.models.Beer
import com.ab.ploy.persistence.BeerRepository
import org.springframework.stereotype.Service

@Service
class BeerService(val beerRepository: BeerRepository) {
    fun getBeers() = beerRepository.getAll()
    fun createBeer(beer: Beer) = beerRepository.create(beer)
    fun deleteBeer(name: String) = beerRepository.delete(name)
}
