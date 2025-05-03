import { AddressProvider } from "./AddressContext";
import { EntityProvider } from "./EntityContext";
import { NeighborhoodsProvider } from "./NeighborhoodContext";
import { PriceFactorProvider } from "./PriceFactorContext";
import { ThemeProvider } from "./ThemeContext";

export const AppProviders = ({ children }: { children: React.ReactNode }) => {
  return (
    <ThemeProvider>
      <EntityProvider>
        <AddressProvider>
          <NeighborhoodsProvider>
            <PriceFactorProvider>{children}</PriceFactorProvider>
          </NeighborhoodsProvider>
        </AddressProvider>
      </EntityProvider>
    </ThemeProvider>
  );
};
