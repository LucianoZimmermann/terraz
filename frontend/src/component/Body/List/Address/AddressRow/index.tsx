import { AddressDTO, PriceFactorDTO } from "../../../../../dto";
import { useModal } from "../../../../../hook/useModal";
import ItemActions from "../../ItemActions";
import EditAddressModal from "../EditAddressModal";

interface AddressCardProps {
  address: AddressDTO;
  priceFactors: PriceFactorDTO[];
}

export default function AddressRow({
  address,
  priceFactors,
}: AddressCardProps) {
  const { isOpen, open, close } = useModal();
  const priceFactor = priceFactors.find(
    (pf) => pf.id === address.priceFactor.factor,
  );

  return (
    <>
      <div className="address-item">
        <p>
          {address.street}
          {address.number ? `, ${address.number}` : ""}
        </p>
        <p>{address.neighborhood.priceFactor.factor}</p>
        <p>{address.city}</p>
        <p>{address.state}</p>
        <p>{address.cep}</p>
        <p>{priceFactor?.factor ?? "N/A"}</p>
        <ItemActions id={address.id!} entityType="addresses" onEdit={open} />
      </div>

      {isOpen && <EditAddressModal address={address} onClose={close} />}
    </>
  );
}
