import { useState } from 'react'
import { Link } from 'react-router-dom'

type AccountType = 'person' | 'company'

type FormData = {
  accountType: AccountType
  username: string
  email: string
  phone: string
  password: string
  confirmPassword: string
  companyName: string
}

type FormErrors = Partial<Record<keyof FormData, string>>

const initialForm: FormData = {
  accountType: 'person',
  username: '',
  email: '',
  phone: '',
  password: '',
  confirmPassword: '',
  companyName: '',
}

function validate(form: FormData): FormErrors {
  const errors: FormErrors = {}
  if (!form.username.trim())               errors.username        = 'Username is required'
  if (!form.email.includes('@'))           errors.email           = 'Enter a valid email'
  if (form.phone.length < 7)              errors.phone           = 'Enter a valid phone number'
  if (form.password.length < 8)           errors.password        = 'Password must be at least 8 characters'
  if (form.password !== form.confirmPassword) errors.confirmPassword = 'Passwords do not match'
  if (form.accountType === 'company' && !form.companyName.trim())
                                           errors.companyName     = 'Company name is required'
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

export default function Register() {
  const [form, setForm]       = useState<FormData>(initialForm)
  const [errors, setErrors]   = useState<FormErrors>({})
  const [submitted, setSubmitted] = useState(false)

  const set = (key: keyof FormData) => (val: string) =>
    setForm(prev => ({ ...prev, [key]: val }))

  const handleSubmit = () => {
    const errs = validate(form)
    setErrors(errs)
    if (Object.keys(errs).length === 0) setSubmitted(true)
  }

  if (submitted) {
    return (
      <div className="min-h-screen bg-[#f7f6f2] flex items-center justify-center px-6">
        <div className="text-center max-w-sm">
          <div className="w-16 h-16 rounded-full bg-[#1a1a18] flex items-center justify-center mx-auto mb-6">
            <svg width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="#f7f6f2" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
              <polyline points="20 6 9 17 4 12" />
            </svg>
          </div>
          <h2 className="font-serif text-3xl font-bold tracking-tight mb-3">You're in.</h2>
          <p className="text-[#1a1a18]/50 font-light text-base leading-relaxed mb-8">
            Account created successfully. You can now log in and start reserving your spot.
          </p>
          <Link
            to="/login"
            className="inline-block bg-[#1a1a18] text-[#f7f6f2] px-8 py-3.5 rounded-sm text-sm font-medium hover:bg-[#333330] transition-colors"
          >
            Go to login
          </Link>
        </div>
      </div>
    )
  }

  return (
    <div className="min-h-screen bg-[#f7f6f2] flex">

      {/* Left panel — decorative */}
      <div className="hidden lg:flex flex-col justify-between w-[420px] shrink-0 bg-[#1a1a18] text-[#f7f6f2] p-12">
        <div>
          <span className="font-serif text-xl font-bold tracking-tight">ParkOS</span>
        </div>
        <div>
          <p className="text-[11px] tracking-[0.15em] uppercase text-[#f7f6f2]/35 font-medium mb-4">
            Why register?
          </p>
          {[
            'Reserve spots in advance',
            'Real-time availability updates',
            'Contactless QR entry',
            'Full booking history & invoices',
          ].map(item => (
            <div key={item} className="flex items-center gap-3 mb-4">
              <div className="w-1 h-1 rounded-full bg-[#f7f6f2]/40 shrink-0" />
              <span className="text-[14px] font-light text-[#f7f6f2]/60">{item}</span>
            </div>
          ))}
        </div>
        <p className="text-[12px] text-[#f7f6f2]/20 font-light">
          © 2026 Central City Parking
        </p>
      </div>

      {/* Right panel — form */}
      <div className="flex-1 flex items-start justify-center px-6 py-16 overflow-y-auto">
        <div className="w-full max-w-[480px]">

          {/* Header */}
          <div className="mb-10">
            <h1 className="font-serif text-[40px] font-black tracking-tight leading-none mb-3">
              Create account
            </h1>
            <p className="text-[15px] font-light text-[#1a1a18]/50">
              Already have one?{' '}
              <Link to="/login" className="text-[#1a1a18] font-medium underline underline-offset-2 hover:opacity-70 transition-opacity">
                Log in
              </Link>
            </p>
          </div>

          {/* Account type toggle */}
          <div className="mb-8">
            <p className="text-[13px] font-medium text-[#1a1a18]/70 tracking-wide mb-2.5">
              Account type
            </p>
            <div className="flex rounded-sm border border-[#1a1a18]/15 overflow-hidden bg-white">
              {(['person', 'company'] as AccountType[]).map(type => (
                <button
                  key={type}
                  onClick={() => setForm(prev => ({ ...prev, accountType: type }))}
                  className={`flex-1 py-3 text-[14px] font-medium transition-colors capitalize
                    ${form.accountType === type
                      ? 'bg-[#1a1a18] text-[#f7f6f2]'
                      : 'bg-white text-[#1a1a18]/50 hover:text-[#1a1a18]'
                    }`}
                >
                  {type === 'person' ? 'Individual' : 'Company'}
                </button>
              ))}
            </div>
          </div>

          {/* Form fields */}
          <div className="flex flex-col gap-5">

            {form.accountType === 'company' && (
              <Field
                label="Company name"
                id="companyName"
                value={form.companyName}
                onChange={set('companyName')}
                error={errors.companyName}
                placeholder="Acme Ltd."
              />
            )}

            <Field
              label="Username"
              id="username"
              value={form.username}
              onChange={set('username')}
              error={errors.username}
              placeholder="your_username"
            />

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Email address"
                id="email"
                type="email"
                value={form.email}
                onChange={set('email')}
                error={errors.email}
                placeholder="you@example.com"
              />
              <Field
                label="Phone number"
                id="phone"
                type="tel"
                value={form.phone}
                onChange={set('phone')}
                error={errors.phone}
                placeholder="+359 88 123 4567"
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Password"
                id="password"
                type="password"
                value={form.password}
                onChange={set('password')}
                error={errors.password}
                placeholder="Min. 8 characters"
              />
              <Field
                label="Confirm password"
                id="confirmPassword"
                type="password"
                value={form.confirmPassword}
                onChange={set('confirmPassword')}
                error={errors.confirmPassword}
                placeholder="Repeat password"
              />
            </div>

            {/* Password strength */}
            {form.password.length > 0 && (
              <div className="flex gap-1.5 items-center">
                {[1, 2, 3, 4].map(i => (
                  <div
                    key={i}
                    className={`h-1 flex-1 rounded-full transition-colors ${
                      form.password.length >= i * 3
                        ? form.password.length >= 10 ? 'bg-green-500' : 'bg-amber-400'
                        : 'bg-[#1a1a18]/10'
                    }`}
                  />
                ))}
                <span className="text-[12px] text-[#1a1a18]/40 font-light ml-1">
                  {form.password.length < 6 ? 'Weak' : form.password.length < 10 ? 'Fair' : 'Strong'}
                </span>
              </div>
            )}

            {/* Submit */}
            <button
              onClick={handleSubmit}
              className="w-full bg-[#1a1a18] text-[#f7f6f2] py-4 rounded-sm text-[15px] font-medium hover:bg-[#333330] hover:-translate-y-px active:translate-y-0 transition-all mt-2"
            >
              Create account
            </button>

            <p className="text-[12px] text-[#1a1a18]/35 font-light text-center leading-relaxed">
              By registering you agree to our{' '}
              <Link to="/terms" className="underline underline-offset-2 hover:text-[#1a1a18]/60">Terms of Service</Link>
              {' '}and{' '}
              <Link to="/privacy" className="underline underline-offset-2 hover:text-[#1a1a18]/60">Privacy Policy</Link>.
            </p>
          </div>
        </div>
      </div>
    </div>
  )
}