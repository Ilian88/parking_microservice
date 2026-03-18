import { Link, useLocation } from 'react-router-dom'
import type { NavLink, NavLinkProps } from './types'

export default function NavLinks({ links } : NavLinkProps) {
  const { pathname } = useLocation()

  return (
    <div className="flex gap-5">
      {links.map(({to, label}: NavLink) => (
        <Link
          key={to}
          to={to}
          className={`text-sm transition-colors ${
            pathname === to
              ? 'text-gray-900 font-medium'
              : 'text-gray-500 hover:text-gray-900'
          }`}
        >
          {label}
        </Link>
      ))}
    </div>
  )
}