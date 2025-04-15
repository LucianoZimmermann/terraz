import { useCallback, useEffect, useState } from "react";
import { AddressDTO, PriceFactorDTO } from "../../../../dto";
import AddressService from "../../../../service/AddressService";
import PriceFactorService from "../../../../service/PriceFactorService";
import AddressRow from "../AddressRow";

interface AddressListProps {
  onAdd: () => void;
}

export default function AddressList({ onAdd }: AddressListProps) {
  const [addresses, setAddresses] = useState<AddressDTO[]>([]);
  const [priceFactors, setPriceFactors] = useState<PriceFactorDTO[]>([]);

  const loadPriceFactors = useCallback(() => {
    PriceFactorService.getAllPriceFactor()
      .then(setPriceFactors)
      .catch(console.error);
  }, []);

  const loadAddresses = useCallback(() => {
    AddressService.getAllAddresses().then(setAddresses).catch(console.error);
  }, []);

  useEffect(() => {
    loadAddresses();
    loadPriceFactors();
  }, [loadAddresses, loadPriceFactors]);

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
