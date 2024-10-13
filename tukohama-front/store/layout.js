import axios from "axios";

const API_URL = process.env.baseApiURL

export const state = () => ({
  layout: false,
  color: "#1eafca",
});
export const mutations = {
  setColorNav(state, color) {
    state.color = color;
  },
  setLayout(state, layout) {
    state.layout = layout;
  },
  setNoticiasPortada(state, cantidad) {
    state.layout.noticiasPortada = cantidad;
  },
  setCantidadUltimas(state, cantidad) {
    state.layout.cantidadUltimas = cantidad;
  },
  setDestacada1(state, noticia) {
    state.layout.destacada1 = noticia;
  },
  setDestacada2(state, noticia) {
    state.layout.destacada2 = noticia;
  },
  setDestacada3(state, noticia) {
    state.layout.destacada3 = noticia;
  },
  setDestacada4(state, noticia) {
    state.layout.destacada4 = noticia;
  },
  setCategoria1(state, noticias) {
    state.layout.categoria1 = noticias;
  },
  setCategoria2(state, noticias) {
    state.layout.categoria2 = noticias;
  },
  setCategoria3(state, noticias) {
    state.layout.categoria3 = noticias;
  },
  setCategoria4(state, noticias) {
    state.layout.categoria4 = noticias;
  },
};
export const actions = {
  getLayout({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "layout/buscarPorId/1", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setLayout", result.data);
            // eslint-disable-next-line no-console
            console.log("layout -> layout -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("layout -> layout -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("layout -> layout -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  guardarLayout({ rootState, commit }, objeto) {
    return new Promise((resolve, reject) => {
      axios
        .put(API_URL + "layout/1", objeto, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 201) {
            commit("setLayout", result.data);
            // eslint-disable-next-line no-console
            console.log("layout -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("layout -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("layout -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
};
