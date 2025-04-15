import Draggable from "react-draggable";
import { useRef, useState } from "react";
import { AddressDTO } from "../../../../../dto";
import { useAddressContext } from "../../../../../context/AddressContext";
import AddressService from "../../../../../service/AddressService";
import { XMarkIcon } from "@heroicons/react/24/solid";

interface EditAddressModalProps {
  address: AddressDTO;
  onClose: () => void;
}

export default function EditAddressModal({
  address,
  onClose,
}: EditAddressModalProps) {
  const { refreshAddresses } = useAddressContext();
  const [formData, setFormData] = useState<AddressDTO>(address);

  const nodeRef = useRef<HTMLDivElement>(null!);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value ?? "" }));
  };

  const handleSave = async () => {
    try {
      await AddressService.updateAddress(formData.id!, formData);
      refreshAddresses();
      onClose();
    } catch (error) {
      console.error(error);
      alert("Erro ao atualizar endereço.");
    }
  };

  return (
    <div className="edit-address-modal-overlay">
      <Draggable handle=".modal-header" nodeRef={nodeRef}>
        <div ref={nodeRef} className="edit-address-modal">
          <div className="flex justify-between modal-header font-bold text-lg mb-4 cursor-move">
            <h2>Editar Endereço</h2>
            <XMarkIcon
              onClick={onClose}
              className="w-5 h-5 text-gray-500 dark:text-gray-300 hover:text-red-500 cursor-pointer transition"
            />
          </div>

          <div className="flex flex-col gap-2">
            <input
              name="street"
              value={formData.street ?? ""}
              onChange={handleChange}
              placeholder="Rua"
              className="border p-2 rounded"
            />
            <input
              name="number"
              value={formData.number ?? ""}
              onChange={handleChange}
              placeholder="Número"
              className="border p-2 rounded"
            />
            <input
              name="neighborhood"
              value={formData.neighborhood ?? ""}
              onChange={handleChange}
              placeholder="Bairro"
              className="border p-2 rounded"
            />
            <input
              name="city"
              value={formData.city ?? ""}
              onChange={handleChange}
              placeholder="Cidade"
              className="border p-2 rounded"
            />
            <input
              name="state"
              value={formData.state ?? ""}
              onChange={handleChange}
              placeholder="Estado"
              className="border p-2 rounded"
            />
            <input
              name="cep"
              value={formData.cep ?? ""}
              onChange={handleChange}
              placeholder="CEP"
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
