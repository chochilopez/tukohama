<template>
  <div>
    <v-row justify="center" class="mt-1 mb-3">
      <v-col cols="6" sm="2">
        <v-card color="purple darken-1" dark max-width="200">
          <h4 class="pt-3 pl-3">Noticias Publicadas</h4>
          <v-card-title class="pt-0">
            <v-icon large left> mdi-newspaper-variant </v-icon>
            <span class="text-h3 font-weight-900">{{
              $store.state.noticia.cantidadNoticiasPublicadas
            }}</span>
          </v-card-title>
        </v-card>
      </v-col>
      <v-col cols="6" sm="2">
        <v-card color="pink darken-1" dark max-width="200">
          <h4 class="pt-3 pl-3">Noticias Borradores</h4>
          <v-card-title class="pt-0">
            <v-icon large left> mdi-newspaper-plus </v-icon>
            <span class="text-h3 font-weight-900">{{
              $store.state.noticia.cantidadNoticiasBorradores
            }}</span>
          </v-card-title>
        </v-card>
      </v-col>
      <v-col cols="6" sm="2">
        <v-card color="deep-purple darken-1" dark max-width="200">
          <h4 class="pt-3 pl-3">Visitas</h4>
          <v-card-title class="pt-0">
            <v-icon large left> mdi-account-arrow-right-outline </v-icon>
            <span class="text-h3 font-weight-900">{{
              $store.state.visita.cantidadVisitas
            }}</span>
          </v-card-title>
        </v-card>
      </v-col>
      <v-col cols="6" sm="2">
        <v-card color="light-green darken-1" dark max-width="200">
          <h4 class="pt-3 pl-3"></h4>
          <v-card-title class="pt-0">
            <v-icon large left> </v-icon>
            <span class="text-h3 font-weight-900"></span>
          </v-card-title>
        </v-card>
      </v-col>
      <v-col cols="6" sm="2">
        <v-card color="blue darken-1" dark max-width="200">
          <h4 class="pt-3 pl-3"></h4>
          <v-card-title class="pt-0">
            <v-icon large left> </v-icon>
            <span class="text-h3 font-weight-900"></span>
          </v-card-title>
        </v-card>
      </v-col>
      <v-col cols="6" sm="2">
        <v-card color="orange darken-1" dark max-width="200">
          <h4 class="pt-3 pl-3"></h4>
          <v-card-title class="pt-0">
            <v-icon large left> </v-icon>
            <span class="text-h3 font-weight-900"></span>
          </v-card-title>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  created() {
    this.contadores();
  },
  methods: {
    // eslint-disable-next-line require-await
    async contadores() {
      if (
        this.$store.state.noticia.cantidadNoticiasBorradores === false ||
        this.$store.state.noticia.cantidadNoticiasPublicadas === false ||
        this.$store.state.visita.cantidadVisitas === false
      ) {
        try {
          Promise.all([
            this.$store.dispatch("getCantidadNoticiasBorradores"),
            this.$store.dispatch("getCantidadNoticiasPublicadas"),
            this.$store.dispatch("getCantidadVisitas"),
          ]);
        } catch (error) {
          // eslint-disable-next-line no-console
          console.log("dash-count -> all -> error " + error);
        }
      } else 
      // eslint-disable-next-line no-console
      console.log("dash-count -> all -> reloaded");
    },
  },
};
</script>
