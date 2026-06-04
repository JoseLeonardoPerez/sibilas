type Props = {
  sign: string;
};

const summaries: any = {

  Aries: {
    title: "Pionero Intrépido",
    text:
      "Tu energía está impulsada por la acción, el liderazgo y el deseo de avanzar sin miedo."
  },

  Tauro: {
    title: "Alma Estable",
    text:
      "Buscas estabilidad, placer y seguridad emocional en todo lo que construyes."
  },

  Geminis: {
    title: "Mente Curiosa",
    text:
      "Tu mente vive en constante movimiento, explorando ideas, personas y nuevas experiencias."
  },

  Cáncer: {
    title: "Guardián Emocional",
    text:
      "Tu sensibilidad emocional y conexión con los demás son el centro de tu energía."
  },

  Leo: {
    title: "Creador Radiante",
    text:
      "Tienes una presencia fuerte, creativa y magnética que naturalmente atrae atención."
  },

  Virgo: {
    title: "Arquitecto Sabio",
    text:
      "Tu energía busca orden, mejora constante y comprensión profunda de los detalles."
  },

  Libra: {
    title: "Buscador de Armonía",
    text:
      "Buscas equilibrio, belleza y conexiones humanas auténticas."
  },

  Escorpio: {
    title: "Transformador Místico",
    text:
      "Tu intensidad emocional y profundidad interna generan transformación constante."
  },

  Sagitario: {
    title: "Explorador Cósmico",
    text:
      "Tu espíritu busca libertad, aventura y expansión del conocimiento."
  },

  Capricornio: {
    title: "Constructor Maestro",
    text:
      "Tu energía se enfoca en disciplina, ambición y construcción de grandes objetivos."
  },

  Acuario: {
    title: "Mente Visionaria",
    text:
      "Piensas diferente, buscas innovación y deseas transformar el futuro."
  },

  Piscis: {
    title: "Soñador Intuitivo",
    text:
      "Tu energía está profundamente conectada con la intuición, creatividad y sensibilidad espiritual."
  }

};

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

export default function CosmicSummary({
  sign
}: Props) {

  const summary =
    summaries[sign];

  if (!summary) {

    return (
      <div
        style={{
          marginTop: "40px",
          color: "white"
        }}
      >
        No existe resumen para el signo: {sign}
      </div>
    );
  }

  return (

    <div
      style={{
        width: "700px",
        background:
          "linear-gradient(180deg,#0f172a,#111827)",
        borderRadius: "28px",
        padding: "40px",
        marginTop: "40px",
        border: "1px solid #334155",
        boxShadow:
          "0 0 40px rgba(168,85,247,0.15)",
        textAlign: "center"
      }}
    >

      <div
        style={{
          fontSize: "90px",
          color: "#a855f7",
          textShadow:
            "0 0 30px #a855f7"
        }}
      >
        {
          zodiacSymbols[sign]
        }
      </div>

      <h1
        style={{
          fontSize: "48px",
          marginBottom: "10px"
        }}
      >
        {sign}
      </h1>

      <h2
        style={{
          color: "#67e8f9",
          marginBottom: "30px",
          fontWeight: "normal"
        }}
      >
        {summary.title}
      </h2>

      <p
        style={{
          fontSize: "20px",
          lineHeight: "1.8",
          opacity: 0.85
        }}
      >
        {summary.text}
      </p>

    </div>
  );
}