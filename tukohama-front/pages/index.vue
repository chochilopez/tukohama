<template>  
  <v-app>
    <v-main>
      <div>
        <!--Header-->
        <siteAllUltimoMomento />
        <siteAllBrand />
        <siteClimaHeader />
        <siteAllNavbar />
        <!--Cuerpo-->
        <siteHomeDestacadas />
        <siteBanerPublicidad v-if="baners.length >= 1" :publicidad="baners[0]" />
        <v-container>
          <v-row>
            <v-col cols="12" md="9">
              <siteHomePortada />
              <siteBanerPublicidad v-if="baners.length >= 2" :publicidad="baners[1]" />
              <siteHomeCategoria v-if="categoria1 != null" :categoria="categoria1" :noticias="noticias1" />
              <siteBanerPublicidad v-if="baners.length >= 3" :publicidad="baners[2]" />
              <siteHomeCategoria v-if="categoria2 != null" :categoria="categoria2" :noticias="noticias2" />
              <siteBanerPublicidad v-if="baners.length >= 4" :publicidad="baners[3]" />
              <siteHomeCategoria v-if="categoria3 != null" :categoria="categoria3" :noticias="noticias3" />
              <siteBanerPublicidad v-if="baners.length >= 5" :publicidad="baners[4]" />
              <siteHomeCategoria v-if="categoria4 != null" :categoria="categoria4" :noticias="noticias4" />
              <siteBanerPublicidad v-if="baners.length >= 6" :publicidad="baners[5]" />
            </v-col>
            <v-col cols="12" md="3">
              <siteCardFacebook />
              <siteHomeColumnaPublicidad />
              <siteHomeNecrologicas />
            </v-col>
          </v-row>
        </v-container>
        <!--Footer-->
        <siteClimaFooter />
        <siteAllFooter />
      </div>
    </v-main>
  </v-app>
</template>

<script>
// TODO revisar datos ingresados en path como valor
import Helper from "~/services/helper";

export default {
  // metaInfo() {
  //   return {
  //     title: "CrespoVale - Portal de noticias",
  //     meta: [
  //       { name: "description", content:"Enterate de las ultimas noticias de Crespo y zona. Todas las noticias en una sola página - Crespo - Entre Rios" },
  //       { name: "googlebot", content: "index, follow" },
  //       { property: "og:title", content: "CrespoVale - Portal de Noticias" },
  //       { property: "og:type", content: "website" },
  //       { property: "og:url", content: "https://www.crespovale.com.ar/" },
  //       { property: "og:description", content: "Portal de noticias para Crespo y zona." },
  //       { property: "og:image", content: "../../assets/logo.png" },
  //       { property: "fb:app_id", content: "2834490246658663" },
  //     ],
  //   };
  // },
  data() {
    return {
      destacadas: [],
      noticiasPortada: [],
      categoria1: {},
      categoria2: {},
      categoria3: {},
      categoria4: {},
      noticias1: [],
      noticias2: [],
      noticias3: [],
      noticias4: [],
      baners: [],
    };
  },
  created() {
    this.getIp();
    this.fulfillPromises();
  },
  methods: {
    async fulfillPromises() {
      try {
        await this.doLogin();
        await this.doLayout();
        await this.doCategorias();
        Promise.all([
          // this.doWidgets(),
          this.doPublicadasPortada(),
          // this.doNecrologicas(),
          // this.doPublicidades(),
          // this.doCategoriasPortada(),
        ]);
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> login -> error -> " + error);
      }
    },
    async getIp() {
      if (this.$store.state.visita.ip === false) {
        try {
          await this.$store.dispatch("visita/getIp");
        } catch (error) {
          // eslint-disable-next-line no-console
          console.log("home -> address -> fetch error -> " + error);
        }
      // eslint-disable-next-line no-console
      } else console.log("home -> address -> reloaded");
    },
    async doLogin() {
      if (this.$store.state.auth.autenticado === false) {
        try {
          const usuario = {
            username: "invitado@invitado",
            password: "invitado",
          };
          await this.$store.dispatch("auth/login", usuario).then((result) => {
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
    async doLayout() {
      try {
        await this.$store.dispatch("layout/getLayout").then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("home -> layout -> logged");
          }
        });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> layout -> error -> " + error);
      }
    },
    async doCategorias() {
      try {
        await this.$store.dispatch("categoria/getCategorias").then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("home -> category -> fetched");
          }
        });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> category -> error -> " + error);
      }
    },
    async doWidgets() {
      try {
        await this.$store.dispatch("clima/getWidgets").then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("home -> widgets -> fetched");
          }
        });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> widgets -> error -> " + error);
      }
    },
    async doPublicadasPortada() {
      try {
        await this.$store.dispatch("noticia/getPublicadasPortada").then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("home -> featured -> fetched");
            if (
              this.$store.state.noticia.noticiasPublicadasPortada.length >=
              this.$store.state.layout.layout.cantidadUltimas
            ) {
              const noticias = [];
              for (
                let i = 1;
                i <= this.$store.state.layout.layout.cantidadUltimas;
                i++
              ) {
                noticias.push(this.$store.state.noticia.noticiasPublicadasPortada[i]);
              }
              this.$store.commit("noticia/setNoticiasUltimoMomento", noticias);
            }
            this.$store.commit(
              "noticia/setNoticiasPublicadasPortada",
              this.noticiasPortada = this.$store.state.noticia.noticiasPublicadasPortada);
            this.obtenerDestacadasPortada();
          }
        });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> featured -> error -> " + error);
      }
    },
    async doNecrologicas() {
      try {
        await this.$store.dispatch("necrologica/getNecrologicas").then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("home -> nec -> fetched");
          }
        });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> nec -> error -> " + error);
      }
    },
    async doPublicidades() {
      try {
        await this.$store.dispatch("publicidad/getPublicidades").then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("home -> ads -> fetched");
            const arreglo = Helper.filtrarPublicidades(this.$store.state.publicidad.publicidades)
            this.$store.commit("publicidad/setArrayCards", arreglo[0]);
            this.$store.commit("publicidad/setArrayBaners", arreglo[1]);
          }
        });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> ads -> error -> " + error);
      }
      this.baners = this.$store.state.publicidad.arrayBaners;
    },
    async doCategoriasPortada() {
      try {
        if (this.$store.state.layout.layout.categoria1 != null) {
          this.categoria1 = Helper.buscarPorId(
            this.$store.state.categoria.categorias,
            this.$store.state.layout.layout.categoria1
          );
          await this.$store
            .dispatch("noticia/getCategoriasPortada", this.categoria1.nombre)
            .then((result) => {
              this.noticias1 = result.data;
              // eslint-disable-next-line no-console
              console.log("home -> front-category1 -> fetched");
            });
        }
        if (this.$store.state.layout.layout.categoria2 != null) {
          this.categoria2 = Helper.buscarPorId(
            this.$store.state.categoria.categorias,
            this.$store.state.layout.layout.categoria2
          );
          await this.$store
            .dispatch("noticia/getCategoriasPortada", this.categoria2.nombre)
            .then((result) => {
              this.noticias2 = result.data;
              // eslint-disable-next-line no-console
              console.log("home -> front-category2 -> fetched");
            });
        }
        if (this.$store.state.layout.layout.categoria3 != null) {
          this.categoria3 = Helper.buscarPorId(
            this.$store.state.categoria.categorias,
            this.$store.state.layout.layout.categoria3
          );
          await this.$store
            .dispatch("noticia/getCategoriasPortada", this.categoria3.nombre)
            .then((result) => {
              this.noticias3 = result.data;
              // eslint-disable-next-line no-console
              console.log("home -> front-category3 -> fetched");
            });
        }
        if (this.$store.state.layout.layout.categoria4 != null) {
          this.categoria4 = Helper.buscarPorId(
            this.$store.state.categoria.categorias,
            this.$store.state.layout.layout.categoria4
          );
          await this.$store
            .dispatch("noticia/getCategoriasPortada", this.categoria4.nombre)
            .then((result) => {
              this.noticias4 = result.data;
              // eslint-disable-next-line no-console
              console.log("home -> front-category4 -> fetched");
            });
        }
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log("home -> category -> fetch error -> " + error);
      }
    },
    obtenerDestacadasPortada() {
      this.noticiasPortada = this.$store.state.noticia.noticiasPublicadasPortada;
      for (let a = 0; a < this.$store.state.layout.layout.cantidadUltimas; a++){
        this.destacadas.push(this.noticiasPortada[a]);
        this.noticiasPortada = Helper.eliminarPorId(
          this.noticiasPortada,
          this.noticiasPortada[a].id
        );
      }
      this.$store.commit("noticia/setArrayDestacadas", this.destacadas);
      if (this.noticiasPortada.length>this.$store.state.layout.layout.noticiasPortada){
        this.noticiasPortada.length=this.noticiasPortada.length-(this.noticiasPortada.length-this.$store.state.layout.layout.noticiasPortada)
      }
      this.$store.commit("noticia/setArrayPortada", this.noticiasPortada);
    },
  },
};
</script>
