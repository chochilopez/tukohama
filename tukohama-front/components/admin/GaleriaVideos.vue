<template>
  <div>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-card outlined class="ma-5 mb-10 pa-2">
        <v-card-title>Archivos</v-card-title>
        <v-row align="center" align-content="center">
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-text-field
              v-model="archivo.nombreArchivo"
              dense
              outlined
              filled
              counter="50"
              clearable
              class="pt-2"
              label="Nombre del archivo"
              prepend-icon="mdi-file-music"
              :rules="[rules.required, rules.min, rules.maxString]"
            />
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
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
              :rules="[rules.maxString]"
            />
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-btn
              class="ma-5"
              color="light-blue darken-1"
              @click="elegirArchivo()"
              >Seleccionar archivo</v-btn
            >
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-file-input
              id="fileArchivo"
              v-model="archivo.archivo"
              accept="video/*"
              label="Seleccioná el archivo"
              filled
              prepend-icon="mdi-file-check"
              hint="Cargá el archivo"
              :rules="[rules.required]"
              @change="cargarArchivo()"
            />
          </v-col>
        </v-row>
        <v-row align="center" align-content="center">
          <v-col sm="6" md="9" align="center" align-content="center">
            <v-img v-if="url" :src="url" />
          </v-col>
          <v-col sm="6" md="3" align="center" align-content="center">
            <v-btn
              v-if="archivo.archivo != null"
              class="ma-5"
              color="warning"
              @click="subirArchivo()"
              >Subir archivo</v-btn
            >
          </v-col>
        </v-row>
      </v-card>
    </v-form>
    <v-row>
      <v-col v-for="archivo in archivos" :key="archivo.id" cols="12" md="4" >
        <v-card outlined class="ma-5 mb-10 pa-2">
          <v-card-text> Nombre archivo: {{ archivo.nombreArchivo }} </v-card-text>
          <v-img
            :src="`${PUBLIC_PATH}media/${archivo.path}`"
            :alt="archivo.descripcion"
          />
          <v-card-text>
            <ul>
              <li>Nombre: {{ archivo.nombreArchivo }}</li>
              <li>Descripcion: {{ archivo.descripcion }}</li>
              <li>Tamaño: {{ archivo.tamanio }}</li>
            </ul>
          </v-card-text>
          <v-card-actions>
            <v-chip color="success" label @click="verVideo(archivo)">
              <v-icon left> mdi-eye </v-icon>
              Ver
            </v-chip>
            <v-spacer />
            <v-chip color="primary" label @click="editarDialogo(archivo)">
              <v-icon left> mdi-home-edit </v-icon>
              Editar
            </v-chip>
            <v-spacer />
            <v-chip color="error" label @click="borrarDialogo(archivo)">
              <v-icon left> mdi-delete </v-icon>
              Borrar
            </v-chip>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
    <ConfirmarDialogo ref="confirmar" />
    <ModificarArchivoDialogo ref="modificarArchivo" />
    <VerVideoDialogo ref="verVideo" />
  </div>
</template>

<script>
import ConfirmarDialogo from "../dialogos/ConfirmarDialogo.vue";
import ModificarArchivoDialogo from "../dialogos/ModificarArchivoDialogo.vue";
import VerVideoDialogo from "../dialogos/VerVideoDialogo.vue";
import Helper from "~/services/helper";

export default {
  components: {
    ConfirmarDialogo,
    ModificarArchivoDialogo,
    VerVideoDialogo,
  },
  data() {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      formDataArchivo: null,
      archivo: {
        id: 0,
        archivo: null,
        nombreArchivo: "",
        descripcion: "",
        tamanio: 0,
      },
      archivos: [],
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
    this.obtenerArchivos();
    this.buscarMensajeBorrado();
    this.buscarMensajeCreado();
    this.buscarMensajeEditado();
  },
  methods: {
    async verVideo(objeto) {
      await this.$refs.verVideo.open(objeto);
    },
    elegirArchivo () {
      document.getElementById("fileArchivo").click();
    },
    async obtenerArchivos() {
      try {
        await this.$store.dispatch("getVideos");
      } catch (error) {
        // eslint-disable-next-line no-console
        console.error("video -> gallery -> fetch error -> " + error);
        this.$toast.open({
          message: "Hubo un error al intentar obtener los videos",
          type: "error",
          position: "top",
          duration: 5000,
        });
      }
      this.archivos = Helper.ordenarArrayCreadoDesc(this.$store.state.multimedia.videos)
    },
    cargarArchivo() {
      const file = this.archivo.archivo;
      this.url = URL.createObjectURL(file);
      this.formDataArchivo = new FormData();
      this.formDataArchivo.append("file", file);
      this.archivo.tamanio = file.size / 1000000;
    },
    subirArchivo() {
      if (
        this.$refs.form.validate() &&
        this.archivo.nombreArchivo != null &&
        this.archivo.descripcion != null
      ) {
        this.formDataArchivo.append("nombre", this.archivo.nombreArchivo);
        this.formDataArchivo.append("descripcion", this.archivo.descripcion);
        this.formDataArchivo.append("tamanio", this.archivo.tamanio);
        const respuesta = this.$store.dispatch(
          "guardarVideo",
          this.formDataArchivo
        );
        respuesta
          .then((response) => {
            if (response.status === 200) {
              window.location.href =
                window.location.pathname + "?createdSuccess=1";
            }
            if (response.status === 204) {
              this.$toast.open({
                message: "Ya existe un archivo con ese nombre",
                type: "warning",
                position: "top",
                duration: 5000,
              });
            }
          })
          .catch((error) => {
            this.$toast.open({
              message: "Error grave al subir el archivo " + error,
              type: "error",
              position: "top",
              duration: 5000,
            });
          });
      }
    },
    async editarDialogo(objeto) {
      const respuesta = await this.$refs.modificarArchivo.open(
        "Editar archivo",
        "Desea editar el archivo: " + objeto.nombreArchivo,
        objeto
      );
      const resultado = this.$store.dispatch("modificarMultimedia", respuesta);
      resultado
        .then((result) => {
          if (result.status === 201) {
            window.location.href =
              window.location.pathname + "?modifiedSuccess=1";
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al modificar el archivo " + error);
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
          "Borrar archivo",
          "Desea borrar el archivo: " + objeto.nombreArchivo + "?"
        )
      ) {
        const resultado = this.$store.dispatch("borrarMultimedia", objeto.id);
        resultado
          .then((result) => {
            if (result.status === 202) {
              window.location.href =
                window.location.pathname + "?deleteSuccess=1";
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Hubo un error al eliminar el archivo " + error);
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
          message: "Archivo borrado correctamente",
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
          message: "Archivo creado correctamente",
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
          message: "Archivo modificado correctamente",
          type: "info",
          position: "top",
          duration: 5000,
        });
      }
    },
  },
};
</script>
