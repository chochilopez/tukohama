export default function ({ store, redirect }) {
  // Si el usuario no está autenticado
  if (!store.state.autenticado) {
    return redirect('/login')
  }
}