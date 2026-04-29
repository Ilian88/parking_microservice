import { useState } from "react";
import ParkingSpotDetails from "./ParkingSpotDetails";
import { styles } from "./styles";
import SpotCard from "./SpotsCard";

export type VehicleType = "car" | "truck";
export type SpotStatus = "occupied" | "free" | "reserved" | "booked";

interface Vehicle {
  plate: string;
  type: VehicleType;
  owner: string;
  since: string;
  until: string;
}

export interface ParkingSpot {
  id: string;
  floor: string;
  status: SpotStatus;
  vehicle: Vehicle | null;
  revenue: number;
}

export interface StatusStyle {
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


const PAGE_SIZE = 5;

function Header() {
  return (
    <div style={{ marginBottom: 32 }}>
      <h1 style={{ fontFamily: "'Fraunces', serif", fontSize: 40, fontWeight: 600, lineHeight: 1.1 }}>Admin overview</h1>
      <p style={{ color: "#888", fontSize: 14, marginTop: 6 }}>Real-time parking occupancy and vehicle details.</p>
    </div>
  )
}

function Stats({totalRevenue, occupiedCount, freeCount, length}: {
  totalRevenue: number,
  occupiedCount: number, 
  freeCount: number,
  length: number
}) {
  return (
    <div style={{ display: "grid", gridTemplateColumns: "repeat(4, 1fr)", gap: 16, marginBottom: 36 }}>
          {[
            { label: "Total spots", value: length.toString(), accent: "#1a1a1a" },
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
  )
}

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


  return (
    <div style={{ fontFamily: "'DM Sans', sans-serif", background: "#f2ede8", minHeight: "100vh", color: "#1a1a1a" }}>
      <style>{styles}</style>

      <div style={{ maxWidth: 1200, margin: "0 auto", padding: "40px 24px" }}>

        {/* Header */}
        <Header/>

        {/* Stats */}
        <Stats  totalRevenue={totalRevenue} occupiedCount={occupiedCount}  freeCount={freeCount} length={mockSpots.length}/>

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
                  <SpotCard key={spot.id} spot={spot} handleSpotClick={handleSpotClick} isSelected={isSelected} sc={sc}/>
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
            {selected ? <ParkingSpotDetails selected={selected} statusColor={statusColor} /> : (
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