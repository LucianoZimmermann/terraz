import { useEffect, useState } from "react";
import { NeighborhoodDTO } from "../../../../../dto";
import PriceFactorRow from "../PriceFactorRow";
import NeighborhoodService from "../../../../../service/NeighborhoodService";

export default function PriceFactorList() {
  const [neighborhoods, setNeighborhoods] = useState<NeighborhoodDTO[]>([]);

  useEffect(() => {
    NeighborhoodService.getAllNeighborhoods().then((data) => {
      setNeighborhoods(data);
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
