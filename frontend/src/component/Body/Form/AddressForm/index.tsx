import { useState, FormEvent } from "react";
import Button from "../../../Button";
import AddressService from "../../../../service/AddressService";
import { AddressDTO, NeighborhoodDTO, PriceFactorDTO } from "../../../../dto";
import { useAddressContext } from "../../../../context/AddressContext";

interface AddressFormProps {
  onCancel: () => void;
}

const initialPriceFactor: PriceFactorDTO = {
  id: undefined,
  factor: 0,
};

const initialNeighborhood: NeighborhoodDTO = {
  id: undefined,
  name: "",
  priceFactor: initialPriceFactor,
};

const initialAddress: AddressDTO = {
  id: undefined,
  street: "",
  number: "",
  neighborhood: initialNeighborhood,
  city: "",
  state: "",
  cep: "",
};

export default function AddressForm({ onCancel }: AddressFormProps) {
  const { refreshAddresses } = useAddressContext();
  const [formData, setFormData] = useState<AddressDTO>(initialAddress);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSave = async (e: FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      await AddressService.createAddress(formData);
      refreshAddresses();
      onCancel();
    } catch (err: any) {
      console.error(err);
      setError(err.message || "Erro ao cadastrar endereço.");
    } finally {
      setLoading(false);
    }
  };

  const handleChange = <K extends keyof AddressDTO>(
    key: K,
    value: AddressDTO[K],
  ) => {
    setFormData((prev) => ({ ...prev, [key]: value }));
  };

  const handleNeighborhoodNameChange = (name: string) => {
    setFormData((prev) => ({
      ...prev,
      neighborhood: { ...prev.neighborhood, name },
    }));
  };

  const handlePriceFactorIdChange = (id: number) => {
    setFormData((prev) => ({
      ...prev,
      neighborhood: {
        ...prev.neighborhood,
        priceFactor: { ...prev.neighborhood.priceFactor, id },
      },
    }));
  };

  return (
    <form onSubmit={handleSave} className="entity-form">
      {error && <p className="text-red-500">{error}</p>}

      <div>
        <label htmlFor="street" className="block text-sm font-medium mb-1">
          Rua
        </label>
        <input
          value={formData.street}
          onChange={(e) => handleChange("street", e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Número</label>
        <input
          id="number"
          type="text"
          value={formData.number}
          onChange={(e) => handleChange("number", e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Bairro</label>
        <input
          value={formData.neighborhood.name}
          onChange={(e) => handleNeighborhoodNameChange(e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Cidade</label>
        <input
          id="city"
          type="text"
          value={formData.city}
          onChange={(e) => handleChange("city", e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Estado</label>
        <input
          id="state"
          type="text"
          value={formData.state}
          onChange={(e) => handleChange("state", e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">CEP</label>
        <input
          id="cep"
          type="text"
          value={formData.cep}
          onChange={(e) => handleChange("cep", e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">
          Price Factor ID
        </label>
        <input
          id="priceFactorId"
          type="number"
          value={formData.neighborhood.priceFactor.id}
          onChange={(e) => handlePriceFactorIdChange(Number(e.target.value))}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div className="flex items-center space-x-2 pt-4">
        <Button content="Salvar" type="submit" disabled={loading} />
        <Button
          content="Cancelar"
          type="button"
          onClick={onCancel}
          variant="secondary"
        />
      </div>
    </form>
  );
}
