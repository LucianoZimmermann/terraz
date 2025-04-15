import { useAddressContext } from "../../../../../context/AddressContext";
import { PriceFactorDTO } from "../../../../../dto";
import PriceFactorService from "../../../../../service/PriceFactorService";
import AddressRow from "../AddressRow";
import { useEffect, useState } from "react";

export default function AddressList() {
  const { addresses } = useAddressContext();
  const [priceFactors, setPriceFactors] = useState<PriceFactorDTO[]>([]);

  useEffect(() => {
    PriceFactorService.getAllPriceFactor()
      .then(setPriceFactors)
      .catch(console.error);
  }, []);

  return (
    <>
      <div className="address-sub">
        <span>Rua</span>
        <span>Bairro</span>
        <span>Cidade</span>
        <span>Estado</span>
        <span>CEP</span>
        <span>Fator Preço</span>
      </div>
      <div>
        {addresses.map((address) => (
          <AddressRow
            key={address.id}
            address={address}
            priceFactors={priceFactors}
          />
        ))}
      </div>
    </>
  );
}
