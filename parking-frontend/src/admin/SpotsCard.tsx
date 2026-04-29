import type { ParkingSpot, VehicleType } from "./AdminPage"


const VehicleIcon = ({ type }: { type: VehicleType }) =>
  type === "truck" ? (
    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
      <rect x="1" y="8" width="14" height="10" rx="1" />
      <path d="M15 12h4l3 3v3h-7V12z" />
      <circle cx="5" cy="19" r="2" />
      <circle cx="18" cy="19" r="2" />
    </svg>
  ) : (
    <svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
      <path d="M5 17H3a2 2 0 01-2-2V5a2 2 0 012-2h11l5 5v9a2 2 0 01-2 2h-2" />
      <circle cx="9" cy="17" r="2" />
      <circle cx="17" cy="17" r="2" />
    </svg>
  );

export default function SpotCard({ spot, handleSpotClick, isSelected, sc }: {
    spot: ParkingSpot,
    handleSpotClick: (spot: ParkingSpot) => void,
    isSelected: boolean,
    sc: { dot: string, label: string, bg: string }
}) {

    return (
        <>
            <div
                key={spot.id}
                className={`spot-card ${isSelected ? "active-card" : ""}`}
                onClick={() => handleSpotClick(spot)}
                style={{ background: "white", borderRadius: 12, padding: "16px 20px", display: "flex", alignItems: "center", justifyContent: "space-between" }}
            >
                <div style={{ display: "flex", alignItems: "center", gap: 16 }}>
                    <div style={{ width: 8, height: 8, borderRadius: "50%", background: sc.dot, flexShrink: 0 }} />
                    <div>
                        <div style={{ fontFamily: "'Fraunces', serif", fontSize: 20, fontWeight: 600, letterSpacing: "-0.5px" }}>{spot.id}</div>
                        <div style={{ fontSize: 12, color: "#888", marginTop: 1 }}>{spot.floor}</div>
                    </div>
                    {spot.vehicle && (
                        <div style={{ marginLeft: 8, display: "flex", alignItems: "center", gap: 6, color: "#555", fontSize: 13 }}>
                            <VehicleIcon type={spot.vehicle.type} />
                            <span>{spot.vehicle.plate}</span>
                            <span style={{ color: "#bbb" }}>·</span>
                            <span>{spot.vehicle.since} — {spot.vehicle.until}</span>
                        </div>
                    )}
                </div>
                <div style={{ display: "flex", alignItems: "center", gap: 16 }}>
                    {spot.revenue > 0 && (
                        <span style={{ fontFamily: "'Fraunces', serif", fontSize: 16 }}>€{spot.revenue.toFixed(2)}</span>
                    )}
                    <span style={{ fontSize: 12, color: sc.label, background: sc.bg, padding: "3px 10px", borderRadius: 20, textTransform: "capitalize" }}>
                        {spot.status}
                    </span>
                </div>
            </div>
        </>
    )
}