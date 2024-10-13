<template>
  <v-dialog v-model="dialog" max-width="700" color="#fff">
    <div class="player-container">
      <vue-core-video-player
        :src="`${PUBLIC_PATH}media/${objeto.path}`"
        resolution="360p"
        muted
        loop
      ></vue-core-video-player>
    </div>
  </v-dialog>
</template>

<script>
export default {
  data() {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      dialog: false,
      resolve: null,
      reject: null,
      objeto: {
        path: null,
      },
    };
  },
  methods: {
    open(objeto) {
      this.dialog = true;
      this.objeto.path = objeto.path;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    aceptar() {
      this.resolve(this.objeto);
      this.dialog = false;
    },
    cancelar() {
      this.resolve(false);
      this.dialog = false;
    },
  },
};
</script>
