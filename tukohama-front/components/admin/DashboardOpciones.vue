<template>
  <div>
    <v-card outlined class="mb-5">
      <v-card-title> Opciones Portada y Último Noticias </v-card-title>
      <v-card-text>
        Opciones de Portada, cantidad de noticia que se mostraran en la seccion
        portada. Primera seccion luego de Destacadas.
      </v-card-text>
      <v-row class="pa-5">
        <v-col cols="12" md="6">
          <v-slider
            v-model="noticiasPortada"
            class="align-center"
            max="15"
            min="5"
            hide-details
            thumb-label="always"
            label="Cantidad de noticias en portada"
          >
          </v-slider>
        </v-col>
        <v-col cols="12" md="6">
          <v-slider
            v-model="cantidadUltimas"
            class="align-center"
            max="4"
            min="1"
            hide-details
            thumb-label="always"
            label="Cantidad de Noticias Destacadas"
          >
          </v-slider>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      comboUltimasNoticias: [1, 2, 3, 4, 5, 6],
    };
  },
  computed: {
    noticiasPortada: {
      get() {
        return this.$store.state.layout.layout.noticiasPortada;
      },
      set(value) {
        this.$store.commit("setNoticiasPortada", value);
      },
    },
    cantidadUltimas: {
      get() {
        return this.$store.state.layout.layout.cantidadUltimas;
      },
      set(value) {
        this.$store.commit("setCantidadUltimas", value);
      },
    },
  },
};
</script>
