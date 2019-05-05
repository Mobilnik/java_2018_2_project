import React from "react";
import ReactDOM from "react-dom";
import {Router} from 'react-router';
import {Provider} from "react-redux";
import store from "./redux/redux-store.js";
import MiptShopApp from "./MiptShopApp";
import {syncHistoryWithStore} from "react-router-redux";
import {browserHistory} from "./redux/redux-store";
import {HashRouter} from "react-router-dom";

// Create an enhanced history that syncs navigation events with the store
const history = syncHistoryWithStore(browserHistory, store);

const rerenderEntireTree = () => {

    ReactDOM.render(
        <Provider store={store}>
            <HashRouter history={history}>
                <MiptShopApp/>
            </HashRouter>
        </Provider>, document.getElementById('root'));
};

rerenderEntireTree();