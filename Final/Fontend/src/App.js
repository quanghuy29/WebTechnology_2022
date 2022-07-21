import React, {Component} from 'react';
import Routers from './routes';

//import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';

import {setAuthToken} from './helpers/setAuthToken'

function App() {

    // check jwt token
    const token = localStorage.getItem("token");
    if(token) {
        setAuthToken(token);
    }
    
    return (
        <div className="App">
            <Routers/>
        </div>
    );
}

export default App;
