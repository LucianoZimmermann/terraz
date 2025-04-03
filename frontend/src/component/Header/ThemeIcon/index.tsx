import { MoonIcon, SunIcon } from "@heroicons/react/24/solid";
import { useTheme } from "../../../context/ThemeContext";

export default function ThemeIcon() {
  const { theme, toggleTheme } = useTheme();

  return (
    <button onClick={toggleTheme} className="toggleThemeIcon">
      {theme === "dark" ? (
        <SunIcon className="w-6 h-6" />
      ) : (
        <MoonIcon className="w-6 h-6" />
      )}
    </button>
  );
}
