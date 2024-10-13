<template>
  <v-container>
    <v-form ref="form" v-model="valid" lazy-validation>
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field
            v-model="noticia.titulo"
            dense
            outlined
            filled
            clearable
            class="pt-2"
            label="Titulo"
            prepend-icon="mdi-format-title"
            :rules="[rules.required, rules.min, rules.maxString]"
            counter="100"
            :disabled="!formulario"
          />
          <v-text-field
            v-model="noticia.meta"
            dense
            outlined
            filled
            counter="100"
            clearable
            class="pt-2"
            label="Meta Tag"
            prepend-icon="mdi-tag"
            :rules="[rules.required, rules.min, rules.maxString]"
            :disabled="!formulario"
          />
          <v-select
            v-model="autor.id"
            dense
            outlined
            filled
            prepend-icon="mdi-account-edit"
            item-text="nombre"
            item-value="id"
            :items="comboAutores"
            label="Elegí el autor de la noticia"
            :rules="[rules.required]"
            :disabled="!formulario"
          />
          <v-select
            v-model="categoria.id"
            dense
            outlined
            filled
            item-text="nombre"
            item-value="id"
            prepend-icon="mdi-arrow-split-vertical"
            :items="comboCategorias"
            label="Elegí la categoría de la noticia"
            :rules="[rules.required]"
            :disabled="!formulario"
          />
          <v-select
            v-model="importancia"
            dense
            outlined
            filled
            prepend-icon="mdi-arrow-up-down"
            item-text="valor"
            item-value="id"
            :items="comboImportancia"
            label="Elegí la importancia de la noticia"
            :rules="[rules.required]"
            :disabled="!formulario"
          />
          <v-textarea
            v-model="noticia.copete"
            dense
            outlined
            filled
            counter="500"
            clearable
            class="pt-2"
            height="200"
            label="Copete"
            :rules="[rules.required, rules.min, rules.maxText]"
            :disabled="!formulario"
          />
        </v-col>
        <v-col cols="12" md="6">
          <v-btn
            color="info"
            class="ml-4 mr-4"
            :disabled="!formulario"
            @click="cargarImagen"
            >Seleccionar imagen portada</v-btn
          >
          // eslint-disable-next-line vue/no-lone-template
          <template>
            <v-img
              :src="`${PUBLIC_PATH}media/${noticia.imagen}`"
              height="390"
              class="ma-4"
              :disabled="!formulario"
            />
          </template>

          <v-switch
            v-model="noticia.comentarios"
            label="Permimir comentarios"
            :disabled="!formulario"
          />
          <v-switch
            v-model="noticia.portada"
            label="Noticia en portada"
            :disabled="!formulario"
          />
        </v-col>
      </v-row>
      <v-card outlined>
        <v-row>
          <v-col>
            <v-btn
              color="info"
              class="ma-2"
              :disabled="!formulario"
              @click="cargarAudios"
              >Seleccionar audios</v-btn
            >
          </v-col>
        </v-row>
        <v-row v-if="audios.length > 0">
          <v-col>
            <v-simple-table>
              <template #default>
                <thead>
                  <tr>
                    <th class="text-left">Audio</th>
                    <th class="text-left">Nombre</th>
                    <th class="text-left">Quitar</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="audio in audios" :key="audio.id">
                    <td>
                    </td>
                    <td>
                      {{ audio.nombre }}
                    </td>
                    <td>
                      <v-chip class="ma-2" color="error" label>
                        <v-icon left> mdi-delete </v-icon>
                        Borrar
                      </v-chip>
                    </td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-col>
        </v-row>
      </v-card>
      <v-card outlined>
        <v-row>
          <v-col>
            <v-btn
              color="info"
              class="ma-2"
              :disabled="!formulario"
              @click="cargarImagenes"
              >Seleccionar imagenes</v-btn
            >
          </v-col>
        </v-row>
        <v-row v-if="imagenes.length > 0">
          <v-col>
            <v-simple-table>
              <template #default>
                <thead>
                  <tr>
                    <th class="text-left">Foto</th>
                    <th class="text-left">Nombre</th>
                    <th class="text-left">Quitar</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="imagen in imagenes" :key="imagen.id">
                    <td>
                      <v-img
                        :src="`${PUBLIC_PATH}media/${imagen.path}`"
                        max-height="100"
                        max-width="100"
                        class="ma-2"
                      />
                    </td>
                    <td>
                      {{ imagen.nombre }}
                    </td>
                    <td>
                      <v-chip class="ma-2" color="error" label>
                        <v-icon left> mdi-delete </v-icon>
                        Borrar
                      </v-chip>
                    </td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-col>
        </v-row>
      </v-card>
      <v-card outlined>
        <v-row>
          <v-col>
            <v-btn
              color="info"
              class="ma-2"
              :disabled="!formulario"
              @click="cargarVideos"
              >Seleccionar videos</v-btn
            >
          </v-col>
        </v-row>
        <v-row v-if="videos.length > 0">
          <v-col>
            <v-simple-table>
              <template #default>
                <thead>
                  <tr>
                    <th class="text-left">Video</th>
                    <th class="text-left">Nombre</th>
                    <th class="text-left">Quitar</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="video in videos" :key="video.id">
                    <td>
                      <vue-core-video-player
                        :src="`${PUBLIC_PATH}media/${video.path}`"
                        resolution="360p"
                        muted
                        loop
                      ></vue-core-video-player>
                    </td>
                    <td>
                      {{ video.nombre }}
                    </td>
                    <td>
                      <v-chip class="ma-2" color="error" label>
                        <v-icon left> mdi-delete </v-icon>
                        Borrar
                      </v-chip>
                    </td>
                  </tr>
                </tbody>
              </template>
            </v-simple-table>
          </v-col>
        </v-row>
      </v-card>
      <v-row>
        <v-col cols="12">
          <VueEditor v-model="noticia.cuerpo" :disabled="!formulario" />
          <!--VueEditor v-model="noticia.cuerpo" useCustomImageHandler @imageAdded="handleImageAdded"/-->
        </v-col>
      </v-row>
      <v-row>
        <v-btn
          :disabled="!botonCancelar"
          color="error"
          class="ma-2 white--text"
          @click="cancelarBorrador()"
        >
          Descartar borrador
          <v-icon right dark> mdi-cancel </v-icon>
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          :disabled="!botonEditar"
          color="error"
          class="ma-2 white--text"
          @click="editarNoticia()"
        >
          Editar noticia
          <v-icon right dark> mdi-pen </v-icon>
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn
          :disabled="!botonPrevia"
          color="lime darken-2"
          class="ma-2 white--text"
          @click="vistaPrevia()"
        >
          Vista previa
          <v-icon right dark> mdi-table-eye </v-icon>
        </v-btn>
        <v-spacer />
        <v-btn
          :disabled="!valid && botonGuardar"
          color="warning"
          class="ma-2 white--text"
          @click="guardarNoticia()"
        >
          Guardar
          <v-icon right dark> mdi-content-save </v-icon>
        </v-btn>
        <v-spacer />
        <v-btn
          :disabled="!botonPublicable"
          color="cyan darken-2"
          class="ma-2 white--text"
          @click="publicarNoticia()"
        >
          Publicar
          <v-icon right dark> mdi-arrow-up-bold-circle </v-icon>
        </v-btn>
      </v-row>
    </v-form>
    <SeleccionarAudioDialogo ref="seleccionarAudio" />
    <SeleccionarImagenDialogo ref="seleccionarImagen" />
    <SeleccionarVideoDialogo ref="seleccionarVideo" />
  </v-container>
</template>

<script>
import SeleccionarAudioDialogo from "../dialogos/SeleccionarAudioDialogo.vue";
import SeleccionarImagenDialogo from "../dialogos/SeleccionarImagenDialogo.vue";
import SeleccionarVideoDialogo from "../dialogos/SeleccionarVideoDialogo.vue";

export default {
  components: {
    SeleccionarAudioDialogo,
    SeleccionarImagenDialogo,
    SeleccionarVideoDialogo,
  },
  data() {
    return {
      PUBLIC_PATH: process.env.BASE_URL,
      valid: true,
      idEditable: null,
      botonPublicable: false,
      botonGuardar: false,
      formulario: true,
      botonEditar: false,
      botonCancelar: true,
      botonPrevia: false,
      rules: {
        required: (v) => !!v || "Debés completar el campo",
        min: (v) => v.length >= 8 || "Al menos 8 carácteres",
        maxString: (v) => v.length <= 100 || "Cómo máximo 100 carácteres",
        maxText: (v) => v.length <= 500 || "Cómo máximo 500 carácteres",
      },
      comboImportancia: [],
      comboCategorias: [],
      comboAutores: [],
      categoria: { id: 0, nombre: "", color: "", icono: "" },
      importancia: 0,
      autor: { id: 0, nombre: "" },
      imagenPortada: {
        id: null,
      },
      noticia: {
        id: null,
        titulo: "",
        copete: "",
        meta: "",
        comentarios: true,
        portada: true,
        cuerpo: "",
        fecha: "",
        imagen: "vacio.png",
        audios: [],
        imagenes: [],
        videos: [],
      },
      audios: [],
      imagenes: [],
      videos: [],
    };
  },
  mounted() {
    this.obtenerCategorias();
    this.obtenerAutores();
    this.comboImportancia.push(
      { id: 1, valor: "Importancia muy alta" },
      { id: 2, valor: "Importancia alta" },
      { id: 3, valor: "Importancia baja" },
      { id: 4, valor: "Importancia muy baja" }
    );
    this.editarNoticia();
    this.buscarMensajePublicado();
  },
  methods: {
    // TODO boton vista previa sin método
    // TODO boton editar luego de noticia guardada no hace nada
    reset() {
      this.$refs.form.reset();
    },
    async cargarImagen() {
      const respuesta = await this.$refs.seleccionarImagen.open();
      if (respuesta) {
        this.imagenPortada.id = respuesta.id;
        this.noticia.imagen = respuesta.path;
      }
    },
    async cargarAudios() {
      const respuesta = await this.$refs.seleccionarAudio.open();
      if (respuesta) {
        if (!this.noticia.audios.includes(respuesta.id)) {
          this.noticia.audios.push(respuesta.id);
          this.audios.push(respuesta);
        } else {
          this.$toast.open({
            message: "El audio seleccionada ya fue agregada",
            type: "warning",
            position: "top",
            duration: 5000,
          });
        }
      }
    },
    async cargarImagenes() {
      const respuesta = await this.$refs.seleccionarImagen.open();
      if (respuesta) {
        if (!this.noticia.imagenes.includes(respuesta.id)) {
          this.noticia.imagenes.push(respuesta.id);
          this.imagenes.push(respuesta);
        } else {
          this.$toast.open({
            message: "La imagen seleccionada ya fue agregada",
            type: "warning",
            position: "top",
            duration: 5000,
          });
        }
      }
    },
    async cargarVideos() {
      const respuesta = await this.$refs.seleccionarVideo.open();
      if (respuesta) {
        if (!this.noticia.videos.includes(respuesta.id)) {
          this.noticia.videos.push(respuesta.id);
          this.videos.push(respuesta);
        } else {
          this.$toast.open({
            message: "El video seleccionada ya fue agregada",
            type: "warning",
            position: "top",
            duration: 5000,
          });
        }
      }
    },
    vistaPrevia() {},
    guardarNoticia() {
      if (
        this.$refs.form.validate() &&
        this.noticia.cuerpo !== "" &&
        this.imagenPortada.id != null
      ) {
        const noticia = {
          id: this.noticia.id,
          titulo: this.noticia.titulo,
          copete: this.noticia.copete,
          cuerpo: this.noticia.cuerpo,
          meta: this.noticia.meta,
          importancia: this.importancia,
          publicable: true,
          comentarios: this.noticia.comentarios,
          portada: this.noticia.portada,
          idCategoria: this.categoria.id,
          idAutor: this.autor.id,
          idImagenPortada: this.imagenPortada.id,
          idsAudios: this.noticia.audios,
          idsImagenes: this.noticia.imagenes,
          idsVideos: this.noticia.videos,
        };
        const respuesta = this.$store.dispatch("guardarNoticia", noticia);
        respuesta
          .then((result) => {
            if (result.status === 201) {
              this.noticia.id = result.data.id;
              this.botonCancelar = false;
              this.botonPrevia = true;
              this.botonPublicable = true;
              this.botonGuardar = false;
              this.botonEditar = true;
              this.$toast.open({
                message: "Tu noticia se encuentra lista para publicarse",
                type: "success",
                position: "top",
                duration: 5000,
              });
              this.formulario = false;
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Hubo un error al crear/modificar la noticia " + error);
            this.$toast.open({
              message: "Hubo un error al intentar crear/modificar el elemento",
              type: "warning",
              position: "top",
              duration: 5000,
            });
          });
      } else {
        this.$toast.open({
          message: "Tenés que compconstar todos los campos",
          type: "error",
          position: "top",
          duration: 5000,
        });
      }
    },
    publicarNoticia() {
      const respuesta = this.$store.dispatch("publicarNoticia", this.noticia.id);
      respuesta
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log(result);
            window.location.href =
              window.location.pathname + "?publishedSuccess=1";
          }
        })
        .catch((error) => {
          this.$toast.open({
            message: "Hubo un error al intentar publicar la noticia",
            type: "warning",
            position: "top",
            duration: 5000,
          });
          // eslint-disable-next-line no-console
          console.error("Ocurrio un error al intentar publicar la noticia " + error);
        });
    },
    obtenerCategorias() {
      const respuesta = this.$store.dispatch("getCategorias");
      respuesta
        .then((result) => {
          for (const prop in result.data) {
            this.comboCategorias.push(result.data[prop]);
          }
        })
        .catch((error) => {
          this.$toast.open({
            message: "Hubo un error al intentar obtener las categorias",
            type: "error",
            position: "top",
            duration: 5000,
          });
          // eslint-disable-next-line no-console
          console.error("Ocurrio un error al intentar obtener las categorias " + error);
        });
    },
    obtenerAutores() {
      const respuesta = this.$store.dispatch("getAutores");
      respuesta
        .then((result) => {
          for (const prop in result.data) {
            this.comboAutores.push(result.data[prop]);
          }
        })
        .catch((error) => {
          this.$toast.open({
            message: "Hubo un error al intentar obtener los autores",
            type: "error",
            position: "top",
            duration: 5000,
          });
          // eslint-disable-next-line no-console
          console.error("Ocurrio un error al intentar obtener los autores " + error);
        });
    },
    cancelarBorrador() {
      this.$router.push("/dashboard/noticias");
    },
    editarNoticia() {
      const { search } = window.location;
      const editar = new URLSearchParams(search).get("idNoticia");
      if (editar != null) this.noticia.id = editar;
      if (this.idEditable != null) this.noticia.id = this.idEditable;
      if (this.noticia.id != null) {
        const despublicar = this.$store.dispatch(
          "despublicarNoticia",
          this.noticia.id
        );
        despublicar
          .then((result) => {
            // eslint-disable-next-line no-console
            console.log("Noticia despublicada " + result);
          })
          .catch((error) => {
            this.$toast.open({
              message: "Hubo un error al intentar despublicar la noticia",
              type: "warning",
              position: "top",
              duration: 5000,
            });
            // eslint-disable-next-line no-console
            console.error("Ocurrio un error al intentar despublicar la noticia " + error);
          });
        const respuesta=this.$store.dispatch("getNoticiaPorId", this.noticia.id);
        respuesta
          .then((result) => {
            this.noticia.id = result.data.id;
            this.noticia.titulo = result.data.titulo;
            this.noticia.copete = result.data.copete;
            this.noticia.meta = result.data.meta;
            this.noticia.cuerpo = result.data.cuerpo;
            this.noticia.imagen = result.data.imagenPortada.path;
            this.noticia.comentarios = result.data.comentarios;
            this.noticia.portada = result.data.portada;
            this.categoria.id = result.data.categoria.id;
            this.autor.id = result.data.autor.id;
            this.importancia = result.data.importancia;
            this.imagenPortada.id = result.data.imagenPortada.id;
            this.audios = result.data.audios;
            this.imagenes = result.data.imagenes;
            this.videos = result.data.videos;
          })
          .catch((error) => {
            this.$toast.open({
              message: "Hubo un error al intentar obtener la noticia",
              type: "warning",
              position: "top",
              duration: 5000,
            });
            // eslint-disable-next-line no-console
            console.error("Ocurrio un error al intentar obtener la noticia " + error);
          });
      }
    },
    buscarMensajePublicado() {
      const { search } = window.location;
      const publishedSuccess = new URLSearchParams(search).get(
        "publishedSuccess"
      );
      if (publishedSuccess === "1") {
        this.$toast.open({
          message: "Noticia publicada correctamente",
          type: "success",
          position: "top",
          duration: 5000,
        });
      }
    },
  },
};
</script>
