import Logo from "./Logo";
import SearchBar from "./SearchBar";
import ThemeIcon from "./ThemeIcon";

export default function Header() {
  return (
    <header className="header">
      <Logo />
      <SearchBar />
      <ThemeIcon />
    </header>
  );
}
