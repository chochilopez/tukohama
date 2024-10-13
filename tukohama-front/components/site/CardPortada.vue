<template>
  <v-card>
    <a :href="'/noticia/' + noticia.id" class="text-decoration-none">
      <div class="container mb-0 pb-0">
        <v-hover>
          <template #default="{ hover }">
            <v-card>
              <v-img
                :src="`${PUBLIC_PATH}media/${noticia.imagenPortada.path}`"
                :alt="noticia.titulo"
                height="300"
                class="white--text align-end"
                style="filter: brightness(50%)"
              >
              </v-img>
              <v-fade-transition>
                <v-overlay v-if="hover" absolute :color="noticia.categoria.color">
                  <h3>Ver noticia</h3>
                </v-overlay>
              </v-fade-transition>
            </v-card>
          </template>
        </v-hover>
        <div class="top-left">
          <v-chip class="ml-3 mr-2 mt-3" label :color="noticia.categoria.color">
            <v-icon class="mr-2">{{ noticia.categoria.icono }}</v-icon>
            {{ noticia.categoria.nombre }}
          </v-chip>
        </div>
        <div class="bottom-left text-left">
          <p class="titulo ml-3 mr-2 mt-3">{{ noticia.titulo }}</p>
          <v-chip class="ml-3 mr-2 mt-3" label>
            {{ fecha }}
          </v-chip>
        </div>
      </div>
      <v-card-text class="text--primary text--black">
        {{ noticia.copete }}
      </v-card-text>
    </a>
  </v-card>
</template>

<script>
import Helper from "~/services/helper";

export default {
  props: {
    noticia: {
      type: Object,
      default: () => ({
        id: 0,
        categoria: {
          color: "cyan",
          icono: "mdi-bullhorn",
          nombre: "",
        },
        imagenPortada: {
          path: "../../assets/vacio.png",
        },
        autor: {
          nombre: "",
        },
        meta: "",
        publicado: new Date(),
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
  font: 1.2em Roboto;
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
