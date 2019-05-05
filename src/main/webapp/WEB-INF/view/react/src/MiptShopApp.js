import React from 'react';
import Menu from "./components/menu/Menu";
import {Route} from "react-router";
import ProductPageContainer from "./components/products/ProductPageContainer";
import OrderPageContainer from "./components/orders/OrderPageContainer";
import CartPageContainer from "./components/cart/CartPageContainer";
import s from './MiptShopApp.module.css'
import ProductPageSidebar from "./components/products/sidebar/ProductPageSidebar";

const MiptShopApp = (props) => {
    return (
        <div className={`${s.appWrapper}`}>

            <header className={`${s.appHeader}`}>
                <Menu/>
            </header>

            <div className={`${s.appSidebar}`}>
                <Route path='/products' render={() => <ProductPageSidebar store={props.store}/>}
                />
            </div>

            <div className={`${s.appContent}`}>
                <Route path='/products' render={() => <ProductPageContainer store={props.store}/>}
                />
                <Route path='/orders' render={() => <OrderPageContainer store={props.store}/>}
                />
                <Route path='/cart' render={() => <CartPageContainer store={props.store}/>}
                />
            </div>

        </div>
    );
};

export default MiptShopApp;
