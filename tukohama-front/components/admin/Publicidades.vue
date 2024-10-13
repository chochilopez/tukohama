<template>
  <div>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-card outlined class="ma-5 mb-10 pa-2">
        <v-card-title>Publicidades</v-card-title>
        <v-row align="center" align-content="center">
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-text-field
              v-model="publicidad.firma"
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
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-select
              v-model="publicidad.tipo"
              dense
              outlined
              filled
              prepend-icon="mdi-arrow-decision"
              item-text="nombre"
              item-value="id"
              :items="tipo"
              label="Elegí el tipo de publicidad"
              class="pt-2"
              :rules="[rules.required]"
            />
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-select
              v-model="publicidad.importancia"
              dense
              outlined
              filled
              prepend-icon="mdi-arrow-up-down-bold"
              item-text="valor"
              item-value="id"
              :items="importancia"
              label="Elegí la importancia"
              class="pt-2"
              :rules="[rules.required]"
            />
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-text-field
              v-model="publicidad.link"
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
        </v-row>
        <v-row align="center" align-content="center">
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-btn
              color="light-blue darken-1"
              class="ma-5"
              @click="elegirPublicidad()"
              >Seleccionar publicidad</v-btn
            >
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-file-input
              id="filePublicidad"
              v-model="publicidad.imagen"
              accept="image/*"
              label="Seleccioná la publicidad"
              filled
              prepend-icon="mdi-account-cash"
              hint="Cargá la publicidad"
              :rules="[rules.required]"
              @change="cargarPublicidad()"
            />
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-img v-if="url" :src="url" />
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-btn
              v-if="publicidad.imagen != null"
              class="ma-5"
              color="warning"
              @click="subirPublicidad()"
              >Subir imagen</v-btn
            >
          </v-col>
          <span class="ma-5"
            >Conversor de mp4 a gif
            <a href="https://imagen.online-convert.com/es/convertir/mp4-a-gif"
              >Online-Converter</a
            ></span
          >
        </v-row>
      </v-card>
    </v-form>
    <v-row>
      <v-col
        v-for="publi in publicidades"
        :key="publi.id"
        cols="12"
        md="4"
      >
        <v-card outlined class="ma-5 mb-10 pa-2">
          <v-card-text> Firma/Empresa: {{ publi.firma }} </v-card-text>
          <v-img
            :src="`${PUBLIC_PATH}publicidades/${publi.path}`"
            :alt="publi.firma"
          />
          <v-card-text>
            <ul>
              <li>Empresa: {{ publi.firma }}</li>
              <li>Link web/facebook: {{ publi.link }}</li>
              <li>Importancia: {{ publi.importancia }}</li>
              <li>Tipo: {{ publi.tipo }}</li>
            </ul>
          </v-card-text>
          <v-card-actions>
            <v-chip
              class="ml-10"
              color="primary"
              label
              @click="editarDialogo(publi)"
            >
              <v-icon left> mdi-home-edit </v-icon>
              Editar
            </v-chip>
            <v-spacer />
            <v-chip
              class="mr-10"
              color="error"
              label
              @click="borrarDialogo(publi)"
            >
              <v-icon left> mdi-delete </v-icon>
              Borrar
            </v-chip>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <ConfirmarDialogo ref="confirmar" />
    <ModificarPublicidadDialogo ref="modificarPublicidad" />
  </div>
</template>

<script>
import ConfirmarDialogo from "../dialogos/ConfirmarDialogo.vue";
import ModificarPublicidadDialogo from "../dialogos/ModificarPublicidadDialogo.vue";

export default {
  components: {
    ConfirmarDialogo,
    ModificarPublicidadDialogo,
  },
  data() {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      formDataPublicidad: null,
      publicidad: {
        imagen: null,
        firma: "",
        link: "",
        nombreArchivo: null,
        importancia: null,
        tipo: null,
      },
      importancia: [
        { id: 1, valor: "Muy alta" },
        { id: 2, valor: "Alta" },
        { id: 3, valor: "Media" },
        { id: 4, valor: "Baja" },
        { id: 5, valor: "Muy baja" },
      ],
      tipo: [
        { id: 1, nombre: "Tarjeta" },
        { id: 2, nombre: "Baner" },
      ],
      publicidades: [],
      valid: true,
      url: null,
      rules: {
        required: (v) => !!v || "Debés completar el campo",
        min: (v) => v.length >= 3 || "Al menos 8 carácteres",
        maxString: (v) => v.length <= 100 || "Cómo máximo 100 carácteres",
        maxText: (v) => v.length <= 500 || "Cómo máximo 500 carácteres",
      },
    };
  },
  mounted() {
    this.obtenerPublicidades();
    this.buscarMensajeBorrado();
    this.buscarMensajeCreado();
    this.buscarMensajeEditado();
  },
  methods: {
    elegirPublicidad () {
      document.getElementById("filePublicidad").click();
    },
    obtenerPublicidades() {
      const respuesta = this.$store.dispatch("getPublicidades");
      respuesta
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("Publicidades cargadas correctamente -> dash/Publicidad");
            this.publicidades = result.data;
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("No hay Publicidades en la BD -> dash/Publicidad");
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Error al cargar Publicidades -> dash/Publicidad" + error);
          this.$toast.open({
            message: "Hubo un error al intentar obtener los Publicidad",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    cargarPublicidad() {
      const file = this.publicidad.imagen;
      this.url = URL.createObjectURL(file);
      this.formDataPublicidad = new FormData();
      this.formDataPublicidad.append("file", file);
    },
    subirPublicidad() {
      if (
        this.$refs.form.validate() &&
        this.publicidad.firma != null &&
        this.publicidad.importancia != null &&
        this.publicidad.tipo != null
      ) {
        this.formDataPublicidad.append("firma", this.publicidad.firma);
        this.formDataPublicidad.append("link", this.publicidad.link);
        this.formDataPublicidad.append(
          "importancia",
          this.publicidad.importancia
        );
        this.formDataPublicidad.append("tipo", this.publicidad.tipo);
        const respuesta = this.$store.dispatch(
          "guardarPublicidad",
          this.formDataPublicidad
        );
        respuesta
          .then((response) => {
            if (response.status === 200) {
              window.location.href =
                window.location.pathname + "?createdSuccess=1";
            }
            if (response.status === 204) {
              this.$toast.open({
                message: "Ya existe una publicidad con ese nombre",
                type: "warning",
                position: "top",
                duration: 5000,
              });
            }
          })
          .catch((error) => {
            this.$toast.open({
              message: "Error grave al subir la publicidades " + error,
              type: "error",
              position: "top",
              duration: 5000,
            });
          });
      }
    },
    async editarDialogo(objeto) {
      const respuesta = await this.$refs.modificarPublicidad.open(
        "Editar Publicidad",
        "Desea editar la publicidad: " + objeto.firma,
        objeto
      );
      const resultado = this.$store.dispatch("modificarPublicidad", respuesta);
      resultado
        .then((result) => {
          if (result.status === 201) {
            window.location.href =
              window.location.pathname + "?modifiedSuccess=1";
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al modificar la publicidad " + error);
          this.$toast.open({
            message: "Hubo un error al intentar modificar el elemento",
            type: "warning",
            position: "top",
            duration: 5000,
          });
        });
    },
    async borrarDialogo(objeto) {
      if (
        await this.$refs.confirmar.open(
          "Borrar Publicidad",
          "Desea borrar el Publicidad: " + objeto.firma + "?"
        )
      ) {
        const resultado = this.$store.dispatch("borrarPublicidad", objeto.id);
        resultado
          .then((result) => {
            if (result.status === 202) {
              window.location.href =
                window.location.pathname + "?deleteSuccess=1";
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Hubo un error al eliminar la publicidad " + error);
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
          message: "Publicidad borrada correctamente",
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
          message: "Publicidad creada correctamente",
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
          message: "Publicidad modificada correctamente",
          type: "info",
          position: "top",
          duration: 5000,
        });
      }
    },
  },
};
</script>
