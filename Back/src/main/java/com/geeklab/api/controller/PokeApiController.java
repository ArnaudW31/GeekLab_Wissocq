package com.geeklab.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geeklab.api.service.PokeApiService;

import tools.jackson.databind.JsonNode;

@RestController
@RequestMapping("/api/poke")
public class PokeApiController {
    private final PokeApiService pokeApiService;

    public PokeApiController(PokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    @GetMapping("/items")
    public JsonNode getItems(){
        return this.pokeApiService.getItemList();
    }

    @GetMapping("/items/{name}")
    public JsonNode getItemInfo(@PathVariable String name){
        return this.pokeApiService.getItemInfo(name);
    }

    @GetMapping("/pokemon/{name}")
    public JsonNode getPokeInfo(@PathVariable String name){
        return this.pokeApiService.getPokeInfo(name);
    }

    @GetMapping("/pokemon")
    public JsonNode getPokeList(){
        return this.pokeApiService.getPokeList();
    }
}
