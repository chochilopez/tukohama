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
        <v-row>
          <v-col>
            <DashboardContadores />
          </v-col>
        </v-row>
        <v-row>
          <v-col>
            <DashboardOpciones />
          </v-col>
        </v-row>
        <!--v-row>
          <v-col>
            <DashboardDestacadas />
          </v-col>
        </!--v-row-->
        <v-row>
          <v-col>
            <DashboardCategorias />
          </v-col>
        </v-row>
      </v-col>
    </v-row>
  </div>
</template>

//BUG arreglas las vitas de las noticias en busqueda
//BUG areglas las noticias, baners portada y necorlogicas

<script>
import Navbar from "../../components/admin/Navbar";
import NavigationDrawer from "../../components/admin/NavigationDrawer";
import DashboardContadores from "../../components/admin/DashboardContadores";
import DashboardOpciones from "../../components/admin/DashboardOpciones";
// import DashboardDestacadas from "../../components/admin/DashboardDestacadas";
import DashboardCategorias from "../../components/admin/DashboardCategorias";

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
    DashboardContadores,
    DashboardOpciones,
    //DashboardDestacadas,
    DashboardCategorias,
  },
  methods: {
    async obtenerLayout() {
      try {
        Promise.all([await this.$store.dispatch("getDestacablesPortada"), await this.$store.dispatch("getLayout")])
      } catch (error) {
        console.log("dash -> layout/feat -> fetch error -> " + error);
      }
    },
  },
  created() {
    this.obtenerLayout();
  },
};
</script>
