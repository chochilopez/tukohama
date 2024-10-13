<template>
  <div>
    <v-card>
      <v-card-title>Noticias</v-card-title>
      <v-tabs color="blue" grow background-color="transparent">
        <v-tab href="#tab-1">
          Publicadas
          <v-avatar color="teal darken-1" size="30" class="ml-1">
            <span class="white--text text-h6">{{ cantidad.publicada }}</span>
          </v-avatar>
        </v-tab>
        <v-tab href="#tab-2">
          Borradores
          <v-avatar color="indigo darken-1" size="30" class="ml-1">
            <span class="white--text text-h6">{{ cantidad.borrador }}</span>
          </v-avatar>
        </v-tab>
        <v-tab href="#tab-3">
          Borradas
          <v-avatar color="red darken-1" size="30" class="ml-1">
            <span class="white--text text-h6">{{ cantidad.borrada }}</span>
          </v-avatar>
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tabs">
        <v-tab-item value="tab-1">
          <v-row class="pa-3"> </v-row>
          <v-row class="pa-3">
            <v-col>
              <v-btn href="/dashboard/nueva" color="red">Nueva noticia</v-btn>
            </v-col>
            <v-spacer />
            <v-col>
              <v-text-field
                v-model="buscar"
                append-icon="mdi-magnify"
                label="Buscar"
                clearable
              />
            </v-col>
          </v-row>
          <v-data-table
            :headers="headers"
            :items="noticiasPublicadas"
            sort-by="creado"
            sort-desc
            class="elevation-1"
          >
            <template v-slot:item.acciones="{ item }">
              <v-chip
                class="ma-2"
                color="primary"
                label
                @click="editarDialogo(item)"
              >
                <v-icon left> mdi-home-edit </v-icon>
                Editar
              </v-chip>
              <v-chip
                class="ma-2"
                color="error"
                label
                @click="borrarDialogo(item)"
              >
                <v-icon left> mdi-delete </v-icon>
                Borrar
              </v-chip>
            </template>
          </v-data-table>
        </v-tab-item>
        <v-tab-item value="tab-2">
          <v-row class="pa-3">
            <v-spacer />
            <v-col>
              <v-text-field
                v-model="buscar"
                append-icon="mdi-magnify"
                label="Buscar"
                clearable
              />
            </v-col>
          </v-row>
          <v-data-table
            :headers="headers"
            :items="noticiasBorradores"
            sort-by="creado"
            sort-desc
            class="elevation-1"
          >
            <template v-slot:item.acciones="{ item }">
              <v-chip
                :disabled="!item.publicable"
                class="ma-2"
                color="cyan darken-2"
                label
                @click="publicarNoticia(item)"
              >
                <v-icon left> mdi-pen </v-icon>
                Publicar
              </v-chip>
              <v-chip
                class="ma-2"
                color="primary"
                label
                @click="editarDialogo(item)"
              >
                <v-icon left> mdi-home-edit </v-icon>
                Editar
              </v-chip>
              <v-chip
                class="ma-2"
                color="error"
                label
                @click="borrarDialogo(item)"
              >
                <v-icon left> mdi-delete </v-icon>
                Borrar
              </v-chip>
            </template>
          </v-data-table>
        </v-tab-item>
        <v-tab-item value="tab-3">
          <v-row class="pa-3">
            <v-col>
              <v-btn href="/dashboard/nueva" color="red">Nueva noticia</v-btn>
            </v-col>
            <v-spacer />
            <v-col>
              <v-text-field
                v-model="buscar"
                append-icon="mdi-magnify"
                label="Buscar"
                clearable
              />
            </v-col>
          </v-row>
          <v-data-table
            :headers="headers"
            :items="noticiasBorradas"
            sort-by="creado"
            sort-desc
            class="elevation-1"
          >
            <template v-slot:item.acciones="{ item }">
              <v-chip
                class="ma-2"
                color="success"
                label
                @click="restaurarDialogo(item)"
              >
                <v-icon left> mdi-delete-restore </v-icon>
                Restaurar
              </v-chip>
            </template>
          </v-data-table>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
    <ConfirmarDialogo ref="confirmar" />
  </div>
</template>

<script>
import ConfirmarDialogo from "../dialogos/ConfirmarDialogo";

export default {
  components: {
    ConfirmarDialogo,
  },
  data() {
    return {
      cantidad: {
        publicada: 0,
        borrada: 0,
        borrador: 0,
      },
      noticiasPublicadas: [],
      noticiasBorradores: [],
      noticiasBorradas: [],
      tabs: null,
      buscar: "",
      headers: [
        {
          text: "Titulo",
          align: "start",
          sortable: true,
          filterable: true,
          value: "titulo",
        },
        { text: "Categoria", value: "categoria.nombre", filterable: false },
        { text: "Autor", value: "autor.nombre", filterable: false },
        { text: "Vistas", value: "vistas", filterable: false },
        { text: "Portada", value: "portada", filterable: false },
        { text: "Importancia", value: "importancia", filterable: false },
        { text: "Creado", value: "creado", filterable: false, sortable: true },
        {
          text: "Acciones",
          value: "acciones",
          filterable: false,
          sortable: false,
        },
      ],
    };
  },
  created() {
    this.obtenerNoticiasPublicadas();
    this.obtenerNoticiasBorradores();
    this.obtenerNoticiasBorradas();
    this.buscarMensajeBorrado();
    this.buscarMensajeRestaurado();
    this.buscarMensajePublicado();
  },
  methods: {
    publicarNoticia(objeto) {
      const respuesta = this.$store.dispatch("publicarNoticia", objeto.id);
      respuesta
        .then((result) => {
          if (result.status === 201) {
            window.location.href =
              window.location.pathname + "?publishedSuccess=1";
          } else if (result.status === 204) {
            this.$toast.open({
              message: "La noticia no se puede publicar",
              type: "warning",
              position: "top",
              duration: 5000,
            });
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al intentar publicar la noticia " + error);
          this.$toast.open({
            message: "Hubo un error al intentar publicar la noticia",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    // TODO Para ver todas las noticias hay que paginarlas
    // TODO boton despublicar en publicadas
    obtenerNoticiasPublicadas() {
      const respuesta = this.$store.dispatch("getNoticiasPublicadas");
      respuesta
        .then((result) => {
          this.noticiasPublicadas = result.data;
          this.cantidad.publicada = this.noticiasPublicadas.length;
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al intentar obtener las noticias publicadas " + error);
          this.$toast.open({
            message: "Hubo un error al intentar obtener las noticias publicadas",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    obtenerNoticiasBorradores() {
      const respuesta = this.$store.dispatch("getNoticiasBorradores");
      respuesta
        .then((result) => {
          this.noticiasBorradores = result.data;
          this.cantidad.borrador = this.noticiasBorradores.length;
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al intentar obtener las noticias borradores " + error);
          this.$toast.open({
            message: "Hubo un error al intentar obtener las noticias borradores",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    obtenerNoticiasBorradas() {
      const respuesta = this.$store.dispatch("getNoticiasBorradas");
      respuesta
        .then((result) => {
          this.noticiasBorradas = result.data;
          this.cantidad.borrada = this.noticiasBorradas.length;
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Hubo un error al intentar obtener las noticias borradas " + error);
          this.$toast.open({
            message: "Hubo un error al intentar obtener las noticias borradas",
            type: "error",
            position: "top",
            duration: 5000,
          });
        });
    },
    async restaurarDialogo(objeto) {
      if (
        await this.$refs.confirmar.open(
          "Restaurar noticia",
          "Desea restaurar la noticia con titulo: <br>" + objeto.titulo
        )
      ) {
        const resultado = this.$store.dispatch("restaurarNoticia", objeto.id);
        resultado
          .then((result) => {
            if (result.status === 202) {
              window.location.href =
                window.location.pathname + "?restoredSuccess=1";
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Ocurrio un error al intentar restaurar la noticia " + error);
            this.$toast.open({
              message: "Hubo un error al intentar restaurar el elemento",
              type: "error",
              position: "top",
              duration: 5000,
            });
          });
      }
    },
    async editarDialogo(objeto) {
      if (
        await this.$refs.confirmar.open(
          "Editar noticia",
          "Desea editar la noticia con titulo: <br><br>" +
            objeto.titulo +
            "<br><br>Recordá que si la noticia se encuentra publicada, saldra de publicacion"
        )
      ) {
        await this.$store
          .dispatch("getNoticiaPorId", objeto.id)
          .then((result) => {
            if (result.status === 200)
              this.$router.push("/dashboard/nueva?idNoticia=" + objeto.id);
          });
      }
    },
    async borrarDialogo(objeto) {
      if (
        await this.$refs.confirmar.open(
          "Borrar noticia",
          "Desea borrar la noticia de id -> " +
            objeto.id +
            " y nombre :<br>" +
            objeto.titulo
        )
      ) {
        const resultado = this.$store.dispatch("borrarNoticia", objeto.id);
        resultado
          .then((result) => {
            if (result.status === 202) {
              window.location.href =
                window.location.pathname + "?deleteSuccess=1";
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Hubo un error al eliminar la noticia " + error);
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
          message: "Noticia borrada correctamente",
          type: "error",
          position: "top",
          duration: 5000,
        });
      }
    },
    buscarMensajeRestaurado() {
      const { search } = window.location;
      const restoredSuccess = new URLSearchParams(search).get(
        "restoredSuccess"
      );
      if (restoredSuccess === "1") {
        this.$toast.open({
          message: "Noticia restaurada correctamente",
          type: "success",
          position: "top",
          duration: 5000,
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
