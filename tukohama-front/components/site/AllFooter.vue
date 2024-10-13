<template>
  <div class="fondoFooter text-center">
    <v-row>
      <v-card-text>
        <v-btn
          v-for="categoria in $store.state.categoria.categorias"
          :key="categoria.id"
          text
          rounded
          class="my-2 letraFooter"
          @click="buscar(categoria)"
        >
          <v-icon class="mr-2 letraFooter">{{ categoria.icono }}</v-icon
          >{{ categoria.nombre }}
        </v-btn>
      </v-card-text>
    </v-row>
    <v-row>
      <v-col
        cols="12"
        md="6"
        align-self="center"
        align="center"
        class="letraFooter"
      >
        <NuxtLink to="/"
          ><v-img src="/logo-footer.png" max-width="500"
        /></NuxtLink>
        <v-btn
          v-for="icon in icons"
          :key="icon"
          class="mx-4 letraSecundaria"
          icon
        >
          <v-icon size="24px">
            {{ icon }}
          </v-icon>
        </v-btn>
      </v-col>
      <v-col cols="12" md="6">
        <v-card-text class="letraFooter pt-0">
          Un sitio ideado para que te enteres de todo, sociedad, policiales,
          eventos.
        </v-card-text>
      </v-col>
    </v-row>
    <v-divider></v-divider>
    <v-card-text class="letraSecundaria">
      {{ new Date().getFullYear() }} —
      <strong>GLOIT Soluciones Informaticas</strong>
    </v-card-text>
  </div>
</template>

<script>
export default {
  data: () => ({
    icons: ["mdi-facebook", "mdi-twitter", "mdi-linkedin", "mdi-instagram"],
  }),
  methods: {
    buscar(cat) {
      this.$store.commit("setColorNav", cat.color);
      window.location.href = "/categoria/" + cat.nombre;
    },
  },
};
</script>

<style>
.fondoFooter {
  background-color: #2dd0af !important;
}
.letraFooter {
  color: #ffff !important;
}
.letraSecundaria {
  color: #272f51 !important;
}
</style>
