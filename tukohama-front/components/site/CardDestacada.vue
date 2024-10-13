<template>
  <a :href="'/noticia/' + noticia.id" class="text-decoration-none">
    <v-hover>
      <template #default="{ hover }">
        <v-card class="mx-auto ma-2" dark :width="ancho" :height="alto">
          <v-img
            :src="`${PUBLIC_PATH}media/${noticia.imagenPortada.path}`"
            :alt="noticia.imagenPortada.descripcion"
            :width="ancho"
            :height="alto"
            class="align-end"
          >
            <div class="top-left">
              <v-chip class="ml-3 mr-2 mt-3" label :color="noticia.categoria.color">
                <v-icon class="mr-2">{{ noticia.categoria.icono }}</v-icon>
                {{ noticia.categoria.nombre }}
              </v-chip>
            </div>
            <div class="top-right">
              <v-chip class="ml-3 mr-2 mt-3" label>
                {{ fecha }}
              </v-chip>
            </div>
            <p class="mb-5 ml-2 titulo">
              {{ noticia.titulo }}
            </p>
            <v-card-actions>
              <v-chip class="mb-5 ml-2" label>
                {{ noticia.autor.nombre }}
              </v-chip>
              <v-spacer />
            </v-card-actions>
          </v-img>
          <v-fade-transition>
            <v-overlay v-if="hover" absolute :color="noticia.categoria.color">
              <h1>Ver noticia</h1>
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
        id: null,
        categoria: null,
        imagenPortada: null,
        autor: null,
        meta: null,
        publicado: null,
        titulo: "",
        copete: "",
      }),
    },
    ancho: {
      type: Number,
      default: 500,
    },
    alto: {
      type: Number,
      default: 500,
    },
  },
  data () {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      fecha: null,
    };
  },
  mounted() {
    this.fecha = Helper.getFormatoFecha(this.noticia.publicado);
  },
};
</script>

<style scoped>
.titulo {
  text-shadow: 1px 1px 2px black, 0 0 1em black, 0 0 0.2em black;
  color: white;
  font: 1.5em Roboto;
}
.container {
  position: relative;
  text-align: center;
  color: white;
}

/* Bottom left text */
.bottom-left {
  position: absolute;
  bottom: 8px;
  left: 16px;
}

/* Top left text */
.top-left {
  position: absolute;
  top: 8px;
  left: 16px;
}

/* Top right text */
.top-right {
  position: absolute;
  top: 8px;
  right: 16px;
}

/* Bottom right text */
.bottom-right {
  position: absolute;
  bottom: 8px;
  right: 16px;
}

/* Centered text */
.centered {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>

