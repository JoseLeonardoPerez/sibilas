import { useEffect, useState } from "react";

type Props = {
  onSelect: (city: any) => void;
};

export default function CityAutocomplete({
  onSelect
}: Props) {

  console.log("CITY COMPONENT RENDER");

  const [query, setQuery] = useState("");

  const [results, setResults] = useState<any[]>([]);

  const [showResults, setShowResults] =
    useState(false);

  useEffect(() => {

    console.log("QUERY:", query);

    if (query.length < 3) {

      setResults([]);

      return;
    }

    const timeout = setTimeout(() => {

      async function searchCities() {

        try {

          console.log(
            "BUSCANDO CIUDADES..."
          );

          const response = await fetch(
            `http://localhost:8080/cities?query=${query}`
          );

          const data = await response.json();

          console.log("RESULTADOS:", data);

          setResults(data);

        } catch (error) {

          console.error(error);
        }
      }

      searchCities();

    }, 700);

    return () => clearTimeout(timeout);

  }, [query]);
  return (

    <div
      style={{
        position: "relative",
        width: "320px"
      }}
    >

      <input
        type="text"
        placeholder="Ciudad"

        value={query}

        onChange={(e) => {

          console.log(
            "ESCRIBIENDO:",
            e.target.value
          );

          setQuery(e.target.value);

          setShowResults(true);
        }}

        style={{
          width: "100%",
          padding: "14px",
          borderRadius: "12px",
          border: "1px solid #334155",
          background: "#0f172a",
          color: "white",
          fontSize: "16px"
        }}
      />

      {
        showResults &&
        results.length > 0 && (

          <div
            style={{
              position: "absolute",
              top: "105%",
              left: 0,
              right: 0,
              background: "#111827",
              border: "1px solid #334155",
              borderRadius: "12px",
              overflow: "hidden",
              zIndex: 99999
            }}
          >

            {
              results.map((city, index) => (

                <div
                  key={index}

                  onClick={() => {

                    onSelect(city);

                    setQuery(
                      city.display_name
                    );

                    setShowResults(false);
                  }}

                  style={{
                    padding: "14px",
                    cursor: "pointer",
                    borderBottom:
                      "1px solid #1e293b"
                  }}
                >
                  {city.display_name}
                </div>
              ))
            }

          </div>
        )
      }

    </div>
  );
}