import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

const stats = [
  { value: '240',    label: 'Parking spots'      },
  { value: '24/7',   label: 'Always open'         },
  { value: '< 2min', label: 'Avg. entry time'     },
  { value: 'Free',   label: 'App & registration'  },
]

const features = [
  {
    title: 'Reserve in advance',
    desc: 'Pick your date, time, and floor. Your spot is locked — no surprises when you arrive.',
  },
  {
    title: 'Live availability',
    desc: 'See exactly how many spots are free right now, on every floor, before you even leave home.',
  },
  {
    title: 'Contactless entry',
    desc: 'Your QR code opens the barrier. No paper tickets, no buttons, no queues.',
  },
  {
    title: 'Full history',
    desc: 'Every visit logged. Download invoices, track spending, manage your vehicles in one place.',
  },
]

const steps = [
  { n: '01', title: 'Create an account', desc: 'Sign up for free in under a minute.'              },
  { n: '02', title: 'Choose your spot',  desc: 'Browse the map and pick a floor and space.'       },
  { n: '03', title: 'Show up & scan',    desc: 'Arrive, scan your QR, drive straight in.'         },
]

export default function About() {
  const [visible, setVisible] = useState(false)

  useEffect(() => {
    const t = setTimeout(() => setVisible(true), 80)
    return () => clearTimeout(t)
  }, [])

  const fu = (delay: string) =>
    `transition-all duration-700 ease-out ${visible ? 'opacity-100 translate-y-0' : 'opacity-0 translate-y-5'} ${delay}`

  return (
    <div className="font-sans bg-[#f7f6f2] min-h-screen text-[#1a1a18]">

      {/* Hero */}
      <section className="max-w-[1100px] mx-auto px-12 pt-24 pb-20">

        <div className={fu('delay-[50ms]')}>
          <span className="text-[11px] tracking-[0.18em] uppercase font-medium text-[#1a1a18]/40 bg-[#1a1a18]/[0.07] px-3 py-1.5 rounded-sm">
            Central City Parking — Block A
          </span>
        </div>

        <h1 className={`${fu('delay-[180ms]')} font-serif text-[clamp(52px,8vw,96px)] font-black leading-none tracking-tight mt-5 mb-7 max-w-[700px]`}>
          Your spot,<br />waiting for you.
        </h1>

        <p className={`${fu('delay-[300ms]')} text-lg font-light text-[#1a1a18]/55 max-w-[460px] leading-relaxed mb-10`}>
          A modern parking facility with online reservations, live availability, and
          contactless entry. Register once, park forever without friction.
        </p>

         <div className={`${fu('delay-[420ms]')} flex gap-3 flex-wrap`}>
          <Link
            to="/register"
            className="inline-block bg-[#1a1a18] text-[#f7f6f2] no-underline px-9 py-4 rounded-sm text-sm font-medium tracking-wide hover:bg-[#333330] hover:-translate-y-px transition-all"
          >
            Create free account
          </Link>
          <Link
            to="/login"
            className="inline-block bg-transparent text-[#1a1a18] no-underline px-9 py-4 rounded-sm text-sm font-normal border border-[#1a1a18]/25 hover:border-[#1a1a18]/60 hover:-translate-y-px transition-all"
          >
            I have an account
          </Link>
        </div>
      </section>

      {/* Stats strip */}
      <section className="border-t border-b border-[#1a1a18]/10 bg-white">
        <div className="max-w-[1100px] mx-auto px-12 grid grid-cols-4">
          {stats.map(({ value, label }, i) => (
            <div
              key={label}
              className={`py-8 text-center ${i < stats.length - 1 ? 'border-r border-[#1a1a18]/10' : ''}`}
            >
              <div className="font-serif text-[40px] font-bold tracking-tight leading-none mb-1.5">{value}</div>
              <div className="text-[13px] text-[#1a1a18]/45 tracking-wide">{label}</div>
            </div>
          ))}
        </div>
      </section>

      {/* Features */}
      <section className="max-w-[1100px] mx-auto px-12 py-24">
        <div className="mb-12">
          <span className="text-[11px] tracking-[0.15em] uppercase text-[#1a1a18]/35 font-medium">
            Everything included
          </span>
        </div>
        {features.map(({ title, desc }) => (
          <div
            key={title}
            className="grid grid-cols-[1fr_2fr] gap-12 items-start py-9 border-t border-[#1a1a18]/10 hover:border-[#1a1a18]/30 transition-colors"
          >
            <h3 className="font-serif text-[22px] font-bold leading-tight">{title}</h3>
            <p className="text-base font-light text-[#1a1a18]/55 leading-relaxed">{desc}</p>
          </div>
        ))}
      </section>

      {/* How it works */}
      <section className="bg-[#1a1a18] text-[#f7f6f2]">
        <div className="max-w-[1100px] mx-auto px-12 py-24">
          <div className="mb-14">
            <span className="text-[11px] tracking-[0.15em] uppercase text-[#f7f6f2]/35 font-medium">
              How it works
            </span>
            <h2 className="font-serif text-[40px] font-bold mt-4 tracking-tight">
              Three steps to parked.
            </h2>
          </div>
          <div className="grid grid-cols-1 sm:grid-cols-3 gap-5">
            {steps.map(({ n, title, desc }) => (
              <div
                key={n}
                className="p-8 border border-[#f7f6f2]/10 rounded-md bg-[#f7f6f2]/5 hover:bg-[#f7f6f2]/[0.08] hover:-translate-y-0.5 transition-all"
              >
                <div className="font-serif text-[13px] text-[#f7f6f2]/30 mb-5 tracking-wide">{n}</div>
                <h3 className="font-serif text-xl font-bold mb-2.5 text-[#f7f6f2]">{title}</h3>
                <p className="text-sm font-light text-[#f7f6f2]/50 leading-relaxed">{desc}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Final CTA */}
      <section className="max-w-[1100px] mx-auto px-12 py-24">
        <div className="border border-[#1a1a18]/12 rounded-lg p-16 bg-white flex items-center justify-between flex-wrap gap-8">
          <div>
            <h2 className="font-serif text-[36px] font-bold tracking-tight mb-2.5">
              Ready to get started?
            </h2>
            <p className="text-base font-light text-[#1a1a18]/50">
              Registration is free. No credit card required.
            </p>
          </div>
          <Link
            to="/register"
            className="inline-block bg-[#1a1a18] text-[#f7f6f2] no-underline px-10 py-4 rounded-sm text-base font-medium hover:bg-[#333330] hover:-translate-y-px transition-all whitespace-nowrap"
          >
            Register now
          </Link>
        </div>
      </section>

    </div>
  )
}