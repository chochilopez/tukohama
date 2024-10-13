<template>
  <div>
    <h2 class="mt-5">Portada</h2>
    <v-system-bar height="5" color="cyan" class="mb-5"></v-system-bar>
    <v-row>
      <v-col cols="12" md="6">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=1" :noticia=$store.state.noticia.arrayPortada[0] />
      </v-col>
      <v-col cols="12" md="6">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=2" :noticia=$store.state.noticia.arrayPortada[1] />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=3" :noticia=$store.state.noticia.arrayPortada[2] />
      </v-col>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=4" :noticia=$store.state.noticia.arrayPortada[3] />
      </v-col>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=5" :noticia=$store.state.noticia.arrayPortada[4] />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="6">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=6" :noticia=$store.state.noticia.arrayPortada[5] />
      </v-col>
      <v-col cols="12" md="6">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=7" :noticia=$store.state.noticia.arrayPortada[6] />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=8" :noticia=$store.state.noticia.arrayPortada[7] />
      </v-col>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=9" :noticia=$store.state.noticia.arrayPortada[8] />
      </v-col>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=10" :noticia=$store.state.noticia.arrayPortada[9] />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="6">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=11" :noticia=$store.state.noticia.arrayPortada[10] />
      </v-col>
      <v-col cols="12" md="6">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=12" :noticia=$store.state.noticia.arrayPortada[11] />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=13" :noticia=$store.state.noticia.arrayPortada[12] />
      </v-col>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=14" :noticia=$store.state.noticia.arrayPortada[13] />
      </v-col>
      <v-col cols="12" md="4">
        <CardPortada v-if="$store.state.noticia.arrayPortada.length>=15" :noticia=$store.state.noticia.arrayPortada[14] />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import CardPortada from "./CardPortada.vue"

export default {
  components: {
    CardPortada
  },
  data() {
    return {}
  },
};
</script>
