import React, { useRef, useState } from "react";
import Draggable from "react-draggable";
import { AddressDTO } from "../../../../../dto";
import { useAddressContext } from "../../../../../context/AddressContext";
import AddressService from "../../../../../service/AddressService";
import { XMarkIcon } from "@heroicons/react/24/solid";

interface EditPriceFactorModalProps {
  address: AddressDTO;
  onClose: () => void;
}

export default function EditPriceFactorModal({
  address,
  onClose,
}: EditPriceFactorModalProps) {
  const { refreshAddresses } = useAddressContext();
  const [factor, setFactor] = useState<number>(
    address.priceFactor?.factor ?? 0,
  );

  const nodeRef = useRef<HTMLDivElement>(null!);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFactor(Number(e.target.value));
  };

  const handleSave = async () => {
    try {
      // Atualiza apenas o fator de preço
      const updated: AddressDTO = {
        ...address,
        priceFactorDTO: { id: address.priceFactor?.id, factor },
      } as AddressDTO;

      await AddressService.updateAddress(updated.id!, updated);
      refreshAddresses();
      onClose();
    } catch (error) {
      console.error(error);
      alert("Erro ao atualizar fator de preço.");
    }
  };

  return (
    <div className="edit-address-modal-overlay">
      <Draggable handle=".modal-header" nodeRef={nodeRef}>
        <div ref={nodeRef} className="edit-address-modal">
          <div className="flex justify-between modal-header font-bold text-lg mb-4 cursor-move">
            <h2>Editar Fator de Preço</h2>
            <XMarkIcon
              onClick={onClose}
              className="w-5 h-5 text-gray-500 dark:text-gray-300 hover:text-red-500 cursor-pointer transition"
            />
          </div>

          <div className="flex flex-col gap-2">
            <label htmlFor="factor" className="font-medium">
              Fator:
            </label>
            <input
              id="factor"
              name="factor"
              type="number"
              step="0.01"
              value={factor}
              onChange={handleChange}
              className="border p-2 rounded"
            />
          </div>

          <div className="flex justify-end mt-4 gap-2">
            <button className="btn-cancel" onClick={onClose}>
              Cancelar
            </button>
            <button className="btn-save" onClick={handleSave}>
              Salvar
            </button>
          </div>
        </div>
      </Draggable>
    </div>
  );
}
