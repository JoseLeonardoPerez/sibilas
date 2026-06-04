import { useState } from "react";
import { Link } from "react-router-dom";
import NatalWheel from "../components/NatalWheel";
import CosmicSummary from "../components/CosmicSummary";
import CityAutocomplete from "../components/CityAutocomplete";

const zodiacSymbols: any = {
  Aries: "♈",
  Tauro: "♉",
  Geminis: "♊",
  Cáncer: "♋",
  Leo: "♌",
  Virgo: "♍",
  Libra: "♎",
  Escorpio: "♏",
  Sagitario: "♐",
  Capricornio: "♑",
  Acuario: "♒",
  Piscis: "♓"
};

export default function Natal() {

  const [birthDate, setBirthDate] = useState("");
  const [birthTime, setBirthTime] = useState("");
  const [city, setCity] = useState("");

  const [chart, setChart] = useState<any>(null);

  const sunPlanet =
    chart?.planets?.find(
      (p: any) => p.planet === "Sol"
    ) || null;

  async function calculateChart() {

    const response = await fetch(
      "http://localhost:8080/natal",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          birthDate,
          birthTime,
          city
        })
      }
    );

    console.log(response);
    console.log(response.status);

    const data = await response.json();

    console.log(data);

    setChart(data);
  }

  return (
    <div
      style={{
        minHeight: "100vh",
        background: "#020617",
        color: "white",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column",
        gap: "20px",
        fontFamily: "Arial",
        padding: "40px"
      }}
    >

      <Link
        to="/"
        style={{
          position: "absolute",
          top: "30px",
          left: "30px",
          textDecoration: "none",
          color: "#67e8f9",
          fontSize: "18px",
          textShadow: "0 0 10px #67e8f9"
        }}
      >
        ← Volver al Inicio
      </Link>

      <h1
        style={{
          color: "#a855f7",
          textShadow: "0 0 20px #a855f7",
          fontSize: "48px",
          marginBottom: "20px"
        }}
      >
        Carta Natal
      </h1>

      <input
        type="date"
        value={birthDate}
        onChange={(e) => setBirthDate(e.target.value)}
        style={inputStyle}
      />

      <input
        type="time"
        value={birthTime}
        onChange={(e) => setBirthTime(e.target.value)}
        style={inputStyle}
      />

      <h1>COMPONENTE TEST</h1>

      <CityAutocomplete
        onSelect={(cityData) => {

          console.log(cityData);

          setCity(cityData.display_name);

        }}
      />

      <button
        style={buttonStyle}
        onClick={calculateChart}
      >
        Calcular Carta
      </button>

      {
        chart && (
          <>
            <CosmicSummary
              sign={sunPlanet?.sign}
            />

            <div
              style={{
                marginTop: "40px",
                width: "600px",
                background: "#0f172a",
                padding: "30px",
                borderRadius: "20px",
                border: "1px solid #334155",
                boxShadow: "0 0 30px rgba(168,85,247,0.2)"
              }}
            >

              <div
                style={{
                  textAlign: "center",
                  marginBottom: "50px",
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                  gap: "12px"
                }}
              >

                <div
                  style={{
                    fontSize: "90px",
                    lineHeight: "1",
                    color: "#a855f7",
                    textShadow: "0 0 25px #a855f7",
                    marginBottom: "10px"
                  }}
                >
                  {
                    sunPlanet?.sign
                      ? zodiacSymbols[sunPlanet.sign]
                      : "?"
                  }
                </div>

                <NatalWheel chart={chart} />

                <h1
                  style={{
                    fontSize: "42px",
                    margin: 0,
                    marginBottom: "6px"
                  }}
                >
                  {sunPlanet?.sign}
                </h1>

                <p
                  style={{
                    opacity: 0.7,
                    margin: 0,
                    fontSize: "18px"
                  }}
                >
                  Signo Solar
                </p>

              </div>

              <h2
                style={{
                  color: "#c084fc"
                }}
              >
                Ascendente: {chart.ascendant}
              </h2>

              <h3
                style={{
                  marginTop: "30px"
                }}
              >
                Planetas
              </h3>

              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  gap: "14px",
                  marginTop: "20px"
                }}
              >

                {
                  chart?.planets?.map((planet: any) => (
                    <div
                      key={planet.planet}
                      style={{
                        background: "#1e293b",
                        padding: "16px",
                        borderRadius: "14px",
                        border: "1px solid #334155"
                      }}
                    >

                      <div
                        style={{
                          fontSize: "20px",
                          fontWeight: "bold"
                        }}
                      >
                        {planet.planet}
                      </div>

                      <div
                        style={{
                          opacity: 0.85,
                          marginTop: "6px"
                        }}
                      >
                        {planet.sign}
                      </div>

                      <div
                        style={{
                          opacity: 0.7,
                          marginTop: "4px"
                        }}
                      >
                        Casa {planet.house}
                      </div>

                    </div>
                  ))
                }

              </div>

              <h3
                style={{
                  marginTop: "40px",
                  color: "#c084fc"
                }}
              >
                Interpretación Simbólica
              </h3>

              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  gap: "12px",
                  marginTop: "20px"
                }}
              >
                {
                  chart?.symbolicContext?.map(
                    (item: string, index: number) => (
                      <div
                        key={index}
                        style={{
                          background: "#1e293b",
                          padding: "14px",
                          borderRadius: "12px",
                          border: "1px solid #334155",
                          lineHeight: "1.6",
                          opacity: 0.9
                        }}
                      >
                        {item}
                      </div>
                    )
                  )
                }
              </div>

            </div>

          </>
        )
      }

     {
       chart?.aiInterpretation && (

         <>
           <h3
             style={{
               marginTop: "50px",
               color: "#67e8f9"
             }}
           >
             Interpretación IA
           </h3>

           <div
             style={{
               background: "#111827",
               padding: "24px",
               borderRadius: "18px",
               border: "1px solid #334155",
               marginTop: "20px",
               lineHeight: "1.9",
               fontSize: "18px",
               whiteSpace: "pre-wrap",
               color: "#e2e8f0",
               maxWidth: "900px"
             }}
           >
             {chart.aiInterpretation}
           </div>
         </>
       )
     }


    </div>
  );
}

const inputStyle = {
  width: "300px",
  padding: "14px",
  borderRadius: "12px",
  border: "1px solid #334155",
  background: "#0f172a",
  color: "white",
  fontSize: "16px"
};

const buttonStyle = {
  padding: "14px 28px",
  borderRadius: "14px",
  border: "none",
  background: "#7e22ce",
  color: "white",
  fontSize: "16px",
  cursor: "pointer",
  boxShadow: "0 0 20px rgba(168,85,247,0.6)"
};