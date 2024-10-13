<template>
  <div>
    <v-row>
      <v-col cols="12" md="4">
        <v-card outlined class="pa-3">
          <v-form
            ref="form"
            v-model="valido"
            lazy-validation
            @submit.prevent="guardarAutor"
          >
            <p v-if="!modificar" class="text-h5 text--primary">Nueva autor</p>
            <p v-if="modificar" class="text-h5 text--primary">
              Modificar autor
            </p>
            <v-text-field
              v-model="autor.nombre"
              label="Ingresá el nombre del autor"
              :rules="[rules.required, rules.min, rules.max]"
              outlined
              persistent-hint
              filled
              required
              counter="50"
            ></v-text-field>
            <div v-if="!modificar" class="d-flex d-inline">
              <v-spacer />
              <v-btn
                color="warning"
                class="ma-2 white--text"
                type="submit"
                :disabled="!valido || autor.nombre === ''"
              >
                Guardar
                <v-icon right dark> mdi-content-save </v-icon>
              </v-btn>
            </div>
            <div v-if="modificar" class="d-flex d-inline">
              <v-btn
                color="error"
                class="ma-2 white--text"
                @click="cancelarModificar()"
              >
                Cancelar
                <v-icon right dark> mdi-cancel </v-icon>
              </v-btn>
              <v-spacer />
              <v-btn
                color="warning"
                class="ma-2 white--text"
                type="submit"
                :disabled="!valido"
              >
                Guardar
                <v-icon right dark> mdi-content-save </v-icon>
              </v-btn>
            </div>
          </v-form>
        </v-card>
      </v-col>
      <v-col cols="12" md="8">
        <v-card outlined class="pa-3">
          <p class="text-h5 text--primary">Autores existentes</p>
          <v-simple-table>
            <template #default>
              <thead>
                <tr>
                  <th class="text-left">Nombre</th>
                  <th class="text-left">Accion</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="autorListado in $store.state.autor.autores"
                  :key="autorListado.id"
                >
                  <td>
                    {{ autorListado.nombre }}
                  </td>
                  <td>
                    <v-chip
                      class="ma-2"
                      color="primary"
                      label
                      @click="editarDialogo(autorListado)"
                    >
                      <v-icon left> mdi-home-edit </v-icon>
                      Editar
                    </v-chip>
                    <v-chip
                      class="ma-2"
                      color="error"
                      label
                      @click="
                        borrarDialogo(autorListado.id, autorListado.nombre)
                      "
                    >
                      <v-icon left> mdi-delete </v-icon>
                      Borrar
                    </v-chip>
                  </td>
                </tr>
              </tbody>
            </template>
          </v-simple-table>
        </v-card>
      </v-col>
    </v-row>
    <ConfirmarDialogo ref="confirmar" />
  </div>
</template>

<script>
import ConfirmarDialogo from "../dialogos/ConfirmarDialogo.vue";

export default {
  components: {
    ConfirmarDialogo,
  },
  data() {
    return {
      valido: false,
      modificar: false,
      autores: [],
      autor: {
        id: 0,
        nombre: "",
      },
      rules: {
        required: (value) => !!value || "Debés completar el campo",
        min: (v) => v.length >= 8 || "Al menos 8 carácteres",
        max: (v) => v.length <= 50 || "Cómo máximo 50 carácteres",
      },
    };
  },
  mounted() {
    this.obtenerAutores();
    this.buscarMensajeBorrado();
    this.buscarMensajeCreado();
    this.buscarMensajeEditado();
  },
  methods: {
    guardarAutor() {
      const objeto = { id: this.autor.id, nombre: this.autor.nombre };
      const resultado = this.$store.dispatch("guardarAutor", objeto);
      resultado
        .then((result) => {
          if (result.status === 201) {
            window.location.href =
              window.location.pathname + "?modifiedSuccess=1";
          }
          if (result.status === 204) {
            this.$toast.open({
              message: "No existe el autor que desea modificar",
              type: "warning",
              position: "top",
              duration: 5000,
            });
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al crear/modificar el archivo " + error);
          this.$toast.open({
            message: "Hubo un error al intentar crear/modificar el elemento",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    obtenerAutores() {
      const respuesta = this.$store.dispatch("getAutores");
      respuesta
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("Autores cargados correctamente -> dash/Autor");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("No hay Autores en la BD -> dash/Autor");
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Error al cargar Autores -> dash/Autor " + error);
          this.$toast.open({
            message: "Hubo un error al intentar obtener los Autores",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    cancelarModificar() {
      this.modificar = false;
      this.autor.id = 0;
      this.autor.nombre = "";
    },
    async editarDialogo(objeto) {
      if (
        await this.$refs.confirmar.open(
          "Editar autor",
          "Desea editar el autor: " + objeto.nombre
        )
      ) {
        this.autor.id = objeto.id;
        this.autor.nombre = objeto.nombre;
        this.modificar = true;
      }
    },
    async borrarDialogo(id, nombre) {
      if (
        await this.$refs.confirmar.open(
          "Borrar autor",
          "Desea borrar el autor: " + nombre
        )
      ) {
        const resultado = this.$store.dispatch("borrarAutor", id);
        resultado
          .then((result) => {
            if (result.status === 202) {
              window.location.href =
                window.location.pathname + "?deleteSuccess=1";
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Hubo un error al eliminar el autor " + error);
            this.$toast.open({
              message: "Hubo un error al intentar eliminar el elemento",
              type: "error",
              position: "top",
              duration: 5000,
            });
          });
      }
    },
    buscarMensajeBorrado() {
      const { search } = window.location;
      const deleteSuccess = new URLSearchParams(search).get("deleteSuccess");
      if (deleteSuccess === "1") {
        this.$toast.open({
          message: "Autor borrado correctamente",
          type: "error",
          position: "top",
          duration: 5000,
        });
      }
    },
    buscarMensajeCreado() {
      const { search } = window.location;
      const deleteSuccess = new URLSearchParams(search).get("createdSuccess");
      if (deleteSuccess === "1") {
        this.$toast.open({
          message: "Autor creado correctamente",
          type: "success",
          position: "top",
          duration: 5000,
        });
      }
    },
    buscarMensajeEditado() {
      const { search } = window.location;
      const deleteSuccess = new URLSearchParams(search).get("modifiedSuccess");
      if (deleteSuccess === "1") {
        this.$toast.open({
          message: "Autor modificado correctamente",
          type: "success",
          position: "top",
          duration: 5000,
        });
      }
    },
  },
};
</script>
