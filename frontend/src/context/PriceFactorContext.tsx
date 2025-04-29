import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import { PriceFactorDTO } from "../dto";
import PriceFactorService from "../service/PriceFactorService";

interface PriceFactorContextType {
  factors: PriceFactorDTO[];
  refreshFactors: () => void;
}

const PriceFactorContext = createContext<PriceFactorContextType | undefined>(
  undefined,
);

export const AddressProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [factors, setFactors] = useState<PriceFactorDTO[]>([]);

  const refreshFactors = useCallback(() => {
    PriceFactorService.getAllPriceFactor()
      .then(setFactors)
      .catch(console.error);
  }, []);

  useEffect(() => {
    refreshFactors();
  }, [refreshFactors]);

  return (
    <PriceFactorContext.Provider value={{ factors, refreshFactors }}>
      {children}
    </PriceFactorContext.Provider>
  );
};

export const usePriceFactorContext = () => {
  const context = useContext(PriceFactorContext);
  if (!context)
    throw new Error(
      "usePriceFactorContext must be used within PriceFactorProvider",
    );
  return context;
};
