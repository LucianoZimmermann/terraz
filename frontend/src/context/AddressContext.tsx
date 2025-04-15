import {
  createContext,
  useCallback,
  useContext,
  useEffect,
  useState,
} from "react";
import { AddressDTO } from "../dto";
import AddressService from "../service/AddressService";

interface AddressContextType {
  addresses: AddressDTO[];
  refreshAddresses: () => void;
}

const AddressContext = createContext<AddressContextType | undefined>(undefined);

export const AddressProvider = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  const [addresses, setAddresses] = useState<AddressDTO[]>([]);

  const refreshAddresses = useCallback(() => {
    AddressService.getAllAddresses().then(setAddresses).catch(console.error);
  }, []);

  useEffect(() => {
    refreshAddresses();
  }, [refreshAddresses]);

  return (
    <AddressContext.Provider value={{ addresses, refreshAddresses }}>
      {children}
    </AddressContext.Provider>
  );
};

export const useAddressContext = () => {
  const context = useContext(AddressContext);
  if (!context)
    throw new Error("useAddressContext must be used within AddressProvider");
  return context;
};
