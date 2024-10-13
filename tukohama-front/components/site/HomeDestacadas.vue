<template>
  <v-container>
    <v-row v-if="1 == 1">
      <v-col v-if="$store.state.noticia.arrayDestacadas.length == 1" cols="12">
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[0]"
          :ancho="1500"
          :alto="650"
        />
      </v-col>
      <v-col
        v-if="$store.state.noticia.arrayDestacadas.length == 2 || $store.state.noticia.arrayDestacadas.length == 3"
        cols="12"
        md="6"
      >
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[0]"
          :ancho="950"
          :alto="650"
        />
      </v-col>
      <v-col
        v-else-if="$store.state.noticia.arrayDestacadas.length == 4"
        cols="12"
        md="6"
        class="d-block"
      >
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[0]"
          :ancho="950"
          :alto="325"
        />
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[1]"
          :ancho="950"
          :alto="325"
        />
      </v-col>
      <v-col v-if="$store.state.noticia.arrayDestacadas.length == 2" cols="12" md="6">
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[1]"
          :ancho="950"
          :alto="650"
        />
      </v-col>
      <v-col
        v-else-if="$store.state.noticia.arrayDestacadas.length == 3"
        cols="12"
        md="6"
        class="d-block"
      >
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[1]"
          :ancho="950"
          :alto="325"
        />
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[2]"
          :ancho="950"
          :alto="325"
        />
      </v-col>
      <v-col
        v-else-if="$store.state.noticia.arrayDestacadas.length == 4"
        cols="12"
        md="6"
        class="d-block"
      >
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[2]"
          :ancho="950"
          :alto="325"
        />
        <CardDestacada
          :noticia="$store.state.noticia.arrayDestacadas[3]"
          :ancho="950"
          :alto="325"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import CardDestacada from "./CardDestacada.vue";

export default {
  components: {
    CardDestacada,
  },
  data() {
    return {};
  },
};
</script>
