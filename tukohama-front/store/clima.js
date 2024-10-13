import axios from "axios";

const API_URL = process.env.baseApiURL;

export const state = () => ({
  widgetHeader: false,
  widgetFooter: false,
  widgetCard: false,
});
export const mutations = {
  setWidgets(state, widgets) {
    state.widgetHeader = widgets[0];
    state.widgetFooter = widgets[1];
    state.widgetCard = widgets[2];
  },
};
export const actions = {
  getWidgets({ rootState, commit }) {
    return new Promise((resolve, reject) => {
      axios
        .get(API_URL + "widget_clima/todos", {
          headers: {
            Authorization: "Bearer " + rootState.auth.token,
          },
        })
        .then((result) => {
          if (result.status === 200) {
            if (result.data.length > 2) commit("setWidgets", result.data);
            // eslint-disable-next-line no-console
            console.log("weather -> widgets -> fetched");
          }
          if (result.status === 204) {
            // eslint-disable-next-line no-console
            console.log("weather -> widgets -> empty");
          }
          resolve(result);
        })
        .catch((error) => {
          // eslint-disable-next-line no-console
          console.log("weather -> widgets -> fetch error -> " + error);
          reject(error);
        });
    });
  },
  guardarWidget({ rootState }, objeto) {
    return new Promise((resolve, reject) => {
      if (objeto.id > 0 && objeto.id < 4) {
        axios
          .put(API_URL + "widget_clima/" + objeto.id, objeto, {
            headers: {
              Authorization: "Bearer " + rootState.auth.token,
            },
          })
          .then((result) => {
            if (result.status === 201) {
              // eslint-disable-next-line no-console
              console.log("weather -> persist -> saved");
            }
            if (result.status === 204) {
              // eslint-disable-next-line no-console
              console.log("weather -> persist -> non-existent id");
            }
            resolve(result);
          })
          .catch((error) => {
            // eslint-disable-next-line no-console
            console.log("weather -> persist -> save error -> " + error);
            reject(error);
          });
      }
    });
  },
};
