<template>
  <v-footer
    color="primary"
    dark
    class="d-flex align-center"
  >
    <v-container class="pa-4">
      <v-row align="center" justify="space-between">

        <!-- Nom du site / copyright -->
        <v-col cols="12" md="4" class="text-center text-md-start mb-2 mb-md-0">
          2026 GeekLab Pokémon. Tous droits réservés.
        </v-col>

        <!-- Liens -->
        <v-col cols="12" md="8" class="text-center text-md-end">
          <v-btn text color="white" to="/mentions">Mentions légales</v-btn>
        </v-col>

      </v-row>
    </v-container>
  </v-footer>
</template>

<script>
export default {
  name: "PokeFooter"
};
</script>

<style scoped>
v-footer {
  border-top: 2px solid rgba(255, 255, 255, 0.3);
  max-height: 60px;
}
</style>
