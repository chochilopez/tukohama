<template>
  <div v-if="necrologicas" class="pt-5">
    <h2 class="mt-5">Necrológicas</h2>
    <v-system-bar height="5" color="blue-grey darken-4"></v-system-bar>
    <div v-for="necrologica in necrologicas" :key="necrologica.id" class="mt-5">
      <CardNecrologica :necrologica="necrologica" />
    </div>
  </div>
</template>

<script>
import CardNecrologica from "./CardNecrologica";
import Helper from "~/services/helper";

export default {
  components: {
    CardNecrologica,
  },
  data() {
    return {
      necrologicas: [],
    };
  },
  created() {
    this.necrologicas = this.$store.state.necrologica.necrologicas;
    this.necrologicas = Helper.ordenarArrayCreadoDesc(this.necrologicas);
    if (this.necrologicas.length > 2) this.necrologicas.length = 3;
  },
};
</script>
