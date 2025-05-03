// src/components/neighborhood/NeighborhoodForm.tsx
import { useState, FormEvent } from "react";
import { NeighborhoodDTO, PriceFactorDTO } from "../../../../dto";
import NeighborhoodService from "../../../../service/NeighborhoodService";
import Button from "../../../Button";
import { useNeighborhoodContext } from "../../../../context/NeighborhoodContext";

interface NeighborhoodFormProps {
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

export default function PriceFactorForm({ onCancel }: NeighborhoodFormProps) {
  const { refreshNeighborhoods } = useNeighborhoodContext();
  const [formData, setFormData] =
    useState<NeighborhoodDTO>(initialNeighborhood);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const handleSave = async (e: FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      await NeighborhoodService.createNeighborhood(formData);
      refreshNeighborhoods();
      onCancel();
    } catch (err: any) {
      console.error(err);
      setError(err.message || "Erro ao cadastrar bairro.");
    } finally {
      setLoading(false);
    }
  };

  const handleNameChange = (name: string) => {
    setFormData((prev) => ({ ...prev, name }));
  };

  const handleFactorChange = (factor: number) => {
    setFormData((prev) => ({
      ...prev,
      priceFactor: { ...prev.priceFactor, factor },
    }));
  };

  return (
    <form onSubmit={handleSave} className="entity-form">
      {error && <p className="text-red-500">{error}</p>}

      <div>
        <label className="block text-sm font-medium mb-1">Bairro</label>
        <input
          type="text"
          value={formData.name}
          onChange={(e) => handleNameChange(e.target.value)}
          className="w-full border rounded px-3 py-2"
          required
        />
      </div>

      <div>
        <label className="block text-sm font-medium mb-1">Fator de Preço</label>
        <input
          type="number"
          step="0.01"
          value={formData.priceFactor.factor}
          onChange={(e) => handleFactorChange(Number(e.target.value))}
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
