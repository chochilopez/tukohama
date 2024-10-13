import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  necrologicas: false,
});
export const mutations = {
  setNecrologicas(state, necrologicas) {
    state.necrologicas = necrologicas;
  },
};
export const actions = {
  getNecrologicas({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "necrologica/todas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNecrologicas", result.data);
            // eslint-disable-next-line no-console
            console.log("death-news -> death-news -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("death-news -> death-news -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("death-news -> death-news -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  borrarNecrologica({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "necrologica/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("death-news -> del -> deleted");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("death-news -> del -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("death-news -> del -> delete error -> " + error);
          reject(error);
        });
    });
  },
  guardarNecrologica({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      let ubicacion = API_URL + "necrologica";
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
            console.log("death-news -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("death-news -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("death-news -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
};
