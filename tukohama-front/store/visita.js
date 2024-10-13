import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  ip: false,
  cantidadVisitas: false,
});
export const mutations = {
  setIp(state, ip) {
    state.ip = ip;
  },
  setCantidadVisitas(state, cantidadVisitas) {
    state.cantidadVisitas = cantidadVisitas;
  },
};
export const actions = {
  getCantidadVisitas({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "visita/cantidad", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setCantidadVisitas", result.data);
            // eslint-disable-next-line no-console
            console.log("visit -> count -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("visit -> count -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("visit -> count -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getIp({ commit, dispatch }) {
    return new Promise((resolve, reject) => {
      axios
        .get("https://api.ipify.org/?format=json")
        .then((result) => {
          commit("setIp", result.data.ip);
          dispatch("guardarVisita", result.data.ip);
          resolve(result);
        })
        .catch((error) => {
          reject(error);
        });
    });
  },
  guardarVisita({ rootState }, ip) {
    return new Promise((resolve, reject) => {
      const objeto = { ip };
      axios
        .put(API_URL + "visita", objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            // eslint-disable-next-line no-console
            console.log("visit -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("visit -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("visit -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
};
