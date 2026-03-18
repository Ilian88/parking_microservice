import { Link } from 'react-router-dom'

const AppName = "Parking lot"

export default function NavBrand({ name = AppName }) {
  return (
    <Link to="/" className="text-base font-medium text-gray-900 hover:opacity-80 transition-opacity">
      {name}
    </Link>
  )
}