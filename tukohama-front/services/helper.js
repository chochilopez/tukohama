class Helper {
  setWithExpire(key, value, user, role, ttl) {
    localStorage.removeItem(key);
    const now = new Date();
    // `item` is an object which contains the original value
    // as well as the time when it's supposed to expire
    const item = {
      ur: user,
      ve: value,
      re: role,
      ee: now.getTime() + ttl,
    };
    localStorage.setItem(key, JSON.stringify(item));
  }

  getWithExpiry(key) {
    const itemStr = localStorage.getItem(key);
    // if the item doesn't exist, return null
    if (!itemStr) {
      return null;
    }
    const item = JSON.parse(itemStr);
    const now = new Date();
    // compare the expiry time of the item with the current time
    if (now.getTime() > item.ee) {
      // If the item is expired, delete the item from storage
      // and return null
      localStorage.removeItem(key);
      return null;
    }
    return item;
  }

  getFormatoFecha(fechaSql) {
    const MESES = [
      "Enero",
      "Febrero",
      "Marzo",
      "Abril",
      "Mayo",
      "Junio",
      "Julio",
      "Agosto",
      "Septiembre",
      "Octubre",
      "Noviembre",
      "Diciembre",
    ];
    const f = new Date(fechaSql);
    return (
      f.getDate() + " de " + MESES[f.getMonth()] + " del " + f.getFullYear()
    );
  }

  buscarPorId(arreglo, id) {
    if (arreglo.length > 0 && id > 0) {
      return arreglo.find(function (item) {
        return item.id === id;
      });
    }
  }

  eliminarPorId(arreglo, id) {
    if (arreglo.length > 0 && id > 0) {
      return arreglo.filter(function (item) {
        return item.id !== id;
      });
    }
  }

  filtrarPublicidades(arreglo) {
    const respuesta = [];
    respuesta[0] = [];
    respuesta[1] = [];
    if (arreglo.length > 0) {
      for (let a = 0; a < arreglo.length; a++) {
        if (arreglo[a].tipo === 1) respuesta[0].push(arreglo[a]);
        else if (arreglo[a].tipo === 2) respuesta[1].push(arreglo[a]);
      }
      if (respuesta[0].length > 0) {
        respuesta[0].sort(function (a, b) {
          if (a.importancia < b.importancia) {
            return -1;
          }
          if (a.importancia > b.importancia) {
            return 1;
          }
          // a must be equal to b
          return 0;
        });
      }
      if (respuesta[1].length > 0) {
        respuesta[1].sort(function (a, b) {
          if (a.importancia < b.importancia) {
            return -1;
          }
          if (a.importancia > b.importancia) {
            return 1;
          }
          // a must be equal to b
          return 0;
        });
      }
    }
    return respuesta;
  }

  ordenarArrayCreadoDesc(arreglo) {
    if (arreglo.length > 0) {
      arreglo.sort(function (a, b) {
        if (a.creado > b.creado) {
          return -1;
        }
        if (a.creado < b.creado) {
          return 1;
        }
        // a must be equal to b
        return 0;
      });
    }
    return arreglo;
  }

  ordenarArrayCreadoAsc(arreglo) {
    if (arreglo.length > 0) {
      arreglo.sort(function (a, b) {
        if (a.creado > b.creado) {
          return 1;
        }
        if (a.creado < b.creado) {
          return -1;
        }
        // a must be equal to b
        return 0;
      });
    }
    return arreglo;
  }

  ordenarArrayPublicadoDesc(arreglo) {
    if (arreglo.length > 0) {
      arreglo.sort(function (a, b) {
        if (a.publicado > b.publicado) {
          return -1;
        }
        if (a.publicado < b.publicado) {
          return 1;
        }
        // a must be equal to b
        return 0;
      });
    }
    return arreglo;
  }

  ordenarArrayPublicadoAsc(arreglo) {
    if (arreglo.length > 0) {
      arreglo.sort(function (a, b) {
        if (a.publicado > b.publicado) {
          return 1;
        }
        if (a.publicado < b.publicado) {
          return -1;
        }
        // a must be equal to b
        return 0;
      });
    }
    return arreglo;
  }

  borrarDeArray(arreglo, item) {
    if (arreglo.length > 0) {
      return arreglo.filter(function (e) {
        return e !== item;
      });
    }
  }
}

export default new Helper();
