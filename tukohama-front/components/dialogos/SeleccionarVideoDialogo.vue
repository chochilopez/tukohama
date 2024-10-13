<template>
  <v-dialog v-model="dialog" max-width="700" color="#fff">
    <v-card class="pa-5">
      <v-toolbar dark color="blue darken-2">
        <v-toolbar-title
          class="text-h5 font-weight-bold white--text"
          color="blue darken-1"
        >
          Seleccioná el video para la noticia
        </v-toolbar-title>
        <v-spacer />
        <v-btn color="blue lighten-2" @click="cancelar">
          <v-icon color="#fff" large>mdi-close</v-icon>
        </v-btn>
      </v-toolbar>
      <v-row justify="center">
        <v-row class="pa-5">
          <v-col cols="3" class="pa-2" v-for="video in videos" :key="video.id">
            <v-hover>
              <template #default="{ hover }">
                <v-card class="mx-auto ma-2" max-width="300">
                  <v-card-text>{{ video.id }}</v-card-text>
                  <v-fade-transition>
                    <v-overlay v-if="hover" absolute color="#036358">
                      <v-btn
                        color="orange darken-1"
                        class="text--black"
                        @click="seleccionar(video)"
                        >Elegir</v-btn
                      >
                    </v-overlay>
                  </v-fade-transition>
                </v-card>
              </template>
            </v-hover>
          </v-col>
        </v-row>
      </v-row>
    </v-card>
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
      videos: [],
    };
  },
  created() {
    this.obtenerVideos();
  },
  methods: {
    open() {
      this.dialog = true;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    obtenerVideos() {
      const respuesta = this.$store.dispatch("getVideos");
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
