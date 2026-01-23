<template>
  <v-container class="pa-4">

    <h1 class="mb-4">Mentions légales</h1>

    <v-card class="pa-4 mb-4 elevation-2">
      <h2>Éditeur du site</h2>
      <p>
        Nom du site : <strong>GeekLab</strong><br>
        Éditeur : <strong>Arnaud Wissocq</strong><br>
        Email : <a href="mailto:contact@example.com">arnaudwq@gmail.com</a><br>
      </p>
    </v-card>

    <v-card class="pa-4 mb-4 elevation-2">
      <h2>Propriété intellectuelle</h2>
      <p>
        Tous les contenus présents sur ce site (textes, images, logos, icônes, sons, logiciels, etc.) sont protégés par le droit de la propriété intellectuelle.
        Toute reproduction, distribution, modification, ou usage commercial non autorisé est interdite.
      </p>
    </v-card>

    <v-card class="pa-4 mb-4 elevation-2">
      <h2>Données personnelles</h2>
      <p>
        Les données collectées sur ce site sont utilisées uniquement pour le fonctionnement de l'application.  
        Conformément au RGPD, vous pouvez exercer vos droits d'accès, de rectification et de suppression en nous contactant à l'adresse email indiquée ci-dessus.
      </p>
    </v-card>

    <v-card class="pa-4 mb-4 elevation-2">
      <h2>Responsabilité</h2>
      <p>
        L'éditeur ne peut être tenu responsable des dommages directs ou indirects pouvant résulter de l'accès ou de l'utilisation de ce site.
      </p>
    </v-card>

  </v-container>
</template>

<script>
export default {
  name: "MentionsLegales"
};
</script>

<style scoped>
h1 {
  font-weight: 700;
}
h2 {
  font-weight: 600;
  margin-bottom: 8px;
}
p {
  line-height: 1.6;
}
</style>
