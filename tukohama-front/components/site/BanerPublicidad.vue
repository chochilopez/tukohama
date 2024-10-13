<template>
  <v-container>
    <v-img
      height="80%"
      :src="`${PUBLIC_PATH}publicidades/${publicidad.path}`"
      :alt="publicidad.firma"
    />
  </v-container>
</template>

<script>
export default {
  props: {
    publicidad: {
      type: Object,
      default: () => ({
        link: "",
        path: "",
        firma: "",
      }),
    },
  },
  data () {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
    };
  },
};
</script>
