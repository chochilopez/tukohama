<template>
  <div>
    <AllUltimoMomento />
    <AllBrand />
    <AllNavbar />
    <v-container>
      <v-breadcrumbs large :items="items">
        <template v-slot:divider>
          <v-icon>mdi-bullhorn-outline</v-icon>
        </template>
      </v-breadcrumbs>
      <v-divider></v-divider>
      <v-row>
        <v-col cols="12" md="9" class="mt-4">
          <h3 v-if="noticias.length === 0">
            Sin resultados para la busqueda: {{ $route.params.busqueda }}
          </h3>
          <h3 v-if="noticias.length > 0" class="mb-4">
            Resultados de busqueda para: {{ $route.params.busqueda }} <br>
            Se encontraron {{ noticias.length }} resultados
          </h3>
          <div v-for="noticia in noticias" :key="noticia.id">
            <BanerNoticia
              :noticia="noticia"
              :imagenPortada="noticia.imagenPortada"
              :categoria="noticia.categoria"
              :autor="noticia.autor"
            />
          </div>
        </v-col>
        <v-col cols="12" md="3">
          <ClimaTarjeta />
          <HomeColumnaPublicidad />
        </v-col>
      </v-row>
    </v-container>
    <ClimaFooter />
    <AllFooter />
  </div>
</template>

<script>
import AllUltimoMomento from "../components/site/AllUltimoMomento";
import AllBrand from "../components/site/AllBrand";
import AllNavbar from "../components/site/AllNavbar";
import AllFooter from "../components/site/AllFooter";
import ClimaFooter from "../components/site/ClimaFooter";
import ClimaTarjeta from "../components/site/ClimaTarjeta";
import BanerNoticia from "../components/site/BanerNoticia";
import HomeColumnaPublicidad from "../components/site/HomeColumnaPublicidad.vue";

export default {
  metaInfo() {
    return {
      title: "CrespoVale - Buscá tu noticia",
      meta: [
        {
          name: "description",
          content: "Buscá las noticias más importantes, en Crespo Vale",
        },
        {
          property: "og:title",
          content: "CrespoVale - Buscá tu noticia",
        },
        { name: "googlebot", content: "index, follow" },
        {
          property: "og:url",
          content:
            "https://www.crespovale.com.ar/busqueda/" +
            this.$route.params.busqueda,
        },
        {
          property: "og:description",
          content: "Busca todas las noticias de Crespo y zona",
        },
        { property: "og:type", content: "website" },
        { property: "og:image", content: "logo.png" },
        { property: "fb:app_id", content: "2834490246658663" },
      ],
    };
  },
  components: {
    AllUltimoMomento,
    AllBrand,
    AllNavbar,
    ClimaTarjeta,
    ClimaFooter,
    AllFooter,
    BanerNoticia,
    HomeColumnaPublicidad,
  },
  data() {
    return {
      noticias: [],
      items: [
        { text: "Inicio", disabled: false, href: "/" },
        { text: "Busqueda", disabled: true },
      ],
    };
  },
  created() {
    if (this.$store.state.auth.autenticado === false) this.$router.push("/");
    else this.getNoticiasPorTitulo();
  },
  methods: {
    async getNoticiasPorTitulo() {
      try {
        await this.$store
          .dispatch("getNoticiasPorTitulo", this.$route.params.busqueda)
          .then((result) => {
            if (result.status === 200) {
              this.noticias = result.data;
              this.$toast.open({
                message:
                  "Busqueda exitosa: " + this.noticias.length + " resultados",
                type: "success",
                position: "top",
                duration: 5000,
              });
            } else if (result.status === 204) {
              this.$toast.open({
                message: "Su busqueda no arrojó resultados",
                type: "warning",
                position: "top",
                duration: 5000,
              });
            }
          });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log(error)
        this.$toast.open({
          message: "Hubo un error al intentar ingresar su busqueda",
          type: "error",
          position: "top",
          duration: 5000,
        });
      }
    },
  },
};
</script>
