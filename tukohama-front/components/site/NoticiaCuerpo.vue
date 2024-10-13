<template>
  <v-container>
    <v-divider />
    <v-row justify="space-between">
      <v-col cols="3">
        <v-chip class="mt-2 mb-2" label :color="categoria.color">
          <v-icon class="mr-2">{{ categoria.icono }}</v-icon>
          {{ categoria.nombre }}
        </v-chip>
      </v-col>
      <v-col cols="3" class="text-right" align-self="center">
        {{ noticia.publicado }}
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <p class="font-weight-bold text-h3">{{ noticia.titulo }}</p>
        <p class="font-weight-regular body-2">Por {{ autor.nombre }}</p>
        <p class="font-weight-regular">{{ noticia.copete }}</p>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-img
          :src="`${PUBLIC_PATH}media/${imagenPortada.path}`"
          :alt="noticia.titulo"
          contain
          max-height="500px"
        />
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <p v-html="noticia.cuerpo"></p>
      </v-col>
    </v-row>
    <v-row v-if="noticia.audios.length > 0">
      <v-col>
        <vueper-slides
          :visible-slides="1"
          fixed-height="200px"
          slide-multiple
          :gap="1"
          :slide-ratio="1 / 1"
          :dragging-distance="200"
          :breakpoints="{
            800: { visibleSlides: 1, slideMultiple: 1 },
            1200: { visibleSlides: 1, slideMultiple: 1 },
          }"
        >
          <vueper-slide v-for="audio in noticia.audios" :key="audio.id">
            <template #content>
              Audio
            </template>
          </vueper-slide>
        </vueper-slides>
      </v-col>
    </v-row>
    <v-row v-if="noticia.imagenes.length > 0">
      <v-col>
        <vueper-slides
          :visible-slides="2"
          fixed-height="500px"
          slide-multiple
          :gap="2"
          :slide-ratio="1 / 1"
          :dragging-distance="200"
          :breakpoints="{
            800: { visibleSlides: 1, slideMultiple: 1 },
            1200: { visibleSlides: 2, slideMultiple: 2 },
          }"
        >
          <vueper-slide v-for="imagen in noticia.imagenes" :key="imagen.id">
            <template #content>
              <v-img
                :src="`${PUBLIC_PATH}media/${imagen.path}`"
                :alt="noticia.titulo"
                contain
                max-height="500px"
              />
            </template>
          </vueper-slide>
        </vueper-slides>
      </v-col>
    </v-row>
    <v-row v-if="noticia.videos.length > 0">
      <v-col>
        <vueper-slides
          :visible-slides="1"
          fixed-height="500px"
          slide-multiple
          :gap="1"
          :slide-ratio="1 / 1"
          :dragging-distance="200"
          :breakpoints="{
            800: { visibleSlides: 1, slideMultiple: 1 },
            1200: { visibleSlides: 1, slideMultiple: 1 },
          }"
        >
          <vueper-slide v-for="video in noticia.videos" :key="video.id">
            <template #content>
              <vue-core-video-player
                :src="`${PUBLIC_PATH}media/${video.path}`"
                resolution="360p"
                muted
                loop
              ></vue-core-video-player>
            </template>
          </vueper-slide>
        </vueper-slides>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <h4 class="mt-5">Compartí esta noticia:</h4>
        <v-row class="text-center mt-3">
          <v-col cols="6">
            <div
              class="fb-share-button"
              :data-href="`https://crespovale.com.ar/noticia/${noticia.id}`"
              data-layout="box_count"
              data-size="small"
            >
              <a
                target="_blank"
                :href="`https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fcrespovale.com.ar%2Fnoticia%2F${noticia.id}&amp;src=sdkpreparse`"
                class="fb-xfbml-parse-ignore"
                >Compartir
              </a>
            </div>
          </v-col>
          <v-col cols="6">
            <div
              class="fb-like"
              :data-href="`https://crespovale.com.ar/noticia/${noticia.id}`"
              data-width=""
              data-layout="box_count"
              data-action="like"
              data-size="small"
              data-share="false"
            ></div>
          </v-col>
        </v-row>
      </v-col>
    </v-row>
    <v-row>
      <v-col v-if="noticia.comentarios">
        <h2 class="mt-5">Comentarios:</h2>
        <v-system-bar height="5" color="blue darken-3"></v-system-bar>
        <div
          class="fb-comments"
          :data-href="`crespovale.com.ar/noticia/${noticia.id}`"
          data-width="100%"
          data-numposts="10"
        />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { VueperSlides, VueperSlide } from "vueperslides";
import "vueperslides/dist/vueperslides.css";

export default {
  components: {
    VueperSlides,
    VueperSlide,
  },
  props: {
    categoria: {
      type: Object,
      default: () => ({
        nombre: "General",
        icono: "mdi-bullhorn",
        color: "cyan",
      }),
    },
    imagenPortada: {
      type: Object,
      default: () => ({
        nombre: "Redaccion",
      }),
    },
    autor: {
      type: Object,
      default: () => ({
        path: "",
        nombreArchivo: "",
      }),
    },
    noticia: {
      type: Object,
      default: () => ({
        id: 0,
        titulo: "",
        copete: "",
        meta: "",
        cuerpo: "",
        comentarios: false,
        publicado: "",
        audios: [],
        imagenes: [],
        videos: [],
      }),
    },
  },
  data () {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      fecha: null,
    };
  },
};
</script>
