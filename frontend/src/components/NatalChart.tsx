import { useEffect, useState } from "react";

export default function NatalChart() {
  const [data, setData] = useState<any>(null);

  useEffect(() => {
    fetch("http://localhost:8080/natal", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        birthDate: "1988-06-05",
        birthTime: "08:00",
        city: "Buenos Aires",
      }),
    })
      .then((res) => res.json())
      .then(setData);
  }, []);

  if (!data) return <p>Cargando carta natal...</p>;

  const radius = 150;
  const center = 200;

  return (
    <svg width="400" height="400" style={{ background: "#111" }}>
      {/* círculo base */}
      <circle
        cx={center}
        cy={center}
        r={radius}
        stroke="white"
        fill="none"
      />

      {/* casas (líneas simples) */}
      {data.houses.map((h: any, i: number) => {
        const angle = (h.degrees * Math.PI) / 180;

        const x1 = center + Math.cos(angle) * radius;
        const y1 = center + Math.sin(angle) * radius;

        return (
          <line
            key={i}
            x1={center}
            y1={center}
            x2={x1}
            y2={y1}
            stroke="gray"
          />
        );
      })}

      {/* planetas */}
      {data.planets.map((p: any, i: number) => {
        const angle = (p.degrees * Math.PI) / 180;

        const x = center + Math.cos(angle) * radius;
        const y = center + Math.sin(angle) * radius;

        return (
          <text
            key={i}
            x={x}
            y={y}
            fill="yellow"
            fontSize="12"
            textAnchor="middle"
          >
            {p.planet}
          </text>
        );
      })}
    </svg>
  );
}