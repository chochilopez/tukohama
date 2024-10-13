<template>
  <div class="mb-3">
    <h2>{{ categoria.nombre }}</h2>
    <v-system-bar height="5" :color="categoria.color"></v-system-bar>
    <vueper-slides
      :visible-slides="2"
      fixed-height="400px"
      slide-multiple
      :gap="2"
      :slide-ratio="1 / 1"
      :dragging-distance="200"
      :breakpoints="{
        800: { visibleSlides: 1, slideMultiple: 1 },
        1200: { visibleSlides: 2, slideMultiple: 2 },
      }"
    >
      <vueper-slide
        v-for="noticia in noticias"
        :key="noticia.id"
        :title="noticia.id.toString()"
      >
        <template #content>
          <CardCategoria :noticia="noticia" />
        </template>
      </vueper-slide>
    </vueper-slides>
  </div>
</template>

<script>
// import Helper from "../../services/helper";
import { VueperSlides, VueperSlide } from "vueperslides";
import CardCategoria from "./CardCategoria.vue";
import "vueperslides/dist/vueperslides.css";

export default {
  components: {
    CardCategoria,
    VueperSlides,
    VueperSlide,
  },
  props: {
    categoria: {
      type: Object,
      default: () => ({
        nombre: "",
        color: "",
      }),
    },
    noticias: {
      type: Array,
      required: true,
    },
  },
  data () {
    return {};
  },
};
</script>
