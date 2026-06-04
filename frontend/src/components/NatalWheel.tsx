type Props = {
  chart: any;
};

const planetSymbols: any = {
  Sun: "☉",
  Moon: "☽",
  Mercury: "☿",
  Venus: "♀",
  Mars: "♂",
  Jupiter: "♃",
  Saturn: "♄"
};

const zodiacSigns = [

  { symbol: "♈", name: "Aries" },
  { symbol: "♉", name: "Tauro" },
  { symbol: "♊", name: "Geminis" },
  { symbol: "♋", name: "Cancer" },
  { symbol: "♌", name: "Leo" },
  { symbol: "♍", name: "Virgo" },
  { symbol: "♎", name: "Libra" },
  { symbol: "♏", name: "Escorpio" },
  { symbol: "♐", name: "Sagitario" },
  { symbol: "♑", name: "Capricornio" },
  { symbol: "♒", name: "Acuario" },
  { symbol: "♓", name: "Piscis" }

];

export default function NatalWheel({ chart }: Props) {

  const size = 700;
  const center = size / 2;
  const radius = 280;

  function getPosition(
    degrees: number,
    distance: number
  ) {

    const angle =
      (
        degrees - 90
      ) *
      (Math.PI / 180);

    const x =
      center +
      distance * Math.cos(angle);

    const y =
      center +
      distance * Math.sin(angle);

    return { x, y };
  }

  return (
    <svg
      width={size}
      height={size}
      style={{
        marginTop: "40px"
      }}
    >

      {/* círculo exterior */}

      <circle
        cx={center}
        cy={center}
        r={radius}
        stroke="#7e22ce"
        strokeWidth="3"
        fill="transparent"
      />

{/* signos zodiacales */}

{
  zodiacSigns.map(
    (sign, i) => {

      const angle =
        (
          (i * 30)
          - 75
        ) *
        (Math.PI / 180);

      const x =
        center +
        (radius + 35) *
        Math.cos(angle);

      const y =
        center +
        (radius + 35) *
        Math.sin(angle);

      return (

        <g key={sign.name}>

          <text
            x={x}
            y={y}
            textAnchor="middle"
            fill="#67e8f9"
            fontSize="28"
            style={{
              textShadow:
                "0 0 12px #67e8f9"
            }}
          >
            {sign.symbol}
          </text>

        </g>

      );
    }
  )
}

      {/* casas reales */}

      {
        chart.houses.map(
          (house: any) => {

            const angle =
              (house.degrees - 90) *
              (Math.PI / 180);

            const x =
              center +
              radius * Math.cos(angle);

            const y =
              center +
              radius * Math.sin(angle);

            return (

              <g key={house.number}>

                <line
                  x1={center}
                  y1={center}
                  x2={x}
                  y2={y}
                  stroke="#334155"
                  strokeWidth="2"
                />

                <text
                  x={
                    center +
                    (radius - 25) *
                    Math.cos(angle)
                  }
                  y={
                    center +
                    (radius - 25) *
                    Math.sin(angle)
                  }
                  fill="#94a3b8"
                  fontSize="14"
                  textAnchor="middle"
                >
                  {house.number}
                </text>

              </g>

            );
          }
        )
      }

      {/* ascendente */}

      {
        (() => {

          const ascHouse =
            chart.houses[0];

          const angle =
            (ascHouse.degrees - 90) *
            (Math.PI / 180);

          const x =
            center +
            (radius + 20) *
            Math.cos(angle);

          const y =
            center +
            (radius + 20) *
            Math.sin(angle);

          return (

            <g>

              <line
                x1={center}
                y1={center}
                x2={x}
                y2={y}
                stroke="#f59e0b"
                strokeWidth="4"
              />

              <text
                x={
                  center +
                  (radius + 55) *
                  Math.cos(angle)
                }
                y={
                  center +
                  (radius + 55) *
                  Math.sin(angle)
                }
                fill="#f59e0b"
                fontSize="24"
                fontWeight="bold"
                textAnchor="middle"
              >
                ASC
              </text>

            </g>

          );

        })()
      }

{/* aspectos */}

{
  chart.aspects?.map(
    (aspect: any, index: number) => {

      const planet1 =
        chart.planets.find(
          (p: any) =>
            p.planet === aspect.planet1
        );

      const planet2 =
        chart.planets.find(
          (p: any) =>
            p.planet === aspect.planet2
        );

      if (!planet1 || !planet2)
        return null;

      const pos1 =
        getPosition(
          planet1.degrees,
          radius - 40
        );

      const pos2 =
        getPosition(
          planet2.degrees,
          radius - 40
        );

      let color = "#38bdf8";

      if (
        aspect.type === "Square" ||
        aspect.type === "Opposition"
      ) {
        color = "#ef4444";
      }

      if (
        aspect.type === "Trine"
      ) {
        color = "#22c55e";
      }

      return (

        <line
          key={index}
          x1={pos1.x}
          y1={pos1.y}
          x2={pos2.x}
          y2={pos2.y}
          stroke={color}
          strokeWidth="2"
          opacity="0.7"
        />

      );
    }
  )
}
      {/* planetas */}

      {
        chart?.planets?.map(
          (planet: any) => {

            const closePlanets =
              chart.planets.filter(
                (p: any) => {

                  const diff =
                    Math.abs(
                      p.degrees -
                      planet.degrees
                    );

                  return (
                    diff < 8
                  );
                }
              );

            const offset =
              closePlanets.findIndex(
                (p: any) =>
                  p.planet === planet.planet
              ) * 18;

            const pos =
              getPosition(
                planet.degrees,
                radius - 40 - offset
              );

            return (
              <g key={planet.planet}>

              <title>
                {planet.planet}
                {" - "}
                {planet.sign}
                {" - House "}
                {planet.house}
              </title>

                <circle
                  cx={pos.x}
                  cy={pos.y}
                  r="18"
                  fill="#a855f7"
                />

                <text
                  x={pos.x}
                  y={pos.y + 7}
                  textAnchor="middle"
                  fill="white"
                  fontSize="20"
                >
                  {
                    planetSymbols[
                      planet.planet
                    ]
                  }
                </text>

              </g>
            );
          }
        )
      }

    </svg>
  );
}