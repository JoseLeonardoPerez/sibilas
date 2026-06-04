import { useState } from "react";
import { Link } from "react-router-dom";
import CityAutocomplete from "../components/CityAutocomplete";

export default function Oracle() {

  const [birthDate, setBirthDate] = useState("");
  const [birthTime, setBirthTime] = useState("");
  const [birthCity, setBirthCity] = useState("");

  const [targetDate, setTargetDate] = useState("");
  const [targetTime, setTargetTime] = useState("");
  const [targetCity, setTargetCity] = useState("");

  const [question, setQuestion] = useState("");

  const [result, setResult] = useState("");
  const [birthLat, setBirthLat] = useState("");
  const [birthLon, setBirthLon] = useState("");

  const [targetLat, setTargetLat] = useState("");
  const [targetLon, setTargetLon] = useState("");

  async function consultOracle() {

    const response = await fetch(
      "http://localhost:8080/oracle",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({

          birthDate,
          birthTime,
          birthCity,
          birthLat,
          birthLon,

          targetDate,
          targetTime,
          targetCity,
          targetLat,
          targetLon,

          question

        })
      }
    );

    const data = await response.text();

    setResult(data);
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
          fontSize: "48px",
          color: "#a855f7",
          marginBottom: "30px"
        }}
      >
        Oracle
      </h1>

      <div
        style={{
          width: "700px",
          background: "#111827",
          padding: "30px",
          borderRadius: "20px",
          border: "1px solid #334155",
          marginBottom: "30px"
        }}
      >

        <h2
          style={{
            color: "#67e8f9"
          }}
        >
          Datos de nacimiento
        </h2>

        <p
          style={{
            opacity: 0.7,
            marginBottom: "20px"
          }}
        >
          Ingresa tu fecha, hora y ciudad de nacimiento.
        </p>

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

        <CityAutocomplete
          onSelect={(cityData) => {

            setBirthCity(
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

      </div>

      <div
        style={{
          width: "700px",
          background: "#111827",
          padding: "30px",
          borderRadius: "20px",
          border: "1px solid #334155",
          marginBottom: "30px"
        }}
      >

        <h2
          style={{
            color: "#c084fc"
          }}
        >
          Momento a consultar
        </h2>

        <p
          style={{
            opacity: 0.7,
            marginBottom: "20px"
          }}
        >
          Ingresa la fecha, hora y ciudad del momento que deseas analizar.
          También puedes dejar la pregunta vacía para obtener una lectura energética general.
        </p>

        <input
          type="date"
          value={targetDate}
          onChange={(e) => setTargetDate(e.target.value)}
          style={inputStyle}
        />

        <input
          type="time"
          value={targetTime}
          onChange={(e) => setTargetTime(e.target.value)}
          style={inputStyle}
        />

        <CityAutocomplete
          onSelect={(cityData) => {

            setTargetCity(
              cityData.display_name
            );

            setTargetLat(cityData.lat);

            setTargetLon(cityData.lon);

            console.log(
              "Ciudad objetivo:",
              cityData
            );
          }}
        />

        <textarea
          placeholder="Pregunta opcional..."
          value={question}
          onChange={(e) => setQuestion(e.target.value)}
          style={{
            ...inputStyle,
            height: "140px",
            resize: "none"
          }}
        />

      </div>

      <button
        onClick={consultOracle}
        style={{
          padding: "16px 32px",
          borderRadius: "16px",
          border: "none",
          background: "#7e22ce",
          color: "white",
          fontSize: "18px",
          cursor: "pointer",
          boxShadow: "0 0 25px rgba(168,85,247,0.5)"
        }}
      >
        Consultar Oráculo
      </button>

      {
        result && (

          <div
            style={{
              width: "700px",
              marginTop: "40px",
              background: "#111827",
              padding: "30px",
              borderRadius: "20px",
              border: "1px solid #334155",
              lineHeight: "1.9",
              whiteSpace: "pre-wrap",
              fontSize: "18px"
            }}
          >
            {result}
          </div>
        )
      }

    </div>
  );
}

const inputStyle = {
  width: "100%",
  padding: "14px",
  marginBottom: "16px",
  borderRadius: "12px",
  border: "1px solid #334155",
  background: "#0f172a",
  color: "white",
  fontSize: "16px"
};