import { AddressDTO } from "../../../../../dto";
import { useModal } from "../../../../../hook/useModal";
import ItemActions from "../../ItemActions";

interface PriceFactorRowProps {
  address: AddressDTO;
}

export default function PriceFactorRow({ address }: PriceFactorRowProps) {
  const { isOpen, open, close } = useModal();

  const factor = address.priceFactor?.factor;

  return (
    <>
      <div className="address-item">
        <p>
          {address.street}
          {address.number ? `, ${address.number}` : ""}
        </p>
        <p>{address.neighborhood.name}</p>
        <p>{address.city}</p>
        <p>{address.state}</p>
        <p>{address.cep}</p>
        <p>{factor ?? "N/A"}</p>
        <ItemActions id={address.id!} entityType="addresses" onEdit={open} />
      </div>

      {isOpen && <EditPriceFactorModal address={address} onClose={close} />}
    </>
  );
}
