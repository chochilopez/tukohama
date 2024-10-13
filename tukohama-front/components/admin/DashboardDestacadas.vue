<template>
  <div>
    <v-card outlined class="mb-5">
      <v-card-title> Noticias destacadas </v-card-title>
      <v-card-text>
        1 noticia ocupa toda la pantalla, 2 noticias ocupan media pantalla cada
        una. 3 noticias, la primera ocupa media pantalla y las restantes 1
        cuarto cada una. 4 noticias ocupan 1 cuarto cada una.
      </v-card-text>
      <v-row class="pa-5">
        <v-col cols="6" md="3">
          <v-select
            v-model="destacada1"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-1-circle"
            item-text="titulo"
            item-value="id"
            clearable
            :items="comboDestacablesPortada"
            label="Elegí la noticia destacada"
          />
        </v-col>
        <v-col cols="6" md="3">
          <v-select
            v-model="destacada2"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-2-circle"
            item-text="titulo"
            item-value="id"
            clearable
            :items="comboDestacablesPortada"
            label="Elegí la noticia destacada"
          />
        </v-col>
        <v-col cols="6" md="3">
          <v-select
            v-model="destacada3"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-3-circle"
            item-text="titulo"
            item-value="id"
            clearable
            :items="comboDestacablesPortada"
            label="Elegí la noticia destacada"
          />
        </v-col>
        <v-col cols="6" md="3">
          <v-select
            v-model="destacada4"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-4-circle"
            item-text="titulo"
            item-value="id"
            clearable
            :items="comboDestacablesPortada"
            label="Elegí la noticia destacada"
          />
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      comboDestacablesPortada: [],
    };
  },
  computed: {
    destacada1: {
      get() {
        return this.$store.state.layout.layout.destacada1;
      },
      set(value) {
        this.$store.commit("setDestacada1", value);
      },
    },
    destacada2: {
      get() {
        return this.$store.state.layout.layout.destacada2;
      },
      set(value) {
        this.$store.commit("setDestacada2", value);
      },
    },
    destacada3: {
      get() {
        return this.$store.state.layout.layout.destacada3;
      },
      set(value) {
        this.$store.commit("setDestacada3", value);
      },
    },
    destacada4: {
      get() {
        return this.$store.state.layout.layout.destacada4;
      },
      set(value) {
        this.$store.commit("setDestacada4", value);
      },
    },
  },
  mounted() {
    for (const prop in this.$store.state.noticia.destacablesPortada)
      this.comboDestacablesPortada.push(this.$store.state.noticia.destacablesPortada[prop]);
  },
};
</script>
