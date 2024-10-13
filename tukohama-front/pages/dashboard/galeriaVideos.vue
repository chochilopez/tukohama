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
        <GaleriaVideos />
      </v-col>
    </v-row>
  </div>
</template>

<script>
import Navbar from "../../components/admin/Navbar";
import NavigationDrawer from "../../components/admin/NavigationDrawer";
import GaleriaVideos from "../../components/admin/GaleriaVideos";

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
    GaleriaVideos,
  },
};
</script>
