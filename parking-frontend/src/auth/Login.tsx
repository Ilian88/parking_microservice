import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { useNavigate } from 'react-router-dom'
import { Link } from 'react-router-dom'
import { login, type User } from '../store/userSlice'

type FormData = {
  username: string
  password: string
}

type FormErrors = Partial<Record<string, string>>

function validate(form: FormData): FormErrors {
  const errors: FormErrors = {}
  if (!form.username.trim()) errors.username = 'Username is required'
  if (!form.password)        errors.password = 'Password is required'
  return errors
}

function Field({
  label, id, type = 'text', value, onChange, error, placeholder,
}: {
  label: string
  id: keyof FormData
  type?: string
  value: string
  onChange: (val: string) => void
  error?: string
  placeholder?: string
}) {
  return (
    <div className="flex flex-col gap-1.5">
      <label htmlFor={id} className="text-[13px] font-medium text-[#1a1a18]/70 tracking-wide">
        {label}
      </label>
      <input
        id={id}
        type={type}
        value={value}
        onChange={e => onChange(e.target.value)}
        placeholder={placeholder}
        className={`w-full px-4 py-3 rounded-sm border text-[15px] font-light bg-white text-[#1a1a18] placeholder:text-[#1a1a18]/25 outline-none transition-colors
          ${error
            ? 'border-red-400 focus:border-red-500'
            : 'border-[#1a1a18]/15 focus:border-[#1a1a18]/50'
          }`}
      />
      {error && <span className="text-[12px] text-red-500 font-light">{error}</span>}
    </div>
  )
}

export default function Login() {
  const [form, setForm]     = useState<FormData>({ username: '', password: '' })
  const [errors, setErrors] = useState<FormErrors>({})
  const [loading, setLoading] = useState(false)
  const dispatch = useDispatch()
  const navigate = useNavigate()

  const set = (key: keyof FormData) => (val: string) =>
    setForm(prev => ({ ...prev, [key]: val }))

  const handleSubmit = async () => {
    const errs = validate(form)
    setErrors(errs)
    if (Object.keys(errs).length > 0) return
    
    setLoading(true)
    try {
      const loginResponse = await fetch("http://localhost:8080/users/login", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json'},
        body: JSON.stringify({username: form.username, password: form.password})
      })

      const data = await loginResponse.json();
      dispatch(login({username: form.username, accessToken: data.accessToken} as User))
      
      
    } catch(e) {
      setErrors({login: `Login Failed: ${e}`})
      return
    } finally {
      setLoading(false)
    }

    setTimeout(() => setLoading(false), 1500)

    navigate('/book')
  }

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Enter') handleSubmit()
  }

  return (
    <div className="min-h-screen bg-[#f7f6f2] flex" onKeyDown={handleKeyDown}>

      {/* Left panel — decorative */}
      <div className="hidden lg:flex flex-col justify-between w-[420px] shrink-0 bg-[#1a1a18] text-[#f7f6f2] p-12">
        <div>
          <span className="font-serif text-xl font-bold tracking-tight">ParkOS</span>
        </div>

        <div>
          <h2 className="font-serif text-[36px] font-black tracking-tight leading-none mb-6">
            Welcome<br />back.
          </h2>
          <p className="text-[14px] font-light text-[#f7f6f2]/50 leading-relaxed max-w-[260px]">
            Log in to manage your reservations, check availability, and access your parking history.
          </p>
        </div>

        <p className="text-[12px] text-[#f7f6f2]/20 font-light">
          © 2026 Central City Parking
        </p>
      </div>

      {/* Right panel — form */}
      <div className="flex-1 flex items-center justify-center px-6 py-16">
        <div className="w-full max-w-[400px]">

          {/* Header */}
          <div className="mb-10">
            <h1 className="font-serif text-[40px] font-black tracking-tight leading-none mb-3">
              Log in
            </h1>
            <p className="text-[15px] font-light text-[#1a1a18]/50">
              No account yet?{' '}
              <Link
                to="/register"
                className="text-[#1a1a18] font-medium underline underline-offset-2 hover:opacity-70 transition-opacity"
              >
                Register for free
              </Link>
            </p>
          </div>

          {/* Fields */}
          <div className="flex flex-col gap-5">
            <Field
              label="Username"
              id="username"
              value={form.username}
              onChange={set('username')}
              error={errors.username}
              placeholder="your_username"
            />

            <div className="flex flex-col gap-1.5">
              <div className="flex items-center justify-between">
                <label htmlFor="password" className="text-[13px] font-medium text-[#1a1a18]/70 tracking-wide">
                  Password
                </label>
                <Link
                  to="/forgot-password"
                  className="text-[12px] text-[#1a1a18]/40 hover:text-[#1a1a18]/70 transition-colors underline underline-offset-2"
                >
                  Forgot password?
                </Link>
              </div>
              <input
                id="password"
                type="password"
                value={form.password}
                onChange={e => set('password')(e.target.value)}
                placeholder="Your password"
                className={`w-full px-4 py-3 rounded-sm border text-[15px] font-light bg-white text-[#1a1a18] placeholder:text-[#1a1a18]/25 outline-none transition-colors
                  ${errors.password
                    ? 'border-red-400 focus:border-red-500'
                    : 'border-[#1a1a18]/15 focus:border-[#1a1a18]/50'
                  }`}
              />
              {errors.password && (
                <span className="text-[12px] text-red-500 font-light">{errors.password}</span>
              )}
            </div>

            {/* Submit */}
            <button
              onClick={handleSubmit}
              disabled={loading}
              className="w-full bg-[#1a1a18] text-[#f7f6f2] py-4 rounded-sm text-[15px] font-medium hover:bg-[#333330] hover:-translate-y-px active:translate-y-0 transition-all mt-2 disabled:opacity-50 disabled:cursor-not-allowed disabled:translate-y-0 flex items-center justify-center gap-2"
            >
              {loading ? (
                <>
                  <svg className="animate-spin" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                    <path d="M21 12a9 9 0 1 1-6.219-8.56" />
                  </svg>
                  Logging in...
                </>
              ) : (
                'Log in'
              )}
            </button>

            <p className="text-[12px] text-[#1a1a18]/35 font-light text-center leading-relaxed">
              By logging in you agree to our{' '}
              <Link to="/terms" className="underline underline-offset-2 hover:text-[#1a1a18]/60">
                Terms of Service
              </Link>.
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}