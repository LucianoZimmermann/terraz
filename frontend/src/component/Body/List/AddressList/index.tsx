import { useCallback, useEffect, useState } from "react";
import { AddressDTO, PriceFactorDTO } from "../../../../dto";
import AddressService from "../../../../service/AddressService";
import PriceFactorService from "../../../../service/PriceFactorService";

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
    <div>
      <table className="table">
        <thead>
          <tr>
            <th>Rua</th>
            <th>Bairro</th>
            <th>Cidade</th>
            <th>Estado</th>
            <th>CEP</th>
            <th>Fator Preço</th>
          </tr>
        </thead>
        <tbody>
          {addresses.map((addr) => (
            <tr key={addr.id}>
              <td>{addr.street}</td>
              <td>{addr.neighborhood}</td>
              <td>{addr.city}</td>
              <td>{addr.state}</td>
              <td>{addr.cep}</td>
              <td>
                {priceFactors.find((pf) => pf.id === addr.priceFactorId)
                  ?.factor || "N/A"}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
