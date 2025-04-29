import { useEffect, useState } from "react";
import { usePriceFactorContext } from "../../../../../context/PriceFactorContext";
import { PriceFactorDTO } from "../../../../../dto";
import PriceFactorService from "../../../../../service/PriceFactorService";
import PriceFactorRow from "../PriceFactorRow";

export default function PriceFactorList() {
  const { factors } = usePriceFactorContext();
  const [priceFactors, setPriceFactors] = useState<PriceFactorDTO[]>([]);

  useEffect(() => {
    PriceFactorService.getAllPriceFactor()
      .then(setPriceFactors)
      .catch(console.error);
  }, []);

  return (
    <>
      <div className="address-sub">
        <span>Bairro</span>
        <span>Fator Preço</span>
      </div>
      <div>
        {factors.map((factors) => (
          <PriceFactorRow
            key={factors.id}
            address={factors}
            priceFactors={priceFactors}
          />
        ))}
      </div>
    </>
  );
}
