<template>
  <div>
    <h2 class="mt-5">Publicite aquí</h2>
    <v-system-bar height="5" color="purple darken-3"></v-system-bar>
    <div v-for="publicidad in $store.state.publicidad.arrayCards" :key="publicidad.id" class="mt-5">
      <CardPublicidad :publicidad="publicidad" />
    </div>
  </div>
</template>

<script>
import CardPublicidad from "./CardPublicidad";

export default {
  components: {
    CardPublicidad,
  },
  data() {
    return {
      publicidades: [],
    };
  },
};
</script>
