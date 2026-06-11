import { BrowserRouter, Routes, Route } from "react-router-dom";

import Home from "./pages/Home";
import Natal from "./pages/Natal";
import Oracle from "./pages/Oracle";
import Daily from "./pages/Daily";

function App() {
  return (
    <BrowserRouter basename="/sibilas">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/natal" element={<Natal />} />
        <Route path="/oracle" element={<Oracle />} />
        <Route path="/daily" element={<Daily />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;