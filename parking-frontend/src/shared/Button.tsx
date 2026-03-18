// Button.tsx
type ButtonVariant = 'solid' | 'outline' | 'ghost'

type ButtonProps = {
  children: React.ReactNode
  variant?: ButtonVariant
  onClick?: () => void
  className?: string
}

const variants: Record<ButtonVariant, string> = {
  solid:   'bg-gray-900 text-white hover:bg-gray-700',
  outline: 'border border-gray-200 text-gray-900 hover:bg-gray-50',
  ghost:   'text-gray-500 hover:text-gray-900 hover:bg-gray-50',
}

export default function Button({ children, variant = 'outline', onClick, className = '' }: ButtonProps) {
  return (
    <button
      onClick={onClick}
      className={`text-sm px-3 py-1.5 rounded-md transition-colors ${variants[variant]} ${className}`}
    >
      {children}
    </button>
  )
}