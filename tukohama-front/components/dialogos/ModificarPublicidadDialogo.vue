<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-form ref="form" v-model="valid" lazy-validation>
          <v-toolbar dark color="blue darken-2">
            <v-toolbar-title
              class="text-h5 font-weight-bold white--text"
              color="blue darken-1"
            >
              {{ titulo }} -> {{ objeto.id }}-{{ objeto.firma }}
            </v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-card-text>
              <h4>{{ mensaje }}</h4>
              <h4>Id: {{ objeto.id }}</h4>
            </v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="objeto.firma"
                    dense
                    outlined
                    filled
                    counter="50"
                    clearable
                    class="pt-2"
                    label="Nombre Firma/Empresa"
                    prepend-icon="mdi-shopping"
                    :rules="[rules.required, rules.min, rules.maxString]"
                  />
                </v-col>
                <v-col cols="12">
                  <v-select
                    v-model="objeto.tipo"
                    dense
                    outlined
                    filled
                    prepend-icon="mdi-arrow-decision"
                    item-text="nombre"
                    item-value="id"
                    :items="tipoCombo"
                    label="Elegí el tipo de publicidad"
                    class="pt-2"
                    :rules="[rules.required]"
                  />
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    v-model="objeto.link"
                    dense
                    outlined
                    filled
                    counter="50"
                    clearable
                    class="pt-2"
                    label="Link al sitio/face de le Empresa"
                    prepend-icon="mdi-link"
                    :rules="[rules.maxString]"
                  />
                </v-col>
                <v-col cols="12">
                  <v-select
                    v-model="objeto.importancia"
                    dense
                    outlined
                    filled
                    prepend-icon="mdi-arrow-up-down-bold"
                    item-text="valor"
                    item-value="id"
                    :items="importanciaCombo"
                    label="Elegí la importancia"
                    class="pt-2"
                    :rules="[rules.required]"
                  />
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions class="pt-3">
            <v-btn
              color="error"
              class="ma-2 white--text"
              @click.native="cancelar()"
            >
              Cancelar
              <v-icon right dark> mdi-cancel </v-icon>
            </v-btn>
            <v-spacer />
            <v-btn
              color="warning"
              class="ma-2 white--text"
              @click.native="aceptar()"
            >
              Aceptar
              <v-icon right dark> mdi-content-save </v-icon>
            </v-btn>
          </v-card-actions>
        </v-form>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data() {
    return {
      valid: true,
      dialog: false,
      resolve: null,
      reject: null,
      mensaje: null,
      titulo: null,
      nombre: null,
      descripcion: null,
      objeto: {
        id: null,
        firma: null,
        tipo: null,
        link: null,
        importancia: null,
      },
      firma: "",
      link: "",
      nombreArchivo: null,
      importancia: null,
      tipo: null,
      importanciaCombo: [
        { id: 1, valor: "Muy alta" },
        { id: 2, valor: "Alta" },
        { id: 3, valor: "Media" },
        { id: 4, valor: "Baja" },
        { id: 5, valor: "Muy baja" },
      ],
      tipoCombo: [
        { id: 1, nombre: "Tarjeta" },
        { id: 2, nombre: "Baner" },
      ],
      rules: {
        required: (v) => !!v || "Debés completar el campo",
        min: (v) => v.length >= 3 || "Al menos 8 carácteres",
        maxString: (v) => v.length <= 100 || "Cómo máximo 100 carácteres",
        maxText: (v) => v.length <= 500 || "Cómo máximo 500 carácteres",
      },
    };
  },
  methods: {
    open(titulo, mensaje, objeto) {
      this.dialog = true;
      this.titulo = titulo;
      this.mensaje = mensaje;
      this.objeto.id = objeto.id;
      this.objeto.firma = objeto.firma;
      this.objeto.link = objeto.link;
      this.objeto.tipo = objeto.tipo;
      this.objeto.importancia = objeto.importancia;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    aceptar() {
      this.resolve(this.objeto);
      this.dialog = false;
    },
    cancelar() {
      this.resolve(false);
      this.dialog = false;
    },
  },
};
</script>
