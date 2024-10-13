import Vue from "vue";
import Vuex from "vuex";
// Vuex persistent
import createPersistedState from "vuex-persistedstate";
// Secure LS
import SecureLS from "secure-ls";
// Modulos
const ls = new SecureLS({ isCompression: false });

Vue.use(Vuex);

export const store = new Vuex.Store({
  plugins: [
    createPersistedState({
      storage: {
        getItem: (key) => ls.get(key),
        setItem: (key, value) => ls.set(key, value),
        removeItem: (key) => ls.remove(key),
      },
    }),
  ],
})
