import { Route, Routes } from 'react-router-dom'
import MainLayout from './MainLayout'
import Home from './home/Home'
import Login from './auth/Login'
import Register from './auth/Register'
import Book from './home/Book'

function App() {
  return (
    <Routes>
      <Route element={<MainLayout />}>
        <Route path="/"          element={<Home />}     />
        <Route path="/login"     element={<Login />}    />
        <Route path="/register"  element={<Register />} />
        <Route path="/book"      element={<Book />} />
      </Route>
    </Routes>
  )
}

export default App