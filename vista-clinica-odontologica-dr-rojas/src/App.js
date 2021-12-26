import { BrowserRouter, Route, Switch } from "react-router-dom";
import Homepage from "./Components/Homepage/Homepage";
import HomeUser from "./Components/HomeUser/HomeUser";
import Loginpage from "./Components/Loginpage/Loginpage";
import Signuppage from "./Components/Signuppage/Signuppage";

function App() {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact path="/" component={Homepage}/>
        <Route path="/login" component={Loginpage}/>
        <Route path="/registro" component={Signuppage}/>
        <Route path="/admin/home" component={HomeUser}/>
      </Switch>
    </BrowserRouter>
  );
}

export default App;
