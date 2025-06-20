package com.coffeevender.core.coffeeMenu.service

import com.coffeevender.core.coffeeMenu.dto.CoffeeMenuCreateCommandDto
import com.coffeevender.core.coffeeMenu.dto.CoffeeMenuDto
import com.coffeevender.core.coffeeMenu.dto.FavoriteCoffeeMenuDto
import com.coffeevender.core.coffeeMenu.error.CoffeeMenuError
import com.coffeevender.core.common.ServiceResult

interface CoffeeMenuCommandService {
    fun createCoffeeMenu(command: CoffeeMenuCreateCommandDto)
    : ServiceResult<Long, CoffeeMenuError> // menuId
}

interface CoffeeMenuQueryService {
    fun readCoffeeMenuList()
    : ServiceResult<Iterable<CoffeeMenuDto>, CoffeeMenuError>
    
    fun readCoffeeMenu(menuId: Long)
    : ServiceResult<CoffeeMenuDto, CoffeeMenuError>    
    
    fun readFavoriteCoffeeMenuList()
    : ServiceResult<Iterable<FavoriteCoffeeMenuDto>, CoffeeMenuError>
}