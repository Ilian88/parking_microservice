import NavBrand from './NavBrand'
import NavLinks from './NavLinks'
import NavAuth from './NavAuth'
import { getLoggedUser, logout } from '../store/userSlice'
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';


const links = [
  { to: '/', label: 'Home' },
  { to: '/pricing', label: 'Pricing' },
  { to: '/book', label: 'Book' },
  { to: '/admin', label: 'Admin'}
]

export default function Navbar() {
  const user = useSelector(getLoggedUser)
  const dispatch = useDispatch()
  const navigate = useNavigate()

  const handleLogout = () => {
     dispatch(logout())
     navigate('/')
  }

  return (
    <nav className="bg-white border-b border-gray-200 px-6 h-14 flex items-center justify-between">
      <div className="flex items-center gap-8">
        <NavBrand />
        <NavLinks links={links} />
      </div>
      <NavAuth user={user} onLogout={handleLogout} />
    </nav>
  )
}



