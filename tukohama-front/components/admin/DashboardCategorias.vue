<template>
  <div>
    <v-card outlined class="mb-5">
      <v-card-title>Categorias de noticias en portada</v-card-title>
      <v-card-text>
        Seleccioná hasta 4 categorias de noticias visibles en la portada.
      </v-card-text>
      <v-row class="pa-5">
        <v-col cols="6" md="3">
          <v-select
            v-model="categoria1"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-1-box"
            item-text="nombre"
            item-value="id"
            :items="comboCategorias"
            label="Seleccioná Categoría de Noticias"
          />
        </v-col>
        <v-col cols="6" md="3">
          <v-select
            v-model="categoria2"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-1-box"
            item-text="nombre"
            item-value="id"
            :items="comboCategorias"
            label="Seleccioná Categoría de Noticias"
          />
        </v-col>
        <v-col cols="6" md="3">
          <v-select
            v-model="categoria3"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-1-box"
            item-text="nombre"
            item-value="id"
            :items="comboCategorias"
            label="Seleccioná Categoría de Noticias"
          />
        </v-col>
        <v-col cols="6" md="3">
          <v-select
            v-model="categoria4"
            dense
            outlined
            filled
            prepend-icon="mdi-numeric-1-box"
            item-text="nombre"
            item-value="id"
            :items="comboCategorias"
            label="Seleccioná Categoría de Noticias"
          />
        </v-col>
      </v-row>
    </v-card>
    <v-row>
      <v-col align-self="center" align="center">
        <v-btn color="error" class="ma-2 white--text" @click="cancelarLayout()">
          Cancelar
          <v-icon right dark> mdi-cancel </v-icon>
        </v-btn>
      </v-col>
      <v-col align-self="center" align="center">
        <v-btn
          color="warning"
          class="ma-2 white--text"
          @click="guardarLayout()"
        >
          Guardar
          <v-icon right dark> mdi-content-save </v-icon>
        </v-btn>
      </v-col>
    </v-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      comboCategorias: [],
    };
  },
  computed: {
    categoria1: {
      get() {
        if (this.$store.state.layout.layout.categoria1 != null)
          return this.$store.state.layout.layout.categoria1;
        else return null;
      },
      set(value) {
        this.$store.commit("setCategoria1", value);
      },
    },
    categoria2: {
      get() {
        if (this.$store.state.layout.layout.categoria2 != null)
          return this.$store.state.layout.layout.categoria2;
        else return null;
      },
      set(value) {
        this.$store.commit("setCategoria2", value);
      },
    },
    categoria3: {
      get() {
        if (this.$store.state.layout.layout.categoria3 != null)
          return this.$store.state.layout.layout.categoria3;
        else return null;
      },
      set(value) {
        this.$store.commit("setCategoria3", value);
      },
    },
    categoria4: {
      get() {
        if (this.$store.state.layout.layout.categoria4 != null)
          return this.$store.state.layout.layout.categoria4;
        else return null;
      },
      set(value) {
        this.$store.commit("setCategoria4", value);
      },
    },
  },
  created() {
    this.obtenerCategorias();
  },
  methods: {
    guardarLayout() {
      const objeto = {
        cantidadUltimas: this.$store.state.layout.layout.cantidadUltimas,
        noticiasPortada: this.$store.state.layout.layout.noticiasPortada,
        // destacada1: this.$store.state.layout.layout.destacada1,
        // destacada2: this.$store.state.layout.layout.destacada2,
        // destacada3: this.$store.state.layout.layout.destacada3,
        // destacada4: this.$store.state.layout.layout.destacada4,
        categoria1: this.$store.state.layout.layout.categoria1,
        categoria2: this.$store.state.layout.layout.categoria2,
        categoria3: this.$store.state.layout.layout.categoria3,
        categoria4: this.$store.state.layout.layout.categoria4,
      };
      const respuesta = this.$store.dispatch("guardarLayout", objeto);
      respuesta
        .then((result) => {
          // eslint-disable-next-line no-console
          console.log("dash-lines -> layout -> saved");
          if (result.status === 201) {
            this.$toast.open({
              message: "Layout guardado correctamente",
              type: "success",
              position: "top-right",
              duration: 5000,
            });
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("dash-lines -> layout -> save error -> " + error);
          this.$toast.open({
            message: "Error al guardar el layout",
            type: "error",
            position: "top-right",
            duration: 5000,
          });
        });
    },
    async obtenerCategorias() {
      if (this.$store.state.categoria.categorias === false) {
        try {
          await this.$store.dispatch("getCategorias");
        // eslint-disable-next-line no-unused-expressions
        } catch {(error) => {
          // eslint-disable-next-line no-console
          console.log("dash-lines -> category -> fetch error " + error);
        }}
      } else 
      // eslint-disable-next-line no-console
      console.log("dash-lines -> category -> reloaded");
      for (const prop in this.$store.state.categoria.categorias)
        this.comboCategorias.push(this.$store.state.categoria.categorias[prop]);
    },
    cancelarLayout() {},
  },
};
</script>
