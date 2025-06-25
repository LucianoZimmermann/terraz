import { useEffect, useState } from "react";
import NeighborhoodService from "../../../../service/NeighborhoodService";
import { NeighborhoodDTO } from "../../../../dto";
import PriceFactorRow from "./PriceFactorRow";

export default function PriceFactorList() {
  const [neighborhoods, setNeighborhoods] = useState<NeighborhoodDTO[]>([]);

  useEffect(() => {
    NeighborhoodService.getAllNeighborhoods()
      .then((data) => {
        console.log("🌐 Dados carregados:", data);
        setNeighborhoods(data);
      })
      .catch((err) => {
        console.error("❌ Erro ao buscar bairros", err);
      });
  }, []);

  if (!neighborhoods.length) {
    return <p style={{ color: "white" }}>Carregando bairros...</p>;
  }

  return (
    <>
      <div className="address-sub">
        <span>Bairro</span>
        <span>Fator Preço</span>
      </div>
      <div>
        {neighborhoods.map((neighborhood) => (
          <PriceFactorRow key={neighborhood.id} neighborhood={neighborhood} />
        ))}
      </div>
    </>
  );
}
