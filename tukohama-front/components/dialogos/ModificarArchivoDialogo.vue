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
              {{ titulo }} -> {{ archivo.id }}-{{ archivo.firma }}
            </v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-card-text>
              <h4>{{ mensaje }}</h4>
              <h4>Id: {{ archivo.id }}</h4>
            </v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12" align="center" align-content="center">
                  <v-text-field
                    v-model="archivo.nombreArchivo"
                    dense
                    outlined
                    filled
                    counter="50"
                    clearable
                    class="pt-2"
                    disabled
                    label="Nombre del archivo"
                    prepend-icon="mdi-file-music"
                  />
                </v-col>
                <v-col cols="12" align="center" align-content="center">
                  <v-text-field
                    v-model="archivo.descripcion"
                    dense
                    outlined
                    filled
                    counter="50"
                    clearable
                    class="pt-2"
                    label="Descripcion del archivo"
                    prepend-icon="mdi-comment-text"
                    :rules="[rules.required, rules.min, rules.maxString]"
                  />
                </v-col>
                <v-col cols="12" align="center" align-content="center">
                  <v-text-field
                    v-model="archivo.tamanio"
                    dense
                    outlined
                    filled
                    counter="50"
                    clearable
                    disabled
                    class="pt-2"
                    label="Tamaño del archivo"
                    prepend-icon="mdi-comment-text"
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
      descripcion: null,
      archivo: {
        id: 0,
        nombreArchivo: "",
        descripcion: "",
        tamanio: "",
      },
      rules: {
        required: (v) => !!v || "Debés completar el campo",
        min: (v) => v.length >= 3 || "Al menos 8 carácteres",
        maxString: (v) => v.length <= 100 || "Cómo máximo 100 carácteres",
        maxText: (v) => v.length <= 500 || "Cómo máximo 500 carácteres",
      },
    };
  },
  methods: {
    open(titulo, mensaje, archivo) {
      this.dialog = true;
      this.titulo = titulo;
      this.mensaje = mensaje;
      this.archivo.id = archivo.id;
      this.archivo.nombreArchivo = archivo.nombreArchivo;
      this.archivo.descripcion = archivo.descripcion;
      this.archivo.tamanio = archivo.tamanio;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    aceptar() {
      if (this.$refs.form.validate()) {
        this.resolve(this.archivo);
        this.dialog = false;
      }
    },
    cancelar() {
      this.resolve(false);
      this.dialog = false;
    },
  },
};
</script>
