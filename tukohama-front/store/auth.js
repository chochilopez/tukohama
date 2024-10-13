import axios from "axios";

const API_URL = process.env.baseApiURL

export const state = () => ({
  autenticado: false,
  autoridad: false,
  token: false,
  username: false,
});
export const mutations = {
  setAutenticado(state, autenticado) {
    state.autenticado = autenticado;
  },
  setAutoridad(state, autoridad) {
    state.autoridad = autoridad;
  },
  setToken(state, token) {
    state.token = token;
  },
  setUsername(state, username) {
    state.username = username;
  },
};
export const actions = {
  login({ commit }, usuario) {
    return new Promise((resolve, reject) => {
      axios
        .post(API_URL + "auth/authenticate", {
          username: usuario.username,
          password: usuario.password,
          recordar: usuario.recordar,
        })
        .then((response) => {
          if (response.status === 200) {
            // eslint-disable-next-line no-console
            console.log("auth -> login -> fetched");
            let autoridad = 1;
            if (response.data.roles.includes("editor")) autoridad = 2;
            if (response.data.roles.includes("admin")) autoridad = 3;
            commit("setAutenticado", true);
            commit("setAutoridad", autoridad);
            commit("setToken", response.data.token);
            commit("setUsername", response.data.username);
            resolve(response);
          }
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("auth -> login -> fetch error");
          reject(error);
        });
    });
  },
  cerrarSesion({ dispatch }) {
    const usuario = { username: "invitado@invitado", password: "invitado" };
    // eslint-disable-next-line no-console
    console.log("auth -> logout -> logged-out");
    dispatch("login", usuario);
  },
};
