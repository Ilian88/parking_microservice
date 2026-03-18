import NavBrand from './NavBrand'
import NavLinks from './NavLinks'
import NavAuth from './NavAuth'
import { useState } from 'react'

const links = [
  { to: '/', label: 'Home' },
  { to: '/pricing', label: 'Pricing' },
]

export default function Navbar() {
  const [user, setUser] = useState<{ name: string } | null>(null)

  const onLogout = ()=> setUser(null)

  return (
    <nav className="bg-white border-b border-gray-200 px-6 h-14 flex items-center justify-between">
      <div className="flex items-center gap-8">
        <NavBrand />
        <NavLinks links={links} />
      </div>
      <NavAuth user={user} onLogout={onLogout} />
    </nav>
  )
}