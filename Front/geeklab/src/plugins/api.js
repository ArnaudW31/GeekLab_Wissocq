import axios from 'axios'

const api = axios.create({
  baseURL: "http://localhost:8080/api/"
})

const externalApi = axios.create({
  baseURL: "https://pokeapi.co/api/v2/"
})

export {api, externalApi}