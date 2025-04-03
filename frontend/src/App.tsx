import Header from "./component/Header";
import { AppProviders } from "./context/AppProviders";
import Body from "./component/Body";

function App() {
  return (
    <>
      <AppProviders>
        <Header />
        <Body />
      </AppProviders>
    </>
  );
}

export default App;
