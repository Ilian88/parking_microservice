import Button from '../shared/Button.tsx'
import type { User } from '../store/userSlice.ts'

function Avatar({ name }: {name: string}) {
  return (
    <div className="flex items-center gap-2">
      <div className="w-8 h-8 rounded-full bg-blue-100 flex items-center justify-center text-blue-700 text-xs font-medium">
        {name.slice(0, 2).toUpperCase()}
      </div>
      <span className="text-sm font-medium text-gray-900">{name}</span>
    </div>
  )
}

type NavAuthProps = {
  user: User
  onLogout: ()=> void
}

export default function NavAuth({ user, onLogout }: NavAuthProps) {

  if (user) return (
    <div className="flex items-center gap-3">
      <Avatar name={user.username} />
      <div className="w-px h-5 bg-gray-200" />
      <Button variant="ghost" onClick={onLogout}>Log out</Button>
    </div>
  )

  // return (
  //   <div className="flex items-center gap-2">
  //     <Link to="/login">
  //       <Button variant="outline">Log in</Button>
  //     </Link>
  //     <Link to="/register">
  //       <Button variant="solid">Register</Button>
  //     </Link>
  //   </div>
  // )
}