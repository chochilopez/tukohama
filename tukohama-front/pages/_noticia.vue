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
      <v-row>
        <v-col cols="12" md="9">
          <NoticiaCuerpo
            :noticia="noticia"
            :imagenPortada="noticia.imagenPortada"
            :autor="noticia.autor"
            :categoria="noticia.categoria"
          />
        </v-col>
        <v-col cols="12" md="3">
          <ClimaTarjeta />
          <HomeColumnaPublicidad />
        </v-col>
      </v-row>
    </v-container>
    <AllFooter />
  </div>
</template>

  //TODO agrandar imagen con lupa

<script>
import AllUltimoMomento from "../components/site/AllUltimoMomento";
import AllBrand from "../components/site/AllBrand";
import NoticiaCuerpo from "../components/site/NoticiaCuerpo";
import AllNavbar from "../components/site/AllNavbar";
import AllFooter from "../components/site/AllFooter";
import ClimaTarjeta from "../components/site/ClimaTarjeta.vue";
import HomeColumnaPublicidad from "../components/site/HomeColumnaPublicidad.vue";
import Helper from "~/services/helper";

export default {
  metaInfo() {
    return {
      title: "CrespoVale - " + this.noticia.titulo,
      meta: [
        { name: "description", content: this.noticia.meta },
        { name: "googlebot", content: "index, follow" },
        { property: "og:title", content: this.noticia.titulo },
        { property: "og:type", content: "article" },
        { property: "og:url", content: "https://www.crespovale.com.ar/noticia/" + this.noticia.id },
        { property: "og:description", content: this.noticia.meta },
        { property: "og:image", content: "https://www.crespovale.com.ar/media/" + this.noticia.imagenPortada.path },
        { property: "fb:app_id", content: "2834490246658663" },
      ],
    };
  },
  components: {
    AllUltimoMomento,
    AllBrand,
    AllNavbar,
    AllFooter,
    NoticiaCuerpo,
    HomeColumnaPublicidad,
    ClimaTarjeta,
  },
  data() {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      noticia: {
        id: 0,
        titulo: "",
        meta: "",
        imagenPortada: {
          path: "",
        },
      },
      items: [{ text: "Inicio", disabled: false, href: "/" }],
    };
  },
  created() {
    this.fulfillPromises();
  },
  methods: {
    async fulfillPromises() {
      try {
        await this.doLogin();
        await this.doNoticia();
        await this.doPublicidades();
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> login -> error -> " + error);
      }
    },
    async doLogin() {
      if (this.$store.state.auth.autenticado === false) {
        try {
          const usuario = {
            username: "invitado@invitado",
            password: "invitado",
          };
          await this.$store.dispatch("login", usuario).then((result) => {
            if (result.status === 200) {
              // eslint-disable-next-line no-console
              console.log("home -> login -> logged");
            }
          });
        } catch (error) {
          // eslint-disable-next-line no-console
          console.log("home -> login -> error -> " + error);
        }
      } else {
        // eslint-disable-next-line no-console
        console.log("home -> login -> reloaded");
      }
    },
    async doPublicidades() {
      try {
        await this.$store.dispatch("getPublicidades").then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("home -> ads -> fetched");
            const arreglo = Helper.filtrarPublicidades(this.$store.state.publicidad.publicidades)
            this.$store.commit("setArrayCards", arreglo[0]);
            this.$store.commit("setArrayBaners", arreglo[1]);
          }
        });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> ads -> error -> " + error);
      }
      this.baners = this.$store.state.publicidad.arrayBaners;
    },
    doNoticia() {
        this.$store
          .dispatch("getNoticiaPublicadaPorId", this.$route.params.id)
          .then((result) => {
            if (result.status === 204) {
              this.$toast.open({
                message: "No existe la noticia a la que intentas acceder",
                type: "warning",
                position: "top",
                duration: 5000,
              });
            } else {
              this.noticia = result.data;
              this.noticia.publicado = Helper.getFormatoFecha(this.noticia.publicado);
              this.items.push({
                text: this.noticia.categoria.nombre,
                disabled: false,
                href: "/categoria/" + this.noticia.categoria.nombre,
              });
              this.items.push({ text: this.noticia.titulo, disabled: true });
            }
        });
    },
  },
};
</script>
