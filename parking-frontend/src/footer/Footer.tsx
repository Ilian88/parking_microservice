import { Link } from 'react-router-dom'

const links = {
  Company: [
    { label: 'About',   to: '/about'   },
    { label: 'Contact', to: '/contact' },
    { label: 'Careers', to: '/careers' },
  ],
  Parking: [
    { label: 'How it works', to: '/#how-it-works' },
    { label: 'Pricing',      to: '/pricing'       },
    { label: 'Locations',    to: '/locations'     },
  ],
  Legal: [
    { label: 'Privacy policy',    to: '/privacy' },
    { label: 'Terms of service',  to: '/terms'   },
    { label: 'Cookie policy',     to: '/cookies' },
  ],
}

export default function Footer() {
  return (
    <footer style={{
      background: '#1a1a18',
      color: '#f7f6f2',
      fontFamily: "'DM Sans', sans-serif",
    }}>
      <div style={{ maxWidth: '1100px', margin: '0 auto', padding: '72px 48px 40px' }}>

        {/* Top row */}
        <div style={{
          display: 'grid',
          gridTemplateColumns: '2fr 1fr 1fr 1fr',
          gap: '48px',
          marginBottom: '64px',
        }}>

          {/* Brand */}
          <div>
            <div style={{
              fontFamily: "'Fraunces', serif",
              fontSize: '24px',
              fontWeight: 700,
              marginBottom: '16px',
              letterSpacing: '-0.02em',
            }}>
              ParkOS
            </div>
            <p style={{
              fontSize: '14px',
              fontWeight: 300,
              color: 'rgba(247,246,242,0.45)',
              lineHeight: 1.75,
              maxWidth: '260px',
            }}>
              Smart parking for the modern city. Reserve, enter, and pay — all from your phone.
            </p>

            {/* Address */}
            <div style={{ marginTop: '24px' }}>
              <p style={{ fontSize: '13px', color: 'rgba(247,246,242,0.35)', lineHeight: 1.8 }}>
                12 Central Boulevard<br />
                Sofia, 1000, Bulgaria<br />
                +359 2 123 4567
              </p>
            </div>
          </div>

          {/* Link columns */}
          {Object.entries(links).map(([group, items]) => (
            <div key={group}>
              <h4 style={{
                fontSize: '11px',
                letterSpacing: '0.15em',
                textTransform: 'uppercase',
                color: 'rgba(247,246,242,0.35)',
                fontWeight: 500,
                marginBottom: '20px',
              }}>
                {group}
              </h4>
              <ul style={{ listStyle: 'none', display: 'flex', flexDirection: 'column', gap: '12px' }}>
                {items.map(({ label, to }) => (
                  <li key={label}>
                    <Link to={to} style={{
                      fontSize: '14px',
                      fontWeight: 300,
                      color: 'rgba(247,246,242,0.55)',
                      textDecoration: 'none',
                      transition: 'color 0.15s',
                    }}
                    onMouseEnter={e => (e.currentTarget.style.color = '#f7f6f2')}
                    onMouseLeave={e => (e.currentTarget.style.color = 'rgba(247,246,242,0.55)')}
                    >
                      {label}
                    </Link>
                  </li>
                ))}
              </ul>
            </div>
          ))}
        </div>

        {/* Divider */}
        <div style={{ borderTop: '1px solid rgba(247,246,242,0.08)', paddingTop: '32px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '12px' }}>
          <span style={{ fontSize: '13px', color: 'rgba(247,246,242,0.25)', fontWeight: 300 }}>
            © 2026 Central City Parking. All rights reserved.
          </span>
          <div style={{ display: 'flex', gap: '24px' }}>
            {['Facebook', 'Instagram', 'LinkedIn'].map(s => (
              <a key={s} href="#" style={{
                fontSize: '13px',
                color: 'rgba(247,246,242,0.35)',
                textDecoration: 'none',
                transition: 'color 0.15s',
              }}
              onMouseEnter={e => (e.currentTarget.style.color = '#f7f6f2')}
              onMouseLeave={e => (e.currentTarget.style.color = 'rgba(247,246,242,0.35)')}
              >
                {s}
              </a>
            ))}
          </div>
        </div>

      </div>
    </footer>
  )
}