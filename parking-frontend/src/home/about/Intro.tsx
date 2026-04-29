import { Link } from "react-router-dom";

export default function Intro({fu}: {fu: (delay: string)=> string}) {
    return (
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
    )
}