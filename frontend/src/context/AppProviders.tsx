import { AddressProvider } from "./AddressContext";
import { EntityProvider } from "./EntityContext";
import { ThemeProvider } from "./ThemeContext";

export const AppProviders = ({ children }: { children: React.ReactNode }) => {
  return (
    <ThemeProvider>
      <EntityProvider>
        <AddressProvider>{children}</AddressProvider>
      </EntityProvider>
    </ThemeProvider>
  );
};
