import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  arrayBaners: false,
  arrayCards: false,
  publicidades: false,
});
export const mutations = {
  setArrayBaners(state, publicidades) {
    state.arrayBaners = publicidades;
  },
  setArrayCards(state, publicidades) {
    state.arrayCards = publicidades;
  },
  setPublicidades(state, publicidades) {
    state.publicidades = publicidades;
  },
};
export const actions = {
  getPublicidades({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "publicidad/todas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setPublicidades", result.data);
            // eslint-disable-next-line no-console
            console.log("ads -> ads -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("ads -> ads -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("ads -> ads -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  borrarPublicidad({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "publicidad/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("ads -> del-ads -> deleted");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("ads -> del-ads -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("ads -> del-ads -> delete error -> " + error);
          reject(error);
        });
    });
  },
  guardarPublicidad({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      axios
        .put(API_URL + "publicidad/guardarPublicidad", objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("ads -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("ads -> persist -> create error");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("ads -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
  modificarPublicidad({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      let ubicacion = API_URL + "publicidad";
      if (objeto.id != null) ubicacion = ubicacion + "/" + objeto.id;
      axios
        .put(ubicacion, objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("ads -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("ads -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("ads -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
};
