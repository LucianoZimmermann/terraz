import { EntityProvider } from "./EntityContext";
import { ThemeProvider } from "./ThemeContext";

export const AppProviders = ({ children }: { children: React.ReactNode }) => {
  return (
    <ThemeProvider>
      <EntityProvider>{children}</EntityProvider>
    </ThemeProvider>
  );
};
