<template>
  <div>
    <v-row class="pt-3">
      <v-col cols="12" sm="3" class="ultimoM-fecha" align-self="center" align="center">
        <a href="/dashboard" v-if=" this.$store.state.auth.autoridad == 2 || this.$store.state.auth.autoridad == 3" class="text-decoration-none">
          <v-icon class="mr-2 white--text">mdi-account</v-icon>
        </a>
        <a href="/login" v-if="this.$store.state.auth.autoridad == 1" class="text-decoration-none">
          <v-icon class="mr-2 white--text">mdi-calendar-today</v-icon>
        </a>
        <span class="white--text">{{ hoy }}</span>
      </v-col>
      <v-col cols="12" sm="2" class="ultimoM-ultimo" align="center" align-self="center">
        <span>ULTIMAS NOTICIAS</span>
      </v-col>
      <v-col cols="12" sm="7" class="d-flex align-items-center ultimoM-noticia">
        <v-icon class="mr-2">mdi-bullhorn</v-icon>
        <v-carousel cycle continuous hide-delimiter-background hide-delimiters interval="6000" touchless :show-arrows="false" height="20">
          <v-carousel-item v-for="noticia in $store.state.noticia.noticiasUltimoMomento" :key="noticia.id">
            <a :href="'/noticia/' + noticia.id" class="ultimoM-noticia-texto">{{ noticia.titulo }}</a>
          </v-carousel-item>
        </v-carousel>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data () {
    return {
      hoy: new Date().toLocaleDateString("es-ES", {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
      }),
    };
  },
  created() {},
};
</script>

<style scoped>
html {
  word-break: break-all;
}
a,
a:hover {
  color: #b6d6d6;
}
.sin-fondo {
  border: 0px;
  background-color: #34495e;
}
.ultimoM-fecha {
  background-color: #424b54;
}
.ultimoM-ultimo {
  background-color: #e1ce7a;
  color: #424b54;
}
.ultimoM-ultimo-icono {
  color: #424b54;
}
.ultimoM-noticia {
  background-color: #e5e0dc;
}
.ultimoM-noticia-texto {
  color: #424b54 !important;
  text-decoration: none;
  font-weight: bolder;
}
.marca-social {
  margin-right: 5px;
  width: 30px;
  color: #34495e;
}
.marca-social :hover {
  background-color: #34495e;
  color: #b6d6d6;
}
.navbar-principal i {
  border-radius: 25px;
  color: #34495e;
  background: white;
  padding: 10px;
}
.primario {
  color: #34495e;
}
.secundario {
  color: #b6d6d6;
}
.boton-logo {
  background-color: white;
  border: 0px;
}
.navbar-site {
  font-family: Roboto;
  color: #000 !important;
}
</style>
