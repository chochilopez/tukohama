import axios from "axios";

axios.interceptors.response.use(
  (response) => {
    if (
      response.status === 200 ||
      response.status === 201 ||
      response.status === 202
    ) {
      return Promise.resolve(response);
    } else {
      return Promise.reject(response);
    }
  },
  (error) => {
    if (error.response) {
      // Request made and server responded
      // eslint-disable-next-line no-console
      console.log(error.response.data);
      // eslint-disable-next-line no-console
      console.log(error.response.status);
      // eslint-disable-next-line no-console
      console.log(error.response.headers);
    } else if (error.request) {
      // The request was made but no response was received
      // eslint-disable-next-line no-console
      console.log(error.request);
    } else {
      // Something happened in setting up the request that triggered an Error
      // eslint-disable-next-line no-console
      console.log("Error", error.message);
    }
    return Promise.reject(error);
    // if (error.response.status) {
    //   switch (error.response.status) {
    //     case 400:
    //     // eslint-disable-next-line no-console  
    //     console.log("ERROR 400 a traves de INTERCPTOR -> BAD REQUEST");
    //       break;
    //     case 401:
    //       store.dispatch("actionAsyncIngresarInvitado").then(
    //         (response) => {
    //           return Promise.reject(error.response);
    //         },
    //         (error) => {}
    //       );
    //       // eslint-disable-next-line no-console
    //       console.log("ERROR 401 a traves de INTERCPTOR -> UNAUTHORIZED");
    //       break;
    //     case 403:
    //     // eslint-disable-next-line no-console  
    //     console.log("ERROR 403 a traves de INTERCPTOR -> FORBIDDEN");
    //       break;
    //     case 404:
    //     // eslint-disable-next-line no-console  
    //     console.log("ERROR 403 a traves de INTERCPTOR -> NOT FOUND");
    //       break;
    //     case 405:
    //     // eslint-disable-next-line no-console  
    //     console.log("ERROR 405 a traves de INTERCPTOR -> METHOD NOT ALLOWED");
    //       break;
    //     case 500:
    //     // eslint-disable-next-line no-console  
    //     console.log(
    //         "ERROR 500 a traves de INTERCPTOR -> INTRENAL SERVER ERROR"
    //       );
    //       break;
    //     case 502:
    //       // setTimeout(() => {
    //       //     router.replace({
    //       //         path: "/login",
    //       //         query: {
    //       //             redirect: router.currentRoute.fullPath
    //       //         }
    //       //     });
    //       // }, 1000);
    //       // eslint-disable-next-line no-console
    //       console.log("ERROR 502 a traves de INTERCPTOR -> BAD GATEWAY");
    //       break;
    //   }
    //   return Promise.reject(error.response);
    // }
  }
);
