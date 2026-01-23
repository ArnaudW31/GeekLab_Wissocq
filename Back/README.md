# BIENVENUE SUR GEEKLAB

## Recap des avancées du projet

### Jour 1
Première journée avec la présentation du projet
J'ai choisi mon projet de faire des équipes de pokémons.

Choix des technos
- spring-boot pour apprendre à en faire, j'ai déjà fait des API en C# donc je ne suis pas trop perdu
- VueJS car j'en fais en entreprise et ça va améliorer ma maîtrise

Début du Back
Initialisation du Front

### Jour 2
Création du back
Création des controlleurs, services, repositories
Création des entités, des DTO

### Jour 3
Page de login
Page de création des équipes
Page de visualisation des équipes

## BACK

### Build l'image du DOCKERFILE :
docker build -t geeklab-api .

### Lancer le docker :
docker run -p 8080:8080 geeklab-api

### Faire un .env de la forme :

DB_URL=jdbc:postgresql://url/geeklab
DB_USERNAME=tonusername
DB_PASSWORD=tonmdp

Pour exécuter dans un powershell :

```powershell
Get-Content .env | ForEach-Object {
    if ($_ -and $_ -notmatch '^#') {
        $name, $value = $_ -split '=', 2
        [System.Environment]::SetEnvironmentVariable($name, $value, "Process")
    }
}
mvn spring-boot:run
```

## FRONT

### Build l'image
docker build -t vue-pokemon-app .

### Lancer le container
docker run -p 8080:80 vue-pokemon-app
