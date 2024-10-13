import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  audios: null,
  imagenes: null,
  videos: null,
});
export const mutations = {
  setAudios(state, audios) {
    state.audios = audios;
  },
  setImagenes(state, imagenes) {
    state.imagenes = imagenes;
  },
  setVideos(state, videos) {
    state.videos = videos;
  },
};
export const actions = {
  modificarMultimedia({ rootState }, archivo) {
    return new Promise((resolve, reject) => {
      axios
        .put(API_URL + "multimedia/" + archivo.id, archivo, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
  borrarMultimedia({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "multimedia/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> del -> deleted");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> del -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> del -> delete error -> " + error);
          reject(error);
        });
    });
  },
  guardarAudio({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      axios
        .put(API_URL + "multimedia/guardarAudio", objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist-audio -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist-audio -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> persist-audio -> save error -> " + error);
          reject(error);
        });
    });
  },
  guardarImagen({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      axios
        .put(API_URL + "multimedia/guardarImagen", objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist-image -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist-image -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> persist-image -> save error -> " + error);
          reject(error);
        });
    });
  },
  guardarVideo({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      axios
        .put(API_URL + "multimedia/guardarVideo", objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist-video -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> persist-video -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> persist-video -> save error -> " + error);
          reject(error);
        });
    });
  },
  getAudios({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "multimedia/buscarAudios", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setAudios", result.data);
            // eslint-disable-next-line no-console
            console.log("multimedia -> audios -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> audios -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> audios -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getImagenes({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "multimedia/buscarImagenes", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setImagenes", result.data);
            // eslint-disable-next-line no-console
            console.log("multimedia -> images -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> images -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> images -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getVideos({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "multimedia/buscarVideos", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setVideos", result.data);
            // eslint-disable-next-line no-console
            console.log("multimedia -> videos -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("multimedia -> videos -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("multimedia -> videos -> fetch error -> " + error);
          reject(error);
        });
    });
  },
};
