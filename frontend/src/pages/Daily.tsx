import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import CityAutocomplete from "../components/CityAutocomplete";

export default function Daily() {

  const [birthDate, setBirthDate] = useState("");

  const [birthTime, setBirthTime] = useState("");

  const [city, setCity] = useState("");

  const [birthLat, setBirthLat] = useState("");
  const [birthLon, setBirthLon] = useState("");

  const [reading, setReading] = useState("");

  const [loading, setLoading] = useState(false);

  const [currentLatitude, setCurrentLatitude] =
    useState<number | null>(null);

  const [currentLongitude, setCurrentLongitude] =
    useState<number | null>(null);

    useEffect(() => {

      navigator.geolocation.getCurrentPosition(

        (position) => {

          setCurrentLatitude(
            position.coords.latitude
          );

          setCurrentLongitude(
            position.coords.longitude
          );

          console.log(
            "Ubicación actual:",
            position.coords.latitude,
            position.coords.longitude
          );
        },

        (error) => {

          console.error(
            "Error obteniendo ubicación:",
            error
          );
        }
      );

    }, []);

  async function generateDailyReading() {

    try {

      setLoading(true);

      const response = await fetch(
        "http://localhost:8080/daily",
        {
          method: "POST",

          headers: {
            "Content-Type": "application/json"
          },

          body: JSON.stringify({
            birthDate,
            birthTime,
            city,

            birthLat,
            birthLon,

            currentLatitude,
            currentLongitude
          })
        }
      );

      const data = await response.json();

      console.log(data);

      setReading(data.reading);

    } catch (error) {

      console.error(error);

      setReading(
        "Error generando lectura diaria."
      );

    } finally {

      setLoading(false);
    }
  }

  return (

    <div
      style={{
        minHeight: "100vh",
        background: "#020617",
        color: "white",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        padding: "40px",
        fontFamily: "Arial"
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
          fontSize: "52px",
          color: "#a855f7",
          textShadow: "0 0 25px #a855f7",
          marginBottom: "10px"
        }}
      >
        Daily Oracle
      </h1>

      <p
        style={{
          opacity: 0.7,
          marginBottom: "40px",
          fontSize: "18px"
        }}
      >
        Tu energía astrológica personalizada del día
      </p>

      <p
        style={{
          opacity: 0.6,
          fontSize: "15px",
          maxWidth: "500px",
          textAlign: "center",
          lineHeight: "1.7",
          marginBottom: "35px"
        }}
      >
        Ingresa tu fecha, hora y ciudad de nacimiento.
        La app combinará tu carta natal con los
        tránsitos astrológicos actuales para generar
        tu lectura energética personalizada del día.
      </p>

      <div
        style={{
          display: "flex",
          flexDirection: "column",
          gap: "16px",
          width: "320px"
        }}
      >

        <input
          type="date"
          value={birthDate}
          onChange={(e) =>
            setBirthDate(e.target.value)
          }
          style={inputStyle}
        />

        <input
          type="time"
          value={birthTime}
          onChange={(e) =>
            setBirthTime(e.target.value)
          }
          style={inputStyle}
        />

        <CityAutocomplete
          onSelect={(cityData) => {

            setCity(
              cityData.display_name
            );

            setBirthLat(cityData.lat);

            setBirthLon(cityData.lon);

            console.log(
              "Ciudad natal:",
              cityData
            );
          }}
        />

        <button
          onClick={generateDailyReading}
          style={buttonStyle}
        >
          {
            loading
              ? "Generando..."
              : "Generar Lectura"
          }
        </button>

      </div>

      {
        reading && (

          <div
            style={{
              width: "900px",
              marginTop: "50px",
              background: "#0f172a",
              border: "1px solid #334155",
              borderRadius: "24px",
              padding: "40px",
              boxShadow:
                "0 0 40px rgba(168,85,247,0.2)"
            }}
          >

            <h2
              style={{
                color: "#67e8f9",
                marginBottom: "25px",
                fontSize: "34px"
              }}
            >
              Interpretación
            </h2>

            <div
              style={{
                lineHeight: "2",
                fontSize: "20px",
                whiteSpace: "pre-wrap",
                opacity: 0.92
              }}
            >
              {reading}
            </div>

          </div>
        )
      }

    </div>
  );
}

const inputStyle = {

  width: "100%",

  padding: "14px",

  borderRadius: "14px",

  border: "1px solid #334155",

  background: "#0f172a",

  color: "white",

  fontSize: "16px"
};

const buttonStyle = {

  padding: "16px",

  borderRadius: "16px",

  border: "none",

  background: "#7e22ce",

  color: "white",

  fontSize: "18px",

  cursor: "pointer",

  boxShadow:
    "0 0 25px rgba(168,85,247,0.5)"
};