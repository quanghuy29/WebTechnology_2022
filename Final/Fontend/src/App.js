import React, { Component } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Layout from "./component/Layout";
import NotFound from "./pages/NotFound/NotFound";
import RequireAuth from "./protectAuth/RequireAuth/RequireAuth";
import Login from "./pages/login";
import Room from "./pages/RoomManager/room";
import Students from "./pages/Students/Students";
import SignIn from "./pages/Login/Login";
import AccountManager from "./pages/AccountManager/AccountManager";

const ROLES = {
  status: 200,
  manager: 2,
  accountant: 3,
  user: 4,
};

class App extends Component {
  render() {
    return (
      <div className="App">
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route path="login" element={<SignIn />} />

            <Route element={<RequireAuth allowedRoles={ROLES.status} />}>
              <Route path="account-manager" element={<AccountManager />} />
              <Route path="room" element={<Room />} />
            </Route>

            <Route path="*" element={<NotFound />} />
          </Route>
        </Routes>
        {/* <Router>
          <Routes>
            <Route exact path="/" component={Login} />
            <Route path="/room" component={Room} />
            <Route path="/students" component={Students} />
            <Route path="/signin" component={SignIn} />
            <Route path="/account-manager" component={AccountManager} />
          </Routes>
        </Router> */}
      </div>
    );
  }
>>>>>>> WEB_HUY
}

export default App;
