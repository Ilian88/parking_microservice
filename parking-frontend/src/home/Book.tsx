import { useState } from 'react'
import { Link } from 'react-router-dom'
import type { CarErrors, TruckErrors, VehicleType, CarFormType, TruckFormType } from './book/types'
import CarForm from './book/CarForm'
import TruckForm from './book/TruckForm'

const initialCar: CarFormType = {
  licensePlate: '',
  euroCategory: '',
  make: '',
  model: '',
}

const initialTruck: TruckFormType = {
  licensePlate: '',
  euroCategory: '',
  truckMake: '',
  truckModel: '',
  trailerMake: '',
  trailerModel: '',
  weight: '',
  length: '',
}

function validateCar(f: CarFormType): CarErrors {
  const e: CarErrors = {}
  if (!f.licensePlate.trim()) e.licensePlate = 'Required'
  if (!f.euroCategory)        e.euroCategory = 'Required'
  return e
}

function validateTruck(f: TruckFormType): TruckErrors {
  const e: TruckErrors = {}
  if (!f.licensePlate.trim()) e.licensePlate = 'Required'
  if (!f.euroCategory)        e.euroCategory = 'Required'
  if (!f.weight.trim())       e.weight       = 'Required'
  if (!f.length.trim())       e.length       = 'Required'
  return e
}

export function Field({
  label, id, type = 'text', value, onChange, error, placeholder, required = false,
}: {
  label: string
  id: string
  type?: string
  value: string
  onChange: (val: string) => void
  error?: string
  placeholder?: string
  required?: boolean
}) {
  return (
    <div className="flex flex-col gap-1.5">
      <label htmlFor={id} className="text-[13px] font-medium text-[#1a1a18]/70 tracking-wide flex gap-1">
        {label}
        {required && <span className="text-red-400">*</span>}
      </label>
      <input
        id={id}
        type={type}
        value={value}
        onChange={e => onChange(e.target.value)}
        placeholder={placeholder}
        className={`w-full px-4 py-3 rounded-sm border text-[15px] font-light bg-white text-[#1a1a18] placeholder:text-[#1a1a18]/25 outline-none transition-colors
          ${error ? 'border-red-400 focus:border-red-500' : 'border-[#1a1a18]/15 focus:border-[#1a1a18]/50'}`}
      />
      {error && <span className="text-[12px] text-red-500 font-light">{error}</span>}
    </div>
  )
}

export function SelectField({
  label, id, value, onChange, error, options, placeholder, required = false,
}: {
  label: string
  id: string
  value: string
  onChange: (val: string) => void
  error?: string
  options: string[]
  placeholder?: string
  required?: boolean
}) {
  return (
    <div className="flex flex-col gap-1.5">
      <label htmlFor={id} className="text-[13px] font-medium text-[#1a1a18]/70 tracking-wide flex gap-1">
        {label}
        {required && <span className="text-red-400">*</span>}
      </label>
      <select
        id={id}
        value={value}
        onChange={e => onChange(e.target.value)}
        className={`w-full px-4 py-3 rounded-sm border text-[15px] font-light bg-white text-[#1a1a18] outline-none transition-colors appearance-none cursor-pointer
          ${!value ? 'text-[#1a1a18]/30' : ''}
          ${error ? 'border-red-400 focus:border-red-500' : 'border-[#1a1a18]/15 focus:border-[#1a1a18]/50'}`}
      >
        <option value="" disabled>{placeholder ?? 'Select...'}</option>
        {options.map(o => <option key={o} value={o}>{o}</option>)}
      </select>
      {error && <span className="text-[12px] text-red-500 font-light">{error}</span>}
    </div>
  )
}

export function SectionLabel({ children }: { children: string }) {
  return (
    <p className="text-[11px] tracking-[0.15em] uppercase text-[#1a1a18]/35 font-medium mt-2">
      {children}
    </p>
  )
}

export default function Book() {
  const [vehicleType, setVehicleType] = useState<VehicleType>('car')
  const [car,   setCar]   = useState<CarFormType>(initialCar)
  const [truck, setTruck] = useState<TruckFormType>(initialTruck)
  const [carErrors,   setCarErrors]   = useState<CarErrors>({})
  const [truckErrors, setTruckErrors] = useState<TruckErrors>({})
  const [submitted, setSubmitted] = useState(false)

  const setCar_   = (key: keyof CarFormType)   => (val: string) => setCar(p   => ({ ...p, [key]: val }))
  const setTruck_ = (key: keyof TruckFormType) => (val: string) => setTruck(p => ({ ...p, [key]: val }))

  const handleSubmit = () => {
    if (vehicleType === 'car') {
      const errs = validateCar(car)
      setCarErrors(errs)
      if (Object.keys(errs).length === 0) setSubmitted(true)
    } else {
      const errs = validateTruck(truck)
      setTruckErrors(errs)
      if (Object.keys(errs).length === 0) setSubmitted(true)
    }
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
          <h2 className="font-serif text-3xl font-bold tracking-tight mb-3">Booking confirmed.</h2>
          <p className="text-[#1a1a18]/50 font-light text-base leading-relaxed mb-8">
            Your {vehicleType} has been registered.{' '}
            {vehicleType === 'car'
              ? `${car.make} ${car.model} — ${car.licensePlate}`
              : `${truck.truckMake} ${truck.truckModel} — ${truck.licensePlate}`
            }
          </p>
          <Link
            to="/dashboard"
            className="inline-block bg-[#1a1a18] text-[#f7f6f2] px-8 py-3.5 rounded-sm text-sm font-medium hover:bg-[#333330] transition-colors"
          >
            Go to dashboard
          </Link>
        </div>
      </div>
    )
  }

  return (
    <div className="min-h-screen bg-[#f7f6f2]">
      <div className="max-w-[640px] mx-auto px-6 py-16">

        {/* Header */}
        <div className="mb-10">
          <h1 className="font-serif text-[40px] font-black tracking-tight leading-none mb-3">
            Book a spot
          </h1>
          <p className="text-[15px] font-light text-[#1a1a18]/50">
            Tell us about your vehicle to find the right space.
          </p>
        </div>

        {/* Vehicle type toggle */}
        <div className="mb-8">
          <p className="text-[13px] font-medium text-[#1a1a18]/70 tracking-wide mb-2.5">
            Vehicle type
          </p>
          <div className="flex rounded-sm border border-[#1a1a18]/15 overflow-hidden bg-white">
            {(['car', 'truck'] as VehicleType[]).map(type => (
              <button
                key={type}
                onClick={() => setVehicleType(type)}
                className={`flex-1 py-3.5 text-[14px] font-medium transition-colors flex items-center justify-center gap-2.5
                  ${vehicleType === type ? 'bg-[#1a1a18] text-[#f7f6f2]' : 'bg-white text-[#1a1a18]/50 hover:text-[#1a1a18]'}`}
              >
                {type === 'car' ? (
                  <>
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                      <path d="M5 17H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h1l2-4h10l2 4h1a2 2 0 0 1 2 2v6a2 2 0 0 1-2 2h-2" />
                      <circle cx="7.5" cy="17.5" r="2.5" /><circle cx="16.5" cy="17.5" r="2.5" />
                    </svg>
                    Car
                  </>
                ) : (
                  <>
                    <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
                      <rect x="1" y="3" width="15" height="13" rx="1" />
                      <path d="M16 8h4l3 5v3h-7V8z" />
                      <circle cx="5.5" cy="18.5" r="2.5" /><circle cx="18.5" cy="18.5" r="2.5" />
                    </svg>
                    Truck
                  </>
                )}
              </button>
            ))}
          </div>
        </div>

        {/* Car form */}
        {vehicleType === 'car' && <CarForm car={car} setCar_={setCar_} carErrors={carErrors} handleSubmit={handleSubmit} />}

        {/* Truck form */}
        {vehicleType === 'truck' && <TruckForm truck={truck} setTruck_={setTruck_} truckErrors={truckErrors} handleSubmit={handleSubmit} />}

      </div>
    </div>
  )
}