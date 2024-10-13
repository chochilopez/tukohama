<template>
  <v-row>
    <v-navigation-drawer
      permanent
      dark
      color="light-blue darken-3"
      class="align-self-stretch"
      height="1000"
    >
      <v-list dense>
        <v-list-item link href="/dashboard/nueva">
          <v-list-item-icon>
            <v-icon>mdi-new-box</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>NUEVA NOTICIA</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-divider />
        <v-list-item link href="/dashboard">
          <v-list-item-icon>
            <v-icon>mdi-solar-panel</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Panel Principal</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/autores">
          <v-list-item-icon>
            <v-icon>mdi-account-edit</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Autores</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/categorias">
          <v-list-item-icon>
            <v-icon>mdi-arrow-split-vertical</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Categorias</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/widget_clima?widget=1">
          <v-list-item-icon>
            <v-icon>mdi-weather-lightning-rainy</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Clima</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/audios">
          <v-list-item-icon>
            <v-icon>mdi-file-music</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Galeria audios</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/imagenes">
          <v-list-item-icon>
            <v-icon>mdi-file-image</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Galeria imagenes</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/videos">
          <v-list-item-icon>
            <v-icon>mdi-file-video</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Galeria videos</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/necrologicas">
          <v-list-item-icon>
            <v-icon>mdi-script-text-outline</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Necrologicas</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/noticias">
          <v-list-item-icon>
            <v-icon>mdi-newspaper-variant</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>NOTICIAS</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link href="/dashboard/publicidades">
          <v-list-item-icon>
            <v-icon>mdi-account-cash</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Publicidades</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <v-divider></v-divider>
      <!--v-list dense>
        <v-list-item>
          <v-list-item-icon>
            <v-icon></v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Secciones</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-account-hard-hat</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Clasificados</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-account-question</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Encuestas</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-calendar-clock</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Eventos</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-file-music</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Galeria audios</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-file-image</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Galeria imagenes</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item link>
          <v-list-item-icon>
            <v-icon>mdi-video-image</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Videos</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </!--v-list-->
    </v-navigation-drawer>
  </v-row>
</template>
