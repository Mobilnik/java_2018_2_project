import {applyMiddleware, combineReducers, createStore} from "redux";
import promise from "redux-promise-middleware";
import thunk from "redux-thunk";

import ordersReducer from "./ordersReducer";
import productsReducer from "./productsReducer";
import cartReducer from "./cartReducer";
import {routerMiddleware, routerReducer} from "react-router-redux";
import {createBrowserHistory} from 'history'

let reducers = combineReducers({
    //помещается под ключ store.routing
    routing: routerReducer,
    ordersPage: ordersReducer,
    productsPage: productsReducer,
    cartPage: cartReducer,
});

export let browserHistory = createBrowserHistory({ basename: '/' });

const middleware = applyMiddleware(promise, thunk, routerMiddleware(browserHistory));
export let store = createStore(reducers, middleware);

export default store;