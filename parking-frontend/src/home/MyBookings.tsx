import { useState } from 'react'

type BookingStatus = 'active' | 'completed' | 'cancelled'

type Vehicle = {
  type: 'car' | 'truck'
  plate: string
  make: string
  model: string
}

type Booking = {
  id: string
  spot: string
  dateIn: string
  dateOut: string
  vehicle: Vehicle
  status: BookingStatus
  price: number
  floor: string
}

const mockBookings: Booking[] = [
  {
    id: 'BK-001',
    spot: 'A-12',
    floor: 'Floor 1',
    dateIn: '2026-03-19T08:00',
    dateOut: '2026-03-19T18:00',
    vehicle: { type: 'car', plate: 'CA 1234 AB', make: 'Toyota', model: 'Corolla' },
    status: 'active',
    price: 12.50,
  },
  {
    id: 'BK-002',
    spot: 'C-04',
    floor: 'Floor 3',
    dateIn: '2026-03-15T09:30',
    dateOut: '2026-03-15T17:00',
    vehicle: { type: 'car', plate: 'CB 5678 CD', make: 'Mazda', model: '6' },
    status: 'completed',
    price: 9.00,
  },
  {
    id: 'BK-003',
    spot: 'T-02',
    floor: 'Truck Zone',
    dateIn: '2026-03-10T07:00',
    dateOut: '2026-03-10T15:00',
    vehicle: { type: 'truck', plate: 'CB 9999 TT', make: 'Volvo', model: 'FH16' },
    status: 'completed',
    price: 32.00,
  },
  {
    id: 'BK-004',
    spot: 'B-07',
    floor: 'Floor 2',
    dateIn: '2026-03-08T10:00',
    dateOut: '2026-03-08T14:00',
    vehicle: { type: 'car', plate: 'CA 1234 AB', make: 'Toyota', model: 'Corolla' },
    status: 'cancelled',
    price: 6.00,
  },
  {
    id: 'BK-005',
    spot: 'A-09',
    floor: 'Floor 1',
    dateIn: '2026-03-01T08:00',
    dateOut: '2026-03-01T20:00',
    vehicle: { type: 'car', plate: 'CA 1234 AB', make: 'Toyota', model: 'Corolla' },
    status: 'completed',
    price: 18.00,
  },
]

const statusConfig: Record<BookingStatus, { label: string; bg: string; text: string; dot: string }> = {
  active:    { label: 'Active',    bg: 'bg-green-50',  text: 'text-green-700', dot: 'bg-green-500'  },
  completed: { label: 'Completed', bg: 'bg-gray-100',  text: 'text-gray-500',  dot: 'bg-gray-400'   },
  cancelled: { label: 'Cancelled', bg: 'bg-red-50',    text: 'text-red-600',   dot: 'bg-red-400'    },
}

function formatDate(iso: string) {
  return new Date(iso).toLocaleDateString('en-GB', { day: '2-digit', month: 'short', year: 'numeric' })
}

function formatTime(iso: string) {
  return new Date(iso).toLocaleTimeString('en-GB', { hour: '2-digit', minute: '2-digit' })
}

function VehicleIcon({ type }: { type: 'car' | 'truck' }) {
  if (type === 'truck') return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
      <rect x="1" y="3" width="15" height="13" rx="1" />
      <path d="M16 8h4l3 5v3h-7V8z" />
      <circle cx="5.5" cy="18.5" r="2.5" /><circle cx="18.5" cy="18.5" r="2.5" />
    </svg>
  )
  return (
    <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="1.5" strokeLinecap="round" strokeLinejoin="round">
      <path d="M5 17H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h1l2-4h10l2 4h1a2 2 0 0 1 2 2v6a2 2 0 0 1-2 2h-2" />
      <circle cx="7.5" cy="17.5" r="2.5" /><circle cx="16.5" cy="17.5" r="2.5" />
    </svg>
  )
}

type Filter = 'all' | BookingStatus

export default function MyBookings() {
  const [bookings, setBookings]         = useState<Booking[]>(mockBookings)
  const [filter, setFilter]             = useState<Filter>('all')
  const [expandedId, setExpandedId]     = useState<string | null>(null)
  const [cancellingId, setCancellingId] = useState<string | null>(null)

  const filtered = filter === 'all' ? bookings : bookings.filter(b => b.status === filter)

  const handleCancel = (id: string) => {
    setBookings(prev => prev.map(b => b.id === id ? { ...b, status: 'cancelled' } : b))
    setCancellingId(null)
  }

  const counts = {
    all:       bookings.length,
    active:    bookings.filter(b => b.status === 'active').length,
    completed: bookings.filter(b => b.status === 'completed').length,
    cancelled: bookings.filter(b => b.status === 'cancelled').length,
  }

  return (
    <div className="min-h-screen bg-[#f7f6f2]">
      <div className="max-w-[760px] mx-auto px-6 py-16">

        {/* Header */}
        <div className="mb-10">
          <h1 className="font-serif text-[40px] font-black tracking-tight leading-none mb-2">
            My bookings
          </h1>
          <p className="text-[15px] font-light text-[#1a1a18]/50">
            {counts.active > 0
              ? `You have ${counts.active} active booking${counts.active > 1 ? 's' : ''}.`
              : 'No active bookings right now.'}
          </p>
        </div>

        {/* Filter tabs */}
        <div className="flex gap-1 mb-10 border-b border-[#1a1a18]/10">
          {(['all', 'active', 'completed', 'cancelled'] as Filter[]).map(f => (
            <button
              key={f}
              onClick={() => setFilter(f)}
              className={`px-4 py-2.5 text-[13px] font-medium capitalize transition-colors border-b-2 -mb-px
                ${filter === f
                  ? 'border-[#1a1a18] text-[#1a1a18]'
                  : 'border-transparent text-[#1a1a18]/40 hover:text-[#1a1a18]/70'
                }`}
            >
              {f} <span className="ml-1 text-[11px] opacity-60">{counts[f]}</span>
            </button>
          ))}
        </div>

        {/* Timeline */}
        {filtered.length === 0 ? (
          <div className="text-center py-20 text-[#1a1a18]/30 font-light text-[15px]">
            No bookings found.
          </div>
        ) : (
          <div className="relative">
            {/* vertical line */}
            <div className="absolute left-[7px] top-2 bottom-2 w-px bg-[#1a1a18]/10" />

            <div className="flex flex-col gap-0">
              {filtered.map((booking, i) => {
                const st        = statusConfig[booking.status]
                const isExpanded = expandedId === booking.id
                const isConfirmingCancel = cancellingId === booking.id

                return (
                  <div key={booking.id} className="relative pl-10 pb-8">

                    {/* timeline dot */}
                    <div className={`absolute left-0 top-1.5 w-[15px] h-[15px] rounded-full border-2 border-[#f7f6f2] ${st.dot} z-10`} />

                    {/* date label — show on first item or when date changes */}
                    {(i === 0 || formatDate(filtered[i - 1].dateIn) !== formatDate(booking.dateIn)) && (
                      <p className="text-[11px] tracking-[0.12em] uppercase text-[#1a1a18]/35 font-medium mb-3">
                        {formatDate(booking.dateIn)}
                      </p>
                    )}

                    {/* card */}
                    <div className={`bg-white border border-[#1a1a18]/10 rounded-lg overflow-hidden transition-all`}>

                      {/* card header */}
                      <div
                        className="px-5 py-4 flex items-center justify-between cursor-pointer hover:bg-[#1a1a18]/[0.02] transition-colors"
                        onClick={() => setExpandedId(isExpanded ? null : booking.id)}
                      >
                        <div className="flex items-center gap-4">
                          {/* spot */}
                          <div className="text-center">
                            <div className="font-serif text-[22px] font-bold leading-none tracking-tight">{booking.spot}</div>
                            <div className="text-[11px] text-[#1a1a18]/35 mt-0.5">{booking.floor}</div>
                          </div>

                          <div className="w-px h-8 bg-[#1a1a18]/10" />

                          {/* time */}
                          <div>
                            <div className="text-[14px] font-medium text-[#1a1a18]">
                              {formatTime(booking.dateIn)} — {formatTime(booking.dateOut)}
                            </div>
                            <div className="flex items-center gap-1.5 mt-0.5 text-[#1a1a18]/45">
                              <VehicleIcon type={booking.vehicle.type} />
                              <span className="text-[13px] font-light">{booking.vehicle.plate}</span>
                            </div>
                          </div>
                        </div>

                        <div className="flex items-center gap-3">
                          {/* price */}
                          <span className="font-serif text-[18px] font-bold tracking-tight">
                            €{booking.price.toFixed(2)}
                          </span>

                          {/* status badge */}
                          <span className={`text-[11px] font-medium px-2.5 py-1 rounded-sm ${st.bg} ${st.text}`}>
                            {st.label}
                          </span>

                          {/* chevron */}
                          <svg
                            width="14" height="14" viewBox="0 0 24 24" fill="none"
                            stroke="currentColor" strokeWidth="2" strokeLinecap="round"
                            className={`text-[#1a1a18]/30 transition-transform ${isExpanded ? 'rotate-180' : ''}`}
                          >
                            <polyline points="6 9 12 15 18 9" />
                          </svg>
                        </div>
                      </div>

                      {/* expanded details */}
                      {isExpanded && (
                        <div className="border-t border-[#1a1a18]/08 px-5 py-4 bg-[#f7f6f2]/60">
                          <div className="grid grid-cols-2 gap-x-8 gap-y-3 mb-4">
                            {[
                              { label: 'Booking ID',  value: booking.id                                     },
                              { label: 'Spot',        value: `${booking.spot} — ${booking.floor}`           },
                              { label: 'Check in',    value: `${formatDate(booking.dateIn)} ${formatTime(booking.dateIn)}`   },
                              { label: 'Check out',   value: `${formatDate(booking.dateOut)} ${formatTime(booking.dateOut)}` },
                              { label: 'Vehicle',     value: `${booking.vehicle.make} ${booking.vehicle.model}` },
                              { label: 'Plate',       value: booking.vehicle.plate                          },
                              { label: 'Type',        value: booking.vehicle.type === 'car' ? 'Car' : 'Truck' },
                              { label: 'Total price', value: `€${booking.price.toFixed(2)}`                 },
                            ].map(({ label, value }) => (
                              <div key={label}>
                                <p className="text-[11px] text-[#1a1a18]/35 uppercase tracking-wide font-medium mb-0.5">{label}</p>
                                <p className="text-[14px] font-light text-[#1a1a18]">{value}</p>
                              </div>
                            ))}
                          </div>

                          {/* cancel action */}
                          {booking.status === 'active' && (
                            <div className="border-t border-[#1a1a18]/08 pt-4">
                              {isConfirmingCancel ? (
                                <div className="flex items-center gap-3">
                                  <p className="text-[13px] text-[#1a1a18]/60 font-light flex-1">
                                    Are you sure you want to cancel this booking?
                                  </p>
                                  <button
                                    onClick={() => setCancellingId(null)}
                                    className="text-[13px] px-4 py-2 border border-[#1a1a18]/15 rounded-sm hover:bg-[#1a1a18]/05 transition-colors"
                                  >
                                    Keep it
                                  </button>
                                  <button
                                    onClick={() => handleCancel(booking.id)}
                                    className="text-[13px] px-4 py-2 bg-red-500 text-white rounded-sm hover:bg-red-600 transition-colors"
                                  >
                                    Yes, cancel
                                  </button>
                                </div>
                              ) : (
                                <button
                                  onClick={() => setCancellingId(booking.id)}
                                  className="text-[13px] text-red-500 hover:text-red-700 font-medium transition-colors"
                                >
                                  Cancel booking
                                </button>
                              )}
                            </div>
                          )}
                        </div>
                      )}
                    </div>
                  </div>
                )
              })}
            </div>
          </div>
        )}
      </div>
    </div>
  )
}