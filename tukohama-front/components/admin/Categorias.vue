<template>
  <div>
    <v-row>
      <v-col cols="12" md="4">
        <v-card outlined class="pa-3">
          <v-form
            ref="form"
            v-model="valido"
            lazy-validation
            @submit.prevent="guardarCategoria"
          >
            <p v-if="!modificar" class="text-h5 text--primary">
              Nueva categoría
            </p>
            <p v-if="modificar" class="text-h5 text--primary">
              Modificar categoría
            </p>
            <label style="font-size: 14px"
              >Seleccioná el color de la categoría</label
            >
            <v-color-picker
              v-model="categoria.color"
              dot-size="27"
              mode="hexa"
              label="Seleccina el color de la categoría"
              hide-inputs
            ></v-color-picker>
            <v-text-field
              v-model="categoria.nombre"
              label="Ingresá el nombre de la categoría"
              outlined
              persistent-hint
              filled
              required
              :rules="[rules.required, rules.min, rules.max]"
            ></v-text-field>
            <v-text-field
              v-model="categoria.icono"
              label="Ingresa el nombre de icono - de la biblioteca mdi"
              outlined
              persistent-hint
              filled
            ></v-text-field>
            <v-chip
              class="ma-2"
              :color="categoria.color"
              label
              text-color="white"
            >
              <v-icon left>{{ categoria.icono }}</v-icon
              >{{ categoria.nombre }} </v-chip
            ><br />
            Podés encontrar mas iconos
            <a href="https://materialdesignicons.com/">acá</a>, solo poné el
            nombre.
            <div v-if="!modificar" class="d-flex d-inline">
              <v-spacer />
              <v-btn
                color="warning"
                class="ma-2 white--text"
                type="submit"
                :disabled="!valido || categoria.nombre === ''"
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
          <p class="text-h5 text--primary">Categorias existentes</p>
          <v-simple-table>
            <template #default>
              <thead>
                <tr>
                  <th class="text-left">Nombre</th>
                  <th class="text-left">Color</th>
                  <th class="text-left">Icono</th>
                  <th class="text-left">Categoria</th>
                  <th class="text-left">Accion</th>
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="categoriaListado in $store.state.categoria.categorias"
                  :key="categoriaListado.id"
                >
                  <td>{{ categoriaListado.nombre }}</td>
                  <td>{{ categoriaListado.color }}</td>
                  <td>{{ categoriaListado.icono }}</td>
                  <td>
                    <v-chip
                      class="ma-2"
                      :color="categoriaListado.color"
                      label
                      text-color="white"
                    >
                      <v-icon left>{{ categoriaListado.icono }}</v-icon
                      >{{ categoriaListado.nombre }}
                    </v-chip>
                  </td>
                  <td>
                    <v-chip
                      class="ma-2"
                      color="primary"
                      label
                      @click="editarDialogo(categoriaListado)"
                    >
                      <v-icon left> mdi-home-edit </v-icon>
                      Editar
                    </v-chip>
                    <v-chip
                      class="ma-2"
                      color="error"
                      label
                      @click="
                        borrarDialogo(
                          categoriaListado.id,
                          categoriaListado.nombre
                        )
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
      categoria: {
        id: 0,
        nombre: "",
        color: "#0277BD",
        icono: "mdi-bullhorn",
      },
      rules: {
        required: (value) => !!value || "Debés completar el campo",
        min: (v) => v.length >= 3 || "Al menos 3 carácteres",
        max: (v) => v.length <= 50 || "Cómo máximo 50 carácteres",
      },
    };
  },
  mounted() {
    this.obtenerCategorias();
    this.buscarMensajeBorrado();
    this.buscarMensajeCreado();
    this.buscarMensajeEditado();
  },
  methods: {
    guardarCategoria() {
      const objeto = {
        id: this.categoria.id,
        nombre: this.categoria.nombre,
        icono: this.categoria.icono,
        color: this.categoria.color,
      };
      const respuesta = this.$store.dispatch("guardarCategoria", objeto);
      respuesta
        .then((result) => {
          if (result.status === 201) {
            window.location.href =
              window.location.pathname + "?modifiedSuccess=1";
          }
          if (result.status === 204) {
            this.$toast.open({
              message: "No existe la categoría que desea modificar",
              type: "warning",
              position: "top",
              duration: 5000,
            });
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al crear/modificar la categoria " + error);
          this.$toast.open({
            message: "Hubo un error al intentar crear/modificar el elemento",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    obtenerCategorias() {
      const respuesta = this.$store.dispatch("getCategorias");
      respuesta
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("Categorias cargadas correctamente -> dash/Categoria");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("No hay Categorias en la BD -> dash/Categoria");
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error(
            "Error al cargar Categorias -> dash/Categoria " + error
          );
          this.$toast.open({
            message: "Hubo un error al intentar obtener los Categorias",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    cancelarModificar() {
      this.modificar = false;
      this.categoria.id = 0;
      this.categoria.nombre = "";
      this.categoria.color = "#0277BD";
      this.categoria.icono = "mdi-bullhorn";
    },
    async editarDialogo(objeto) {
      if (
        await this.$refs.confirmar.open(
          "Editar categoria",
          "Desea editar la categoria: " + objeto.nombre
        )
      ) {
        this.categoria.id = objeto.id;
        this.categoria.nombre = objeto.nombre;
        this.categoria.color = objeto.color;
        this.categoria.icono = objeto.icono;
        this.modificar = true;
      }
    },
    async borrarDialogo(id, nombre) {
      if (
        await this.$refs.confirmar.open(
          "Borrar categoria",
          "Desea borrar la categoria: " + nombre
        )
      ) {
        const resultado = this.$store.dispatch("borrarCategoria", id);
        resultado
          .then((result) => {
            if (result.status === 202) {
              window.location.href =
                window.location.pathname + "?deleteSuccess=1";
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Hubo un error al eliminar la categoria " + error);
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
          message: "Categoria borrada correctamente",
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
          message: "Categoria creada correctamente",
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
          message: "Categoria modificada correctamente",
          type: "success",
          position: "top",
          duration: 5000,
        });
      }
    },
  },
};
</script>
