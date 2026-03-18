import { Outlet } from 'react-router-dom'
import Navbar from './navbar/Navbar'
import Footer from './footer/Footer'

export default function MainLayout() {
  return (
    <div>
      <Navbar />
      <main>
        <Outlet />
      </main>
      <Footer />
    </div>
  )
}