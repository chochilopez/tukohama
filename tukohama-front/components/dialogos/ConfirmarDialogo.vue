<template>
  <v-dialog
    v-model="dialog"
    :max-width="options.width"
    :style="{ zIndex: options.zIndex }"
    @keydown.esc="cancel"
  >
    <v-card>
      <v-toolbar dark color="blue darken-2">
        <v-toolbar-title
          class="text-h5 font-weight-bold white--text"
          color="blue darken-1"
        >
          {{ title }}
        </v-toolbar-title>
      </v-toolbar>
      <v-card-text
        v-show="!!message"
        class="pa-4 black--text text-subtitle-2"
        v-html="message"
      />
      <v-card-actions class="pt-3">
        <v-btn
          v-if="!options.noconfirm"
          color="error"
          class="ma-2 white--text"
          @click.native="cancel"
        >
          Cancelar
          <v-icon right dark> mdi-cancel </v-icon>
        </v-btn>
        <v-spacer />
        <v-btn color="warning" class="ma-2 white--text" @click.native="agree">
          Aceptar
          <v-icon right dark> mdi-content-save </v-icon>
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "ConfirmDlg",
  data() {
    return {
      dialog: false,
      resolve: null,
      reject: null,
      message: null,
      title: null,
      options: {
        color: "grey lighten-3",
        width: 400,
        zIndex: 200,
        noconfirm: false,
      },
    };
  },

  methods: {
    open(title, message, options) {
      this.dialog = true;
      this.title = title;
      this.message = message;
      this.options = Object.assign(this.options, options);
      return new Promise((resolve, reject) => {
        this.resolve = resolve;
        this.reject = reject;
      });
    },
    agree() {
      this.resolve(true);
      this.dialog = false;
    },
    cancel() {
      this.resolve(false);
      this.dialog = false;
    },
  },
};
</script>
