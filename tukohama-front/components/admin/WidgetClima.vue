<template>
  <div>
    <v-row>
      <v-col cols="12" md="3">
        <v-card>
          <v-card-title> Que tarjeta deseas modificar </v-card-title>
          <v-card-text>
            <v-select
              v-model="widget.id"
              dense
              outlined
              filled
              prepend-icon="mdi-weather-night-partly-cloudy"
              item-text="nombre"
              item-value="id"
              :items="comboTarjeta"
              label="Elegí la noticia destacada"
              @change="verWidget"
            />
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="9">
        <v-card>
          <v-card-title> Widget </v-card-title>
          <a
            class="weatherwidget-io"
            href="https://forecast7.com/es/n32d03n60d31/crespo/"
            :data-label_1="widget.label1"
            :data-label_2="widget.label2"
            :data-font="widget.font"
            :data-icons="widget.icon"
            :data-mode="widget.mode"
            :data-days="widget.day"
            :data-theme="widget.theme"
          />
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" md="4">
        <v-card>
          <v-card-title> Letra </v-card-title>
          <v-card-text>
            <v-select
              v-model="widget.font"
              dense
              outlined
              filled
              prepend-icon="mdi-numeric-1-box"
              item-text="font"
              item-value="font"
              :items="fonts"
              label="Elegí la fuente del widget"
            />
            <v-text-field
              v-model="widget.label1"
              dense
              outlined
              filled
              counter="30"
              clearable
              label="Titulo"
              prepend-icon="mdi-numeric-2-box"
              :rules="[rules.required, rules.min, rules.max]"
            />
            <v-text-field
              v-model="widget.label2"
              dense
              outlined
              filled
              counter="30"
              clearable
              label="Sub-titulo"
              prepend-icon="mdi-numeric-3-box"
              :rules="[rules.required, rules.min, rules.max]"
            />
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="4">
        <v-card>
          <v-card-title> Forma </v-card-title>
          <v-card-text>
            <v-select
              v-model="widget.mode"
              dense
              outlined
              filled
              prepend-icon="mdi-numeric-1-box-outline"
              item-text="mode"
              item-value="mode"
              :items="modes"
              label="Elegí que temperatura queres mostrar"
            />
            <v-select
              v-model="widget.day"
              dense
              outlined
              filled
              prepend-icon="mdi-numeric-2-box-outline"
              item-text="day"
              item-value="day"
              :items="days"
              label="Elegí la cantidad de días del pronóstico"
            />
          </v-card-text>
        </v-card>
      </v-col>
      <v-col cols="12" md="4">
        <v-card>
          <v-card-title> Diseño </v-card-title>
          <v-card-text>
            <v-select
              v-model="widget.theme"
              dense
              outlined
              filled
              prepend-icon="mdi-numeric-1-box-multiple"
              item-text="theme"
              item-value="theme"
              :items="themes"
              label="Elegí el tema del widget"
            />
            <v-select
              v-model="widget.icon"
              dense
              outlined
              filled
              prepend-icon="mdi-numeric-2-box-multiple"
              item-text="icon"
              item-value="icon"
              :items="icons"
              label="Elegí el tipo de ícono"
            />
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card>
          <v-card-actions>
            <v-btn
              color="error"
              class="ma-2 white--text"
              @click="cancelarWidget()"
            >
              Cancelar
              <v-icon right dark> mdi-cancel </v-icon>
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
              color="warning"
              class="ma-2 white--text"
              @click="guardarWidget()"
            >
              Guardar
              <v-icon right dark> mdi-content-save </v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
// eslint-disable-next-line no-unused-expressions
!(function (d, s, id) {
  let js;
    const fjs = d.getElementsByTagName(s)[0];
  if (!d.getElementById(id)) {
    js = d.createElement(s);
    js.id = id;
    js.src = "https://weatherwidget.io/js/widget.min.js";
    fjs.parentNode.insertBefore(js, fjs);
  }
})(document, "script", "weatherwidget-io-js");

export default {
  data() {
    return {
      widget: {
        id: 0,
        mode: "",
        label1: "",
        label2: "",
        font: "",
        icon: "",
        day: "",
        theme: "",
      },
      comboTarjeta: [
        { id: 1, nombre: "Clima cabecera" },
        { id: 2, nombre: "Clima pie" },
        { id: 3, nombre: "Clima tarjeta" },
      ],
      label1: "CrespoVale",
      label2: "El tiempo en Crespo E.R.",
      fonts: [
        { id: 1, font: "Arial" },
        { id: 2, font: "Arial Black" },
        { id: 3, font: "Arial Narrow" },
        { id: 4, font: "Arial Rounded MT Bold" },
        { id: 5, font: "Arimo" },
        { id: 6, font: "Consolas" },
        { id: 7, font: "Courier New" },
        { id: 8, font: "Fira Sans" },
        { id: 9, font: "Georgia" },
        { id: 10, font: "Helvetica" },
        { id: 11, font: "Jura" },
        { id: 12, font: "Monaco" },
        { id: 13, font: "Noto Sans" },
        { id: 14, font: "Noto Serif" },
        { id: 15, font: "Open Sans" },
        { id: 16, font: "Condensed" },
        { id: 17, font: "Palatino" },
        { id: 18, font: "Play" },
        { id: 19, font: "Roboto" },
        { id: 20, font: "Roboto Slab" },
        { id: 21, font: "Tahoma" },
        { id: 22, font: "Times New Roman" },
        { id: 23, font: "Tinos" },
        { id: 24, font: "Trebuchet MS" },
        { id: 25, font: "Ubuntu" },
        { id: 26, font: "Verdana" },
      ],
      icons: [
        { id: 1, icon: "Iconvault" },
        { id: 2, icon: "Cliamcons" },
        { id: 3, icon: "Climacons Animated" },
      ],
      modes: [
        { id: 1, mode: "Both" },
        { id: 2, mode: "Forecast" },
        { id: 3, mode: "Current" },
      ],
      days: [
        { id: 1, day: "3" },
        { id: 1, day: "5" },
        { id: 1, day: "7" },
      ],
      themes: [
        { id: 1, theme: "original" },
        { id: 2, theme: "pure" },
        { id: 3, theme: "orange" },
        { id: 4, theme: "gray" },
        { id: 5, theme: "dark" },
        { id: 6, theme: "desert" },
        { id: 7, theme: "candy" },
        { id: 8, theme: "beige" },
        { id: 9, theme: "blank" },
        { id: 10, theme: "salmon" },
        { id: 11, theme: "retro_sky" },
        { id: 12, theme: "marine" },
        { id: 13, theme: "mountains" },
        { id: 14, theme: "blue_mountains" },
        { id: 15, theme: "pillows" },
        { id: 16, theme: "grass" },
        { id: 17, theme: "fall_leaves" },
        { id: 18, theme: "weather_one" },
      ],
      rules: {
        required: (v) => !!v || "Debés completar el campo",
        min: (v) => v.length >= 3 || "Al menos 3 carácteres",
        max: (v) => v.length <= 30 || "Cómo máximo 30 carácteres",
      },
    };
  },
  mounted() {
    this.obtenerWidgets();
    this.buscarWidget();
  },
  methods: {
    guardarWidget() {
      const respuesta = this.$store.dispatch("guardarWidget", this.widget);
      respuesta.then((result) => {
        if (result.status === 201)
          window.location.href =
            window.location.pathname + "?widget=" + this.widget.id;
      });
    },
    obtenerWidgets() {
      const respuesta = this.$store.dispatch("getWidgets");
      respuesta
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("Widgets cargados correctamente -> dash/Clima");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("No hay Widgets en la BD -> dash/Clima");
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.error("Error al cargar Widgets -> dash/Clima " + error);
        });
    },
    verWidget() {
      window.location.href =
        window.location.pathname + "?widget=" + this.widget.id;
    },
    cancelarWidget() {
      location.reload();
    },
    buscarWidget() {
      const { search } = window.location;
      const widget = new URLSearchParams(search).get("widget");
      switch (widget) {
        case "1":
          this.widget = this.$store.state.clima.widgetHeader;
          break;
        case "2":
          this.widget = this.$store.state.clima.widgetFooter;
          break;
        case "3":
          this.widget = this.$store.state.clima.widgetCard;
          break;
        default:
          break;
      }
    },
  },
};
</script>
