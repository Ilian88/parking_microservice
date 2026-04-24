import { useState } from "react";

type VehicleType = "car" | "truck";
type SpotStatus = "occupied" | "free" | "reserved" | "booked";

interface Vehicle {
  plate: string;
  type: VehicleType;
  owner: string;
  since: string;
  until: string;
}

interface ParkingSpot {
  id: string;
  floor: string;
  status: SpotStatus;
  vehicle: Vehicle | null;
  revenue: number;
}

interface StatusStyle {
  dot: string;
  label: string;
  bg: string;
}

const mockSpots: ParkingSpot[] = [
  { id: "A-12", floor: "Floor 1", status: "occupied", vehicle: { plate: "CA 1234 AB", type: "car", owner: "Ivan Markov", since: "08:00", until: "18:00" }, revenue: 12.50 },
  { id: "A-07", floor: "Floor 1", status: "occupied", vehicle: { plate: "CB 9988 XY", type: "car", owner: "Maria Petrova", since: "09:00", until: "17:00" }, revenue: 9.60 },
  { id: "A-03", floor: "Floor 1", status: "free", vehicle: null, revenue: 0 },
  { id: "A-15", floor: "Floor 1", status: "free", vehicle: null, revenue: 0 },
  { id: "B-07", floor: "Floor 2", status: "occupied", vehicle: { plate: "CA 4321 BB", type: "car", owner: "Georgi Ivanov", since: "10:00", until: "14:00" }, revenue: 6.00 },
  { id: "B-01", floor: "Floor 2", status: "reserved", vehicle: { plate: "CB 1111 AA", type: "car", owner: "Stefan Nikolov", since: "12:00", until: "20:00" }, revenue: 0 },
  { id: "B-09", floor: "Floor 2", status: "free", vehicle: null, revenue: 0 },
  { id: "C-04", floor: "Floor 3", status: "occupied", vehicle: { plate: "CB 5678 CD", type: "car", owner: "Elena Stoyanova", since: "09:30", until: "17:00" }, revenue: 9.00 },
  { id: "C-11", floor: "Floor 3", status: "free", vehicle: null, revenue: 0 },
  { id: "T-02", floor: "Truck Zone", status: "occupied", vehicle: { plate: "CB 9999 TT", type: "truck", owner: "Logistics Co.", since: "07:00", until: "15:00" }, revenue: 32.00 },
  { id: "T-05", floor: "Truck Zone", status: "free", vehicle: null, revenue: 0 },
  { id: "A-09", floor: "Floor 1", status: "booked", vehicle: { plate: "CB 7777 KK", type: "car", owner: "Petar Dimitrov", since: "14:00", until: "20:00" }, revenue: 0 },
];

const statusColor: Record<SpotStatus, StatusStyle> = {
  occupied: { dot: "#2d6a4f", label: "#2d6a4f", bg: "#f0f7f4" },
  free: { dot: "#adb5bd", label: "#6c757d", bg: "#f8f9fa" },
  reserved: { dot: "#c9841a", label: "#c9841a", bg: "#fdf6ec" },
  booked: { dot: "#4a6fa5", label: "#4a6fa5", bg: "#eef2f8" },
};

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

interface DetailRowProps {
  label: string;
  value: string;
  capitalize?: boolean;
}

const DetailRow = ({ label, value, capitalize = false }: DetailRowProps) => (
  <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
    <span style={{ fontSize: 13, color: "#888" }}>{label}</span>
    <span style={{ fontSize: 13, fontWeight: 500, textTransform: capitalize ? "capitalize" : "none" }}>{value}</span>
  </div>
);

const PAGE_SIZE = 5;

export default function AdminPage() {
  const [selectedFloor, setSelectedFloor] = useState<string>("All");
  const [selectedStatus, setSelectedStatus] = useState<string>("All");
  const [selected, setSelected] = useState<ParkingSpot | null>(null);
  const [page, setPage] = useState<number>(1);

  const floors: string[] = ["All", ...Array.from(new Set(mockSpots.map((s) => s.floor)))];
  const statuses: string[] = ["All", "occupied", "free", "reserved", "booked"];

  const filtered = mockSpots.filter((s) => {
    const floorMatch = selectedFloor === "All" || s.floor === selectedFloor;
    const statusMatch = selectedStatus === "All" || s.status === selectedStatus;
    return floorMatch && statusMatch;
  });

  const totalPages = Math.ceil(filtered.length / PAGE_SIZE);
  const paginated = filtered.slice((page - 1) * PAGE_SIZE, page * PAGE_SIZE);

  const handleFilterChange = (setter: (v: string) => void, value: string) => {
    setter(value);
    setPage(1); // reset to first page on filter change
  };

  const totalRevenue = mockSpots.reduce((acc, s) => acc + s.revenue, 0);
  const occupiedCount = mockSpots.filter((s) => s.status === "occupied").length;
  const freeCount = mockSpots.filter((s) => s.status === "free").length;

  const handleSpotClick = (spot: ParkingSpot) => {
    setSelected((prev) => (prev?.id === spot.id ? null : spot));
  };

  const handleRelease = (spotId: string) => {
    alert(`Releasing spot ${spotId}...`);
  };

  return (
    <div style={{ fontFamily: "'DM Sans', sans-serif", background: "#f2ede8", minHeight: "100vh", color: "#1a1a1a" }}>
      <style>{`
        @import url('https://fonts.googleapis.com/css2?family=Fraunces:ital,wght@0,400;0,600;1,400&family=DM+Sans:wght@300;400;500&display=swap');
        * { box-sizing: border-box; margin: 0; padding: 0; }
        .spot-card { transition: box-shadow 0.15s, transform 0.15s; cursor: pointer; }
        .spot-card:hover { box-shadow: 0 4px 16px rgba(0,0,0,0.10); transform: translateY(-1px); }
        .spot-card.active-card { box-shadow: 0 0 0 2px #1a1a1a; }
        .filter-btn { background: none; border: 1px solid #d5cfc8; border-radius: 20px; padding: 5px 14px; font-family: 'DM Sans', sans-serif; font-size: 13px; cursor: pointer; transition: all 0.15s; }
        .filter-btn:hover { background: #e8e2db; }
        .filter-btn.active-btn { background: #1a1a1a; color: #f2ede8; border-color: #1a1a1a; }
        .stat-card { background: white; border-radius: 12px; padding: 20px 24px; }
        .detail-panel { background: white; border-radius: 14px; padding: 28px; position: sticky; top: 24px; }
        .release-btn { width: 100%; background: #1a1a1a; color: #f2ede8; border: none; border-radius: 8px; padding: 12px; font-family: 'DM Sans', sans-serif; font-size: 13px; font-weight: 500; cursor: pointer; transition: opacity 0.15s; }
        .release-btn:hover { opacity: 0.85; }
      `}</style>

      <div style={{ maxWidth: 1200, margin: "0 auto", padding: "40px 24px" }}>

        {/* Header */}
        <div style={{ marginBottom: 32 }}>
          <h1 style={{ fontFamily: "'Fraunces', serif", fontSize: 40, fontWeight: 600, lineHeight: 1.1 }}>Admin overview</h1>
          <p style={{ color: "#888", fontSize: 14, marginTop: 6 }}>Real-time parking occupancy and vehicle details.</p>
        </div>

        {/* Stats */}
        <div style={{ display: "grid", gridTemplateColumns: "repeat(4, 1fr)", gap: 16, marginBottom: 36 }}>
          {[
            { label: "Total spots", value: mockSpots.length.toString(), accent: "#1a1a1a" },
            { label: "Occupied", value: occupiedCount.toString(), accent: "#2d6a4f" },
            { label: "Free", value: freeCount.toString(), accent: "#adb5bd" },
            { label: "Today's revenue", value: `€${totalRevenue.toFixed(2)}`, accent: "#c9841a" },
          ].map((s) => (
            <div key={s.label} className="stat-card">
              <div style={{ fontSize: 28, fontFamily: "'Fraunces', serif", fontWeight: 600, color: s.accent }}>{s.value}</div>
              <div style={{ fontSize: 13, color: "#888", marginTop: 4 }}>{s.label}</div>
            </div>
          ))}
        </div>

        <div style={{ display: "grid", gridTemplateColumns: "1fr 320px", gap: 24, alignItems: "start" }}>

          {/* Left */}
          <div>
            {/* Filters */}
            <div style={{ display: "flex", gap: 24, marginBottom: 20, alignItems: "center", flexWrap: "wrap" }}>
              <div style={{ display: "flex", gap: 6 }}>
                {floors.map((f) => (
                  <button key={f} className={`filter-btn ${selectedFloor === f ? "active-btn" : ""}`} onClick={() => handleFilterChange(setSelectedFloor, f)}>{f}</button>
                ))}
              </div>
              <div style={{ width: 1, height: 20, background: "#d5cfc8" }} />
              <div style={{ display: "flex", gap: 6 }}>
                {statuses.map((s) => (
                  <button key={s} className={`filter-btn ${selectedStatus === s ? "active-btn" : ""}`} onClick={() => handleFilterChange(setSelectedStatus, s)} style={{ textTransform: "capitalize" }}>{s}</button>
                ))}
              </div>
            </div>

            {/* Spot cards */}
            <div style={{ display: "flex", flexDirection: "column", gap: 8 }}>
              {paginated.map((spot) => {
                const sc = statusColor[spot.status];
                const isSelected = selected?.id === spot.id;
                return (
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
                );
              })}
            </div>

            {/* Pagination */}
            {totalPages > 1 && (
              <div style={{ display: "flex", alignItems: "center", justifyContent: "space-between", marginTop: 20 }}>
                <span style={{ fontSize: 13, color: "#888" }}>
                  Showing {(page - 1) * PAGE_SIZE + 1}–{Math.min(page * PAGE_SIZE, filtered.length)} of {filtered.length}
                </span>
                <div style={{ display: "flex", gap: 6 }}>
                  <button
                    className="filter-btn"
                    onClick={() => setPage((p) => Math.max(1, p - 1))}
                    disabled={page === 1}
                    style={{ opacity: page === 1 ? 0.4 : 1, cursor: page === 1 ? "default" : "pointer" }}
                  >
                    ← Prev
                  </button>
                  {Array.from({ length: totalPages }, (_, i) => i + 1).map((p) => (
                    <button
                      key={p}
                      className={`filter-btn ${page === p ? "active-btn" : ""}`}
                      onClick={() => setPage(p)}
                    >
                      {p}
                    </button>
                  ))}
                  <button
                    className="filter-btn"
                    onClick={() => setPage((p) => Math.min(totalPages, p + 1))}
                    disabled={page === totalPages}
                    style={{ opacity: page === totalPages ? 0.4 : 1, cursor: page === totalPages ? "default" : "pointer" }}
                  >
                    Next →
                  </button>
                </div>
              </div>
            )}
          </div>

          {/* Right — detail panel */}
          <div className="detail-panel">
            {selected ? (
              <>
                <div style={{ display: "flex", justifyContent: "space-between", alignItems: "flex-start", marginBottom: 24 }}>
                  <div>
                    <div style={{ fontFamily: "'Fraunces', serif", fontSize: 32, fontWeight: 600 }}>{selected.id}</div>
                    <div style={{ fontSize: 13, color: "#888" }}>{selected.floor}</div>
                  </div>
                  <span style={{ fontSize: 12, color: statusColor[selected.status].label, background: statusColor[selected.status].bg, padding: "4px 12px", borderRadius: 20, textTransform: "capitalize" }}>
                    {selected.status}
                  </span>
                </div>

                {selected.vehicle ? (
                  <>
                    <div style={{ borderTop: "1px solid #eee", paddingTop: 20, display: "flex", flexDirection: "column", gap: 16 }}>
                      <DetailRow label="License plate" value={selected.vehicle.plate} />
                      <DetailRow label="Vehicle type" value={selected.vehicle.type} capitalize />
                      <DetailRow label="Owner" value={selected.vehicle.owner} />
                      <DetailRow label="Entry time" value={selected.vehicle.since} />
                      <DetailRow label="Exit time" value={selected.vehicle.until} />
                      <DetailRow label="Revenue" value={`€${selected.revenue.toFixed(2)}`} />
                    </div>
                    {selected.status === "occupied" && (
                      <button className="release-btn" style={{ marginTop: 28 }} onClick={() => handleRelease(selected.id)}>
                        Release spot
                      </button>
                    )}
                    {selected.status === "booked" && (
                      <button
                        className="release-btn"
                        style={{ marginTop: 28, background: "#4a6fa5" }}
                        onClick={() => alert(`Cancelling booking for spot ${selected.id}...`)}
                      >
                        Cancel booking
                      </button>
                    )}
                    {selected.status === "reserved" && (
                      <button
                        className="release-btn"
                        style={{ marginTop: 28, background: "#c9841a" }}
                        onClick={() => alert(`Releasing reservation for spot ${selected.id}...`)}
                      >
                        Release reservation
                      </button>
                    )}
                  </>
                ) : (
                  <div style={{ borderTop: "1px solid #eee", paddingTop: 24, textAlign: "center", color: "#aaa", fontSize: 14 }}>
                    <div style={{ fontSize: 32, marginBottom: 8 }}>🅿️</div>
                    This spot is currently free.
                  </div>
                )}
              </>
            ) : (
              <div style={{ textAlign: "center", color: "#bbb", fontSize: 14, padding: "40px 0" }}>
                <div style={{ fontSize: 28, marginBottom: 12 }}>←</div>
                Select a spot to see details
              </div>
            )}
          </div>

        </div>
      </div>
    </div>
  );
}