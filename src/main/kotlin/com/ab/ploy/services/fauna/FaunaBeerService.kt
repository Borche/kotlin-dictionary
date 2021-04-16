/* Copyright © 2021 */
package com.ab.ploy.services.fauna

import com.ab.ploy.models.fauna.FaunaBeer
import com.ab.ploy.persistence.fauna.FaunaBeerRepository
import org.springframework.stereotype.Service

@Service
class FaunaBeerService(val beerRepository: FaunaBeerRepository) {
    fun getBeers() = beerRepository.getAll()
    fun createBeer(beer: FaunaBeer) = beerRepository.create(beer)
    fun deleteBeer(name: String) = beerRepository.delete(name)
}
