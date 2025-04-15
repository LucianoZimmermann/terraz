import { AddressDTO, PriceFactorDTO } from "../../../../dto";
import ItemActions from "../ItemActions";

interface AddressCardProps {
  address: AddressDTO;
  priceFactors: PriceFactorDTO[];
}

export default function AddressCard({
  address,
  priceFactors,
}: AddressCardProps) {
  const priceFactor = priceFactors.find(
    (pf) => pf.id === address.priceFactorId,
  );

  return (
    <div className="address-item">
      <p>
        {address.street}
        {address.number ? `, ${address.number}` : ""}
      </p>
      <p>{address.neighborhood}</p>
      <p>{address.city}</p>
      <p>{address.state}</p>
      <p>{address.cep}</p>
      <p>{priceFactor?.factor ?? "N/A"}</p>
      <ItemActions />
    </div>
  );
}
