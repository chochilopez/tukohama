export default function authHeader() {
  const datos = JSON.parse(localStorage.getItem("auxtemp"));

  if (datos && datos.ve) {
    return { Authorization: "Bearer " + datos.ve };
  } else {
    return {};
  }
}
