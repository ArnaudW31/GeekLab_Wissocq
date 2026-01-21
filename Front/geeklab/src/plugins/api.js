import axios from 'axios'

const api = axios.create({
  baseURL: "http://localhost:8080/api/"
})

// Mentionner RGPD, données sensibles (email), droit à l'oubli, mentions légales, conditions d'utilisation
// Tests unitaires / end to end
// Envoyer le git
// Déploiement
// Sécurité, mettre BDD sur 2 serveurs pas au même endroit
// Déploiement sur Docker Front + Back + BDD (générer les DOCKERFILE)
// Changer les 6 pokémons en une liste
// Piste d'amélioration : stocker les pokémons en base et mettre les instances des pokémons
// Stocker les images des pokés sur un serveur et stocker les liens dessus
// Isoler de l'API externe
export {api}