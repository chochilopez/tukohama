export default function ({ store, redirect }) {
  // Si el usuario no está autenticado
  if (store.state.autoridad !== 3) {
    return redirect('/login')
  }
}