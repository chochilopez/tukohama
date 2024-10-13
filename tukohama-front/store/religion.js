import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  religiones: false,
});
export const mutations = {
  setReligiones(state, religiones) {
    state.religiones = religiones;
  },
};
export const actions = {
  getReligiones({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "religion/todas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setReligiones", result.data);
            // eslint-disable-next-line no-console
            console.log("religion -> religions -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("religion -> religions -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("religion -> religions -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  borrarReligion({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "religion/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("religion -> del -> deleted");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("religion -> del -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("religion -> del -> delete error -> " + error);
          reject(error);
        });
    });
  },
  guardarReligion({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      let ubicacion = API_URL + "religion";
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
            console.log("religion -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("religion -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("religion -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
};
