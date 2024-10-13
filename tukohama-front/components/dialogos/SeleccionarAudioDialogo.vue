<template>
  <v-dialog v-model="dialog" max-width="700" color="#fff">
    <h1>Audios</h1>
  </v-dialog>
</template>

<script>
import Helper from "~/services/helper";

export default {
  data() {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      overlay: false,
      dialog: false,
      resolve: null,
      reject: null,
      audios: [],
    };
  },
  created() {
    this.obtenerAudios();
  },
  methods: {
    open() {
      this.dialog = true;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    obtenerAudios() {
      const respuesta = this.$store.dispatch("getAudios");
      respuesta.then((response) => {
        this.videos = Helper.ordenarArrayCreadoDesc(response.data);
      });
    },
    seleccionar(objeto) {
      this.resolve(objeto);
      this.dialog = false;
    },
    cancelar() {
      this.resolve(false);
      this.dialog = false;
    },
  },
};
</script>
