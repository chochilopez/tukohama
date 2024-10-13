import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  autores: false,
});
export const mutations = {
  setAutores(state, autores) {
    state.autores = autores;
  },
};
export const actions = {
  getAutores({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "autor/todos", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setAutores", result.data);
            // eslint-disable-next-line no-console
            console.log("author -> authors -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("author -> authors -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("author -> authors -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  borrarAutor({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "autor/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("author -> del -> deleted");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("author -> del -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("author -> del -> delete error -> " + error);
          reject(error);
        });
    });
  },
  guardarAutor({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      let ubicacion = API_URL + "autor";
      if (objeto.id > 0) ubicacion = ubicacion + "/" + objeto.id;
      axios
        .put(ubicacion, objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("author -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("author -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("author -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
};
