package com.geeklab.api.service;

import org.springframework.web.reactive.function.client.WebClient;

import tools.jackson.databind.JsonNode;

import org.springframework.stereotype.Service;

@Service
public class PokeApiService {
    
    private final WebClient webClient;

	public PokeApiService(WebClient webClient) {
		this.webClient = webClient;
	}

	public JsonNode getItemInfo(String name) {

		return webClient.get()
				.uri("https://pokeapi.co/api/v2/item/{name}", name)
				.retrieve()
				.bodyToMono(JsonNode.class)
				.block();
	}

	public JsonNode getItemList() {

		return webClient.get()
				.uri("https://pokeapi.co/api/v2/item-category/held-items")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.map(json -> json.get("items"))
				.block();
	}

	public JsonNode getPokeInfo(String name){
		return webClient.get()
				.uri("https://pokeapi.co/api/v2/pokemon/" + name)
				.retrieve()
				.bodyToMono(JsonNode.class)
				.block();
	}

	public JsonNode getPokeList() {

		return webClient.get()
				.uri("https://pokeapi.co/api/v2/pokemon?offset=0&limit=1024")
				.retrieve()
				.bodyToMono(JsonNode.class)
				.block();
	}

}
