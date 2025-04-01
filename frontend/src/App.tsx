import Header from "./components/Header";
import HomeSelector from "./components/HomeSelector";
import { AppProviders } from "./contexts/AppProviders";

function App() {
  return (
    <>
      <AppProviders>
        <Header />
        <HomeSelector />
      </AppProviders>
    </>
  );
}

export default App;
