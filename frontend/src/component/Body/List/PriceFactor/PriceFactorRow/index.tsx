import { AddressDTO, NeighborhoodDTO } from "../../../../../dto";
import { useModal } from "../../../../../hook/useModal";
import ItemActions from "../../ItemActions";
import EditPriceFactorModal from "../EditPriceFactorModal";

interface PriceFactorRowProps {
  neighborhood: NeighborhoodDTO;
}

export default function PriceFactorRow({ neighborhood }: PriceFactorRowProps) {
  const { isOpen, open, close } = useModal();

  const factor = neighborhood.priceFactor.factor;

  return (
    <>
      <div className="address-item">
        <p>{neighborhood.name}</p>
        <p>{factor ?? "N/A"}</p>
        <ItemActions
          id={neighborhood.id!}
          entityType="addresses"
          onEdit={open}
        />
      </div>

      {isOpen && (
        <EditPriceFactorModal neighborhood={neighborhood} onClose={close} />
      )}
    </>
  );
}
