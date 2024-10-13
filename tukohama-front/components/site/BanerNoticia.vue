<template>
  <a :href="'/noticia/' + noticia.id" class="text-decoration-none">
    <v-hover>
      <template #default="{ hover }">
        <v-card outlined>
          <v-row class="mt-3 mb-3">
            <v-col cols="12" md="2">
              <v-img
                :src="`${PUBLIC_PATH}media/${imagenPortada.path}`"
                :alt="noticia.imagenPortada.descripcion"
                class="align-end"
                max-height="200"
                max-width="200"
              />
            </v-col>
            <v-col cols="12" md="5">
              <v-row>
                <v-chip label :color="categoria.color" class="mb-2">
                  <v-icon class="mr-2">{{ categoria.icono }}</v-icon>
                  {{ categoria.nombre }}
                </v-chip>
              </v-row>
              <v-row>
                <p class="titulo ml-3 mr-2 mt-3">{{ noticia.titulo }}</p>
              </v-row>
              <v-row>
                <v-chip label class="mb-2">
                  {{ noticia.autor.nombre }}
                </v-chip>
              </v-row>
              <v-row>
                <v-chip label>
                  {{ fecha }}
                </v-chip>
              </v-row>
            </v-col>
            <v-col cols="12" md="5">
              <v-card-text>
                {{ noticia.copete }}
              </v-card-text>
            </v-col>
          </v-row>
          <v-fade-transition>
            <v-overlay v-if="hover" absolute :color="categoria.color">
              <h3>Ver noticia</h3>
            </v-overlay>
          </v-fade-transition>
        </v-card>
      </template>
    </v-hover>
  </a>
</template>

<script>
import Helper from "~/services/helper";

export default {
  props: {
    noticia: {
      type: Object,
      default: () => ({
        id: "",
        titulo: "",
        copete: "",
      }),
    },
    imagenPortada: {
      type: Object,
      default: () => ({
        path: "",
        descripcion: "",
      }),
    },
    categoria: {
      type: Object,
      default: () => ({
        nombre: "",
        icono: "",
        color: "",
      }),
    },
    autor: {
      type: Object,
      default: () => ({
        nombre: "",
      }),
    },
  },
  data () {
    return {
      noticias: [],
      PUBLIC_PATH: process.env.BASE_URL,
      fecha: null,
    };
  },
  created() {
    this.fecha = Helper.getFormatoFecha(this.noticia.publicado);
  },
};
</script>

<style scoped>
.titulo {
  color: black;
  font: 1.2em Roboto;
}
</style>
