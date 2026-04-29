import type { ParkingSpot, SpotStatus, StatusStyle } from "./AdminPage";


type ParkingDetailsProps = {
    selected: ParkingSpot,
    statusColor: Record<SpotStatus, StatusStyle>,

}

type DetailRowProps = {
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

export default function ParkingSpotDetails({selected, statusColor}: ParkingDetailsProps) {
    const handleRelease = (spotId: string) => {
        alert(`Releasing spot ${spotId}...`);
    };

    return (

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

    )
}