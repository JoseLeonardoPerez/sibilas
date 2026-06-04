import "./home.css";
import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div className="home">
      <div className="stars" />

      <h1 className="title">Sibilas</h1>

      <p className="subtitle">
        Un sistema de exploración simbólica del cielo natal,
        los arquetipos y los ciclos del destino.
      </p>

      <div className="orbit">

        <Link to="/natal" className="crystal natal">
          Natal
        </Link>

        <Link to="/oracle" className="crystal oracle">
          Oracle
        </Link>

        <Link to="/daily" className="crystal daily">
          Daily
        </Link>

      </div>
    </div>
  );
}