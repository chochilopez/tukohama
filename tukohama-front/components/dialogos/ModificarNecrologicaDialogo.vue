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
              <span v-if="objeto.id == 0">Nueva necrologica</span>
              <span v-if="objeto.id > 0">Modificar necrologica</span>
            </v-toolbar-title>
          </v-toolbar>
          <v-card-text>
            <v-container>
              <v-row>
                <v-col cols="12">
                  <v-text-field
                    v-model="objeto.id"
                    dense
                    outlined
                    filled
                    counter="50"
                    clearable
                    class="pt-2"
                    label="Id"
                    disabled
                  />
                </v-col>
                <v-col cols="12">
                  <v-text-field
                    v-model="objeto.nombre"
                    dense
                    outlined
                    filled
                    counter="50"
                    clearable
                    class="pt-2"
                    label="Nombre"
                    :rules="[rules.required, rules.min, rules.maxString]"
                  />
                </v-col>
                <v-col cols="12">
                  <v-textarea
                    v-model="objeto.cuerpo"
                    dense
                    outlined
                    filled
                    counter="500"
                    clearable
                    class="pt-2"
                    height="200"
                    label="Cuerpo de la necrologica"
                    :rules="[rules.required, rules.min, rules.maxText]"
                  />
                </v-col>
                <v-col cols="12">
                  <v-select
                    v-model="religion.id"
                    dense
                    outlined
                    filled
                    item-text="nombre"
                    item-value="id"
                    :items="religionCombo"
                    label="Elegí el tipo de religion"
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
      religionCombo: [],
      religion: {
        id: 0,
        nombre: "",
        icono: "",
      },
      objeto: {
        id: 0,
        nombre: "",
        cuerpo: "",
      },
      rules: {
        required: (v) => !!v || "Debés completar el campo",
        min: (v) => v.length >= 3 || "Al menos 8 carácteres",
        maxString: (v) => v.length <= 100 || "Cómo máximo 100 carácteres",
        maxText: (v) => v.length <= 500 || "Cómo máximo 500 carácteres",
      },
    };
  },
  created() {
    this.religionCombo = this.$store.state.religion.religiones;
  },
  methods: {
    open(objeto) {
      this.dialog = true;
      this.objeto.id = objeto.id;
      this.objeto.nombre = objeto.nombre;
      this.objeto.cuerpo = objeto.cuerpo;
      this.religion = objeto.religion;
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    aceptar() {
      if (this.$refs.form.validate()) {
        this.objeto.religion = this.religion;
        this.resolve(this.objeto);
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
