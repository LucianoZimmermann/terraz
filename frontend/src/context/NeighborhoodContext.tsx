import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import { NeighborhoodDTO } from "../dto";
import NeighborhoodService from "../service/NeighborhoodService";

interface NeighborhoodContextType {
  neighborhoods: NeighborhoodDTO[];
  refreshNeighborhoods: () => void;
}

const NeighborhoodContext = createContext<NeighborhoodContextType | undefined>(
  undefined,
);

export const NeighborhoodsProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [neighborhoods, setNeighborhoods] = useState<NeighborhoodDTO[]>([]);

  const refreshNeighborhoods = useCallback(() => {
    NeighborhoodService.getAllNeighborhoods()
      .then(setNeighborhoods)
      .catch(console.error);
  }, []);

  useEffect(() => {
    refreshNeighborhoods();
  }, [refreshNeighborhoods]);

  return (
    <NeighborhoodContext.Provider
      value={{ neighborhoods, refreshNeighborhoods }}
    >
      {children}
    </NeighborhoodContext.Provider>
  );
};

export const useNeighborhoodContext = () => {
  const context = useContext(NeighborhoodContext);
  if (!context)
    throw new Error(
      "useNeighborhoodContext must be used within NeighborhoodsProvider",
    );
  return context;
};
