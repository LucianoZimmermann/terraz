import { ThemeProvider } from "./ThemeContext";

export const AppProviders = ({ children }: { children: React.ReactNode }) => {
  return <ThemeProvider>{children}</ThemeProvider>;
};
