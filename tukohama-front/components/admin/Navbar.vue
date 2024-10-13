<template>
  <v-toolbar color="light-blue darken-3" dark>
    <v-toolbar-title>Panel administrador</v-toolbar-title>
    <v-spacer></v-spacer>
    <span>{{ $store.state.auth.username }}</span>
    <v-btn
      class="ma-2 white--text"
      color="deep-orange darken-1"
      @click="cerrarSesion()"
    >
      <span>Cerrar sesión</span>
      <v-icon right>mdi-exit-run</v-icon>
    </v-btn>
  </v-toolbar>
</template>

<script>
export default {
  data() {
    return {};
  },
  methods: {
    cerrarSesion() {
      this.$store.dispatch("cerrarSesion");
      this.$router.push("/");
    },
  },
};
</script>
