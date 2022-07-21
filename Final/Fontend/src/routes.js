import React from "react";
import { Redirect, Switch, Route, Router } from "react-router-dom";
import RouteGuard from "./component/RouteGuard";

//history
import { history } from './helpers/history';

//pages
import LoginPage from "./pages/login";
import Dashboard from "./pages/dashboard";
import Index from "./pages/index";
import AddPage from "./pages/add";
import EditPage from "./pages/edit";
import Register from "./pages/register";
import NotFound from "./pages/notfound";
import FileUploadPage from "./pages/fileupload";
import Room from "./pages/RoomManager/room";
import Bill from "./pages/Bill/bill";

function Routes() {
    return (
        <Router history={history}>
            <Switch>
                <Route exact path="/" component={LoginPage} />
                <RouteGuard path="/dashboard" component={Dashboard} />
                <RouteGuard path="/index" component={Index} />
                <RouteGuard path="/register" component={Register} />
                <RouteGuard path="/add" component={AddPage} />
                <RouteGuard path='/edit/' component={EditPage} />
                <RouteGuard path='/fileupload/' component={FileUploadPage} />
                <RouteGuard path='/room' component={Room} />
                <RouteGuard path='/bill' component={Bill} />
                <RouteGuard path='*' component={NotFound} />

                
            </Switch>
        </Router>
    );
}

export default Routes