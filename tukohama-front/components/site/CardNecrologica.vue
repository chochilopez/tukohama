<template>
  <v-card class="mx-auto" max-width="400">
    <v-card-title>
      <v-icon left>
        {{ necrologica.religion.icono }}
      </v-icon>
      <h4>{{ necrologica.nombre }}</h4>
    </v-card-title>
    <v-card-text class="text-h7">
      {{ necrologica.cuerpo }}
    </v-card-text>
  </v-card>
</template>

<script>
import Helper from "~/services/helper";

export default {
  props: {
    necrologica: {
      type: Object,
      default: () => ({
        id: 0,
        religion: {
          icono: "mdi-bullhorn",
          nombre: "",
        },
        nombre: "",
        creado: "",
      }),
    },
  },
  data () {
    return {
      fecha: null,
    };
  },
  mounted() {
    this.fecha = Helper.getFormatoFecha(this.necrologica.creado);
  },
};
</script>
