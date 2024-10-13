import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  categorias: false,
});
export const mutations = {
  setCategorias(state, categorias) {
    state.categorias = categorias;
  },
};
export const actions = {
  getCategorias({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "categoria/todas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setCategorias", result.data);
            // eslint-disable-next-line no-console
            console.log("categoria -> categorias -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("categoria -> categorias -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("categoria -> categorias -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  borrarCategoria({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "categoria/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("category -> del -> deleted");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("category -> del -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("category -> del -> delete error -> " + error);
          reject(error);
        });
    });
  },
  guardarCategoria({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      let ubicacion = API_URL + "categoria";
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
            console.log("category -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("category -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("category -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
};
