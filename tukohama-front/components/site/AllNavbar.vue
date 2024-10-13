<template>
  <v-container class="mb-0 pb-0 mt-0 pt-0">
    <v-app-bar flat color="white">
      <v-app-bar-nav-icon
        class="d-lg-none"
        @click="drawer = true"
      ></v-app-bar-nav-icon>
      <v-bottom-navigation
        v-model="value"
        :background-color="$store.state.layout.color"
        class="d-none d-md-block text-center"
        dark
        grow
      >
        <v-btn
          v-for="categoria in $store.state.categoria.categorias"
          :key="categoria.id"
          @click="buscar(categoria)"
        >
          <span>{{ categoria.nombre }}</span>
          <v-icon>{{ categoria.icono }}</v-icon>
        </v-btn>
      </v-bottom-navigation>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" absolute temporary>
      <v-list nav dense>
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item
            v-for="categoria in $store.state.categoria.categorias"
            :key="categoria.id"
          >
            <v-list-item-icon>
              <v-icon>{{ categoria.icono }}</v-icon>
            </v-list-item-icon>
            <v-list-item-title>{{ categoria.nombre }}</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </v-container>
</template>

<script>
export default {
  data () {
    return {
      color: "#1eafca",
      drawer: false,
      group: null,
      value: 0,
    };
  },
  methods: {
    buscar(cat) {
      this.$store.commit("setColorNav", cat.color);
      window.location.href = "/categoria/" + cat.nombre;
    },
  },
};
</script>
