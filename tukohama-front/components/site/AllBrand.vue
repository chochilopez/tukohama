<template>
  <v-container class="mb-0 pb-0">
    <v-row>
      <v-col cols="12" md="4" align-self="center" align="center">
        <NuxtLink to="/"
          ><v-img
            src="/logo.png"
            max-height="100"
            max-width="400"
        /></NuxtLink>
      </v-col>
      <v-col cols="12" md="4" class="d-flex align-center mb-0 pb-0 mt-3">
        <v-text-field
          v-model="busqueda"
          append-outer-icon="mdi-bullhorn-outline"
          filled
          outlined
          shaped
          :rules="[rules.min, rules.max, rules.letras]"
          minlenght="3"
          maxlenght="40"
          clear-icon="mdi-close-circle"
          clearable
          hint="Ingresá una palabra clave para encontrar tu noticia"
          label="Buscá tu noticia"
          type="text"
          @keydown.enter="buscar"
          @click:append-outer="buscar"
        ></v-text-field>
      </v-col>
      <v-col cols="12" md="4" align-self="center" align="center">
        <v-btn class="mx-2" fab small dark color="#34495E">
          <v-icon class="text-white"> mdi-email </v-icon>
        </v-btn>
        <v-btn class="mx-2" fab small dark color="#34495E">
          <v-icon class="text-white"> mdi-facebook </v-icon>
        </v-btn>
        <v-btn class="mx-2" fab small dark color="#34495E">
          <v-icon class="text-white"> mdi-instagram </v-icon>
        </v-btn>
        <v-btn class="mx-2" fab small dark color="#34495E">
          <v-icon class="text-white"> mdi-twitter </v-icon>
        </v-btn>
        <v-btn class="mx-2" fab small dark color="#34495E">
          <v-icon class="text-white"> mdi-whatsapp </v-icon>
        </v-btn>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data () {
    return {
      busqueda: "",
      rules: {
        min: (v) => v.length >= 3 || "Al menos 3 carácteres",
        max: (v) => v.length <= 30 || "Cómo máximo 30 carácteres",
        letras: (value) => {
          const pattern = /^[A-Za-z0-9]+$/;
          return pattern.test(value) || "Debe ingresar solo letras y números";
        },
      },
    };
  },
  methods: {
    buscar() {
      if (
        this.busqueda.length >= 3 &&
        this.busqueda.length <= 30 &&
        /^[A-Za-z0-9]+$/.test(this.busqueda)
      )
        window.location.href = "/busqueda/" + this.busqueda;
    },
  },
};
</script>
