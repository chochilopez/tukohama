import axios from "axios";
import Helper from "../services/helper"

const API_URL = process.env.baseApiURL;

export const state = () => ({
  arrayDestacadas: false,
  arrayPortada: false,
  cantidadNoticiasBorradores: false,
  cantidadNoticiasPublicadas: false,
  destacablesPortada: false,
  noticia: false,
  noticias: false,
  noticiasBorradas: false,
  noticiasBorradores: false,
  noticiasPorCategoria: false,
  noticiasPorTitulo: false,
  noticiasPublicadas: false,
  noticiasPublicadasPortada: false,
  noticiasUltimoMomento: [],
});
export const mutations = {
  setArrayDestacadas(state, noticias) {
    state.arrayDestacadas = noticias;
  },
  setArrayPortada(state, noticias) {
    state.arrayPortada = noticias;
  },
  setCantidadNoticiasBorradores(state, cantidad) {
    state.cantidadNoticiasBorradores = cantidad;
  },
  setCantidadNoticiasPublicadas(state, cantidad) {
    state.cantidadNoticiasPublicadas = cantidad;
  },
  setDestacablesPortada(state, noticias) {
    state.destacablesPortada = noticias;
  },
  setNoticia(state, noticia) {
    state.noticia = noticia;
  },
  setNoticias(state, noticias) {
    state.noticias = noticias;
  },
  setNoticiasBorradas(state, noticias) {
    state.noticiasBorradas = noticias;
  },
  setNoticiasBorradores(state, noticias) {
    state.noticiasBorradores = noticias;
  },
  setNoticiasPorCategoria(state, noticias) {
    state.noticiasPorCategoria = noticias;
  },
  setNoticiasPorTitulo(state, arreglo) {
    state.noticiasPorTitulo = arreglo;
  },
  setNoticiasPublicadas(state, noticias) {
    state.noticiasPublicadas = noticias;
  },
  setNoticiasPublicadasPortada(state, noticias) {
    state.noticiasPublicadasPortada = noticias;
  },
  setNoticiasUltimoMomento(state, noticias) {
    state.noticiasUltimoMomento = noticias;
  },
};
export const actions = {
  getCategoriasPortada({ rootState }, categoria) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPorCategoriaPublicadasLimitadas/" + categoria + "/" +4, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            resolve(result);
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> findById -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> findById -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getNoticiaPorId({ rootState, commit }, id) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPorId/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticia", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> findById -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> findById -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> findById -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getDestacablesPortada({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPorImportanciaPublicadasPortada/1", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setDestacablesPortada", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> feat-front -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> feat-front -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> feat-front -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getPublicadasPortada({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPublicadasPortada", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            result.data=Helper.ordenarArrayPublicadoDesc(result.data)
            commit("setNoticiasPublicadasPortada", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> feat-news -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> feat-news -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> feat-news -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getCantidadNoticiasBorradores({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/cantidadBorradores", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setCantidadNoticiasBorradores", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> draft-count -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> draft-count -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> draft-count -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getCantidadNoticiasPublicadas({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/cantidadPublicadas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setCantidadNoticiasPublicadas", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> publish-count -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> publish-count -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> publish-count -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getNoticiaPublicadaPorId({ rootState, commit }, id) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPorIdPublicada/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticia", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> news -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> news -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> news -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getNoticiasPorTitulo({ rootState, commit }, titulo) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPorTituloPublicada/" + titulo, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticiasPorTitulo", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> news-by-tittle -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> news-by-tittle -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> news-by-tittle -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getNoticiasPorCategoria({ rootState, commit }, categoria) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPorCategoriaPublicadas/" + categoria, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticiasPorCategoria", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> news-by-cat -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> news-by-cat -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> news-by-cat -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  publicarNoticia({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/publicar/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("news -> publish -> published");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> publish -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> publish -> publish error -> " + error);
          reject(error);
        });
    });
  },
  despublicarNoticia({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/despublicar/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("news -> unpublish -> unpublished");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> unpublish -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> unpublish -> unpublish error -> " + error);
          reject(error);
        });
    });
  },
  borrarNoticia({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "noticia/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("news -> del-news -> deleted");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> del-news -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> del-news -> delete error -> " + error);
          reject(error);
        });
    });
  },
  getNoticiasPublicadas({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarPublicadas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticiasPublicadas", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> published-news -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> published-news -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> published-news -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getNoticiasBorradores({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarBorradores", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticiasBorradores", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> draft-news -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> draft-news -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> draft-news -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  getNoticiasBorradas({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/buscarBorradas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticiasBorradas", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> deleted-news -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> deleted-news -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> deleted-news -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  guardarNoticia({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      let ubicacion = API_URL + "noticia";
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
            console.log("news -> persist -> saved");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> persist -> non-existent id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> persist -> save error -> " + error);
          reject(error);
        });
    });
  },
  getNoticias({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "noticia/todas", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            commit("setNoticias", result.data);
            // eslint-disable-next-line no-console
            console.log("news -> news -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> news -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> news -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  restaurarNoticia({ rootState }, id) {
    return new Promise((resolve, reject) => {
      axios
        .delete(API_URL + "noticia/restaurar/" + id, {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            // eslint-disable-next-line no-console
            console.log("news -> news -> restored");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("news -> news -> non-existent id");
          }
          if (result.status === 206) {
            // eslint-disable-next-line no-console
            console.log("news -> news -> non-deleted id");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("news -> news -> restore error -> " + error);
          reject(error);
        });
    });
  },
};
