import { useState } from 'react'
import { Link } from 'react-router-dom'

type VehicleType = 'car' | 'truck'

type EuroCategory = 'Euro 1' | 'Euro 2' | 'Euro 3' | 'Euro 4' | 'Euro 5' | 'Euro 6'

type CarForm = {
  licensePlate: string
  euroCategory: EuroCategory | ''
  make: string
  model: string
}

type TruckForm = {
  licensePlate: string
  euroCategory: EuroCategory | ''
  truckMake: string
  truckModel: string
  trailerMake: string
  trailerModel: string
  weight: string
  length: string
}

type CarErrors   = Partial<Record<keyof CarForm,   string>>
type TruckErrors = Partial<Record<keyof TruckForm, string>>

const initialCar: CarForm = {
  licensePlate: '',
  euroCategory: '',
  make: '',
  model: '',
}

const initialTruck: TruckForm = {
  licensePlate: '',
  euroCategory: '',
  truckMake: '',
  truckModel: '',
  trailerMake: '',
  trailerModel: '',
  weight: '',
  length: '',
}

const euroOptions: EuroCategory[] = ['Euro 1', 'Euro 2', 'Euro 3', 'Euro 4', 'Euro 5', 'Euro 6']

function validateCar(f: CarForm): CarErrors {
  const e: CarErrors = {}
  if (!f.licensePlate.trim()) e.licensePlate = 'Required'
  if (!f.euroCategory)        e.euroCategory = 'Required'
  return e
}

function validateTruck(f: TruckForm): TruckErrors {
  const e: TruckErrors = {}
  if (!f.licensePlate.trim()) e.licensePlate = 'Required'
  if (!f.euroCategory)        e.euroCategory = 'Required'
  if (!f.weight.trim())       e.weight       = 'Required'
  if (!f.length.trim())       e.length       = 'Required'
  return e
}

function Field({
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

function SelectField({
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

function SectionLabel({ children }: { children: string }) {
  return (
    <p className="text-[11px] tracking-[0.15em] uppercase text-[#1a1a18]/35 font-medium mt-2">
      {children}
    </p>
  )
}

export default function Book() {
  const [vehicleType, setVehicleType] = useState<VehicleType>('car')
  const [car,   setCar]   = useState<CarForm>(initialCar)
  const [truck, setTruck] = useState<TruckForm>(initialTruck)
  const [carErrors,   setCarErrors]   = useState<CarErrors>({})
  const [truckErrors, setTruckErrors] = useState<TruckErrors>({})
  const [submitted, setSubmitted] = useState(false)

  const setCar_   = (key: keyof CarForm)   => (val: string) => setCar(p   => ({ ...p, [key]: val }))
  const setTruck_ = (key: keyof TruckForm) => (val: string) => setTruck(p => ({ ...p, [key]: val }))

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
        {vehicleType === 'car' && (
          <div className="flex flex-col gap-5">
            <SectionLabel>Vehicle details</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="License plate" id="licensePlate"
                value={car.licensePlate} onChange={setCar_('licensePlate')}
                error={carErrors.licensePlate} placeholder="CA 1234 AB" required
              />
              <SelectField
                label="Euro category" id="euroCategory"
                value={car.euroCategory} onChange={setCar_('euroCategory')}
                error={carErrors.euroCategory} options={euroOptions}
                placeholder="Select category" required
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Make" id="make"
                value={car.make} onChange={setCar_('make')}
                error={carErrors.make} placeholder="e.g. Toyota"
              />
              <Field
                label="Model" id="model"
                value={car.model} onChange={setCar_('model')}
                error={carErrors.model} placeholder="e.g. Corolla"
              />
            </div>

            <p className="text-[12px] text-[#1a1a18]/35 font-light">
              Fields marked with <span className="text-red-400">*</span> are required.
            </p>

            <button
              onClick={handleSubmit}
              className="w-full bg-[#1a1a18] text-[#f7f6f2] py-4 rounded-sm text-[15px] font-medium hover:bg-[#333330] hover:-translate-y-px active:translate-y-0 transition-all mt-2"
            >
              Confirm booking
            </button>
          </div>
        )}

        {/* Truck form */}
        {vehicleType === 'truck' && (
          <div className="flex flex-col gap-5">
            <SectionLabel>Common details</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="License plate" id="licensePlate"
                value={truck.licensePlate} onChange={setTruck_('licensePlate')}
                error={truckErrors.licensePlate} placeholder="CA 1234 AB" required
              />
              <SelectField
                label="Euro category" id="euroCategory"
                value={truck.euroCategory} onChange={setTruck_('euroCategory')}
                error={truckErrors.euroCategory} options={euroOptions}
                placeholder="Select category" required
              />
            </div>

            <SectionLabel>Truck</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Truck make" id="truckMake"
                value={truck.truckMake} onChange={setTruck_('truckMake')}
                error={truckErrors.truckMake} placeholder="e.g. Volvo"
              />
              <Field
                label="Truck model" id="truckModel"
                value={truck.truckModel} onChange={setTruck_('truckModel')}
                error={truckErrors.truckModel} placeholder="e.g. FH16"
              />
            </div>

            <SectionLabel>Trailer</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Trailer make" id="trailerMake"
                value={truck.trailerMake} onChange={setTruck_('trailerMake')}
                error={truckErrors.trailerMake} placeholder="e.g. Schmitz"
              />
              <Field
                label="Trailer model" id="trailerModel"
                value={truck.trailerModel} onChange={setTruck_('trailerModel')}
                error={truckErrors.trailerModel} placeholder="e.g. S.KO"
              />
            </div>

            <SectionLabel>Composition</SectionLabel>

            <div className="grid grid-cols-2 gap-4">
              <Field
                label="Total weight (kg)" id="weight"
                type="number" value={truck.weight} onChange={setTruck_('weight')}
                error={truckErrors.weight} placeholder="e.g. 40000" required
              />
              <Field
                label="Total length (m)" id="length"
                type="number" value={truck.length} onChange={setTruck_('length')}
                error={truckErrors.length} placeholder="e.g. 18.75" required
              />
            </div>

            <p className="text-[12px] text-[#1a1a18]/35 font-light">
              Fields marked with <span className="text-red-400">*</span> are required.
            </p>

            <button
              onClick={handleSubmit}
              className="w-full bg-[#1a1a18] text-[#f7f6f2] py-4 rounded-sm text-[15px] font-medium hover:bg-[#333330] hover:-translate-y-px active:translate-y-0 transition-all mt-2"
            >
              Confirm booking
            </button>
          </div>
        )}

      </div>
    </div>
  )
}