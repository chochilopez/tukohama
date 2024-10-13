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
            Sin resultados para la categoria: {{ $route.params.categoria }}
          </h3>
          <h3 v-if="noticias.length > 0" class="mb-4">
            {{ $route.params.categoria }} <br>
            Se encontraron {{ noticias.length }} noticias
          </h3>
          <paginate name="noticiasPaginate" :list="noticias" :per="10">
            <div v-for="noticia in paginated('noticiasPaginate')" :key="noticia.id">
              <BanerNoticia
                :noticia="noticia"
                :imagenPortada="noticia.imagenPortada"
                :categoria="noticia.categoria"
                :autor="noticia.autor"
              />
            </div>
          </paginate>
          <paginate-links
            for="noticiasPaginate"
            :show-step-links="true"
            :simple="{
              prev: 'Anterior',
              next: 'Siguiente',
            }"
          ></paginate-links>
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
        { name: "description", content: "Noticias por categoría, mostrando categoría: " +  this.$route.params.categoria},
        { name: "googlebot", content: "index, follow" },
        { property: "og:title", content: "Categoría " + this.$route.params.categoria },
        { property: "og:type", content: "website" },
        { property: "og:url", content: "https://www.crespovale.com.ar/categoria/" + this.$route.params.categoria },
        { property: "og:description", content: "Noticias por categoría, mostrando categoría: " +  this.$route.params.categoria},
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
      paginate: ["noticiasPaginate"],
      items: [
        { text: "Inicio", disabled: false, href: "/" },
        { text: "Categorias", disabled: true, href: "/" },
      ],
    };
  },
  created() {
    if (this.$store.state.auth.autenticado === false) this.$router.push("/");
    else this.getNoticiasPorCategoria();
    this.items.push({
      text: this.$route.params.categoria,
      disabled: true,
    });
  },
  methods: {
    async getNoticiasPorCategoria() {
      try {
        await this.$store
          .dispatch("getNoticiasPorCategoria", this.$route.params.categoria)
          .then((result) => {
            if (result.status === 200) {
              this.noticias = result.data;
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

<style scoped>
.paginate-links{
  width:100%;
  list-style: none;
  text-align: center;
}
.paginate-links li {
  display: inline;
  background-color:#E43A48;
  color:white;
  padding:0.5rem;
  margin-left:0.3rem;
  margin-right: 0.3rem;
  cursor:pointer;
  border-radius: 3px;
}
.paginate-result{
  width: 100%;
  text-align:center;
  margin-bottom: 1rem;
}
</style>
