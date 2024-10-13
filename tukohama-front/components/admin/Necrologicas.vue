<template>
  <div>
    <v-card>
      <v-card-title>Necrologicas</v-card-title>
      <v-row class="pa-3">
        <v-col>
          <v-btn color="red" @click="nuevoDialogo()">Nueva necrologica</v-btn>
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
        :items="necrologicas"
        sort-by="nombre"
        class="elevation-1"
      >
        <template v-slot:item.religiones="{ item }">
          <v-icon>{{ item.religion.icono }}</v-icon> -
          {{ item.religion.nombre }}
        </template>
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
          <v-chip class="ma-2" color="error" label @click="borrarDialogo(item.id, item.nombre)">
            <v-icon left> mdi-delete </v-icon>
            Borrar
          </v-chip>
        </template>
      </v-data-table>
    </v-card>
    <NuevaNecrologicaDialogo ref="nueva" />
    <ModificarNecrologicaDialogo ref="modificar" />
    <ConfirmarDialogo ref="confirmar" />
  </div>
</template>

<script>
import ConfirmarDialogo from "../dialogos/ConfirmarDialogo";
import NuevaNecrologicaDialogo from "../dialogos/NuevaNecrologicaDialogo";
import ModificarNecrologicaDialogo from "../dialogos/ModificarNecrologicaDialogo";
import Helper from "~/services/helper";

export default {
  components: {
    ConfirmarDialogo,
    ModificarNecrologicaDialogo,
    NuevaNecrologicaDialogo,
  },
  data() {
    return {
      necrologicas: [],
      necrologica: {
        id: 0,
        nombre: "",
        cuerpo: "",
        religion: null,
      },
      cantidad: 0,
      buscar: "",
      headers: [
        {
          text: "Nombre",
          align: "start",
          sortable: true,
          filterable: true,
          value: "nombre",
        },
        {
          text: "Creado",
          align: "start",
          sorteable: true,
          filterable: true,
          value: "creado",
        },
        {
          text: "Simbolo - Religion",
          align: "start",
          sorteable: true,
          filterable: true,
          value: "religiones",
        },
        {
          text: "Acciones",
          align: "start",
          value: "acciones",
          filterable: false,
          sortable: false,
        },
      ],
    };
  },
  mounted() {
    this.obtenerNecrologicas();
    this.obtenerReligiones();
    this.buscarMensajeBorrado();
    this.buscarMensajeCreado();
    this.buscarMensajeEditado();
  },
  methods: {
    async guardarNecrologica() {
      try {
        const objeto = {
          id: this.necrologica.id,
          nombre: this.necrologica.nombre,
          cuerpo: this.necrologica.cuerpo,
          idReligion: this.necrologica.religion.id,
        };
        await this.$store.dispatch("guardarNecrologica", objeto)
          .then((result) => {
            if (result.status === 201) {
              window.location.href =
                window.location.pathname + "?modifiedSuccess=1";
            }
          });
      } catch (error) {
        // eslint-disable-next-line no-console
        console.error("Hubo un error al crear/modificar el elemento " + error);
        this.$toast.open({
          message: "Hubo un error al intentar crear/modificar el elemento",
          type: "warning",
          position: "top",
          duration: 5000,
        });
      }
    },
    async obtenerNecrologicas() {
      try {
        await this.$store.dispatch("getNecrologicas").then((result) => {
          if (result.status === 204) {
            this.$toast.open({
              message: "La tabla Necrologicas esta vacia",
              type: "warning",
              position: "top",
              duration: 5000,
            });
          }
        });
      } catch (error) {
        this.$toast.open({
          message: "Hubo un error al intentar obtener las Necrologicas",
          type: "error",
          position: "top",
          duration: 5000,
        });
      }
      this.necrologicas = Helper.ordenarArrayCreadoDesc(this.$store.state.necrologica.necrologicas);
    },
    async obtenerReligiones() {
      if (this.$store.state.religion.religiones === false) {
        try {
          await this.$store.dispatch("getReligiones").then((result) => {
            if (result.status === 204) {
              this.$toast.open({
                message: "La tabla Religion esta vacia",
                type: "warning",
                position: "top",
                duration: 5000,
              });
            }
          });
        } catch (error) {
          this.$toast.open({
            message: "Hubo un error al intentar obtener las Religiones",
            type: "error",
            position: "top",
            duration: 5000,
          });
        }
      // eslint-disable-next-line no-console
      } else console.log("religion -> religions -> reloaded");
    },
    async nuevoDialogo() {
      try {
        const respuesta = await this.$refs.nueva.open();
        const envio = {
          nombre: respuesta.nombre,
          cuerpo: respuesta.cuerpo,
          idReligion: respuesta.religion.id,
        };
        this.$store.dispatch("guardarNecrologica", envio)
          .then((result) => {
            if (result.status === 201) {
              window.location.href =
                window.location.pathname + "?modifiedSuccess=1";
            }
          })
          .catch(() => {});
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log(error);
      }
    },
    async editarDialogo(objeto) {
      try {
        const respuesta = await this.$refs.modificar.open(objeto);
        const envio = {
          id: respuesta.id,
          nombre: respuesta.nombre,
          cuerpo: respuesta.cuerpo,
          idReligion: respuesta.idReligion,
        };
        this.$store.dispatch("guardarNecrologica", envio)
          .then((result) => {
            if (result.status === 201) {
              window.location.href =
                window.location.pathname + "?modifiedSuccess=1";
            }
          })
          .catch(() => {});
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log(error);
      }
    },
    async borrarDialogo(id, nombre) {
      if (
        await this.$refs.confirmar.open(
          "Borrar necrologica",
          "Desea borrar la necrologica: " + nombre
        )
      ) {
        const resultado = this.$store.dispatch("borrarNecrologica", id);
        resultado
          .then((result) => {
            if (result.status === 202) {
              window.location.href =
                window.location.pathname + "?deleteSuccess=1";
            }
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.error("Hubo un error al eliminar la necrologica " + error);
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
          message: "Necrologica borrada correctamente",
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
          message: "Necrologica creada correctamente",
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
          message: "Necrologica modificada correctamente",
          type: "info",
          position: "top",
          duration: 5000,
        });
      }
    },
  },
};
</script>
