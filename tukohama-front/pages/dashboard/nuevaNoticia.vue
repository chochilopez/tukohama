<template>
  <div>
    <v-row class="pt-2">
      <Navbar />
    </v-row>
    <v-row>
      <v-col cols="2" md="2">
        <NavigationDrawer />
      </v-col>
      <v-col cols="10" md="10">
        <NuevaNoticia />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Navbar from "../../components/admin/Navbar";
import NavigationDrawer from "../../components/admin/NavigationDrawer";
import NuevaNoticia from "../../components/admin/NuevaNoticia";

export default {
  data() {
    return {};
  },
  metaInfo() {
    return {
      title: "CrespoVale - ",
      meta: [{ name: "googlebot", content: "nofollow" }],
    };
  },
  components: {
    Navbar,
    NavigationDrawer,
    NuevaNoticia,
  },
};
</script>
