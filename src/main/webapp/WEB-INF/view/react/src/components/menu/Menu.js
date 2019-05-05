import React from 'react';
import s from "./Menu.module.css"
import {NavLink} from "react-router-dom";

const Menu = () => {
    return (
        <nav>
            <div className={`${s.menu}`}>
                <div className={`${s.menuItem}`}>
                    <NavLink to='orders' activeClassName={s.activeLink}> My orders </NavLink>
                </div>
                <div className={`${s.menuItem}`}>
                    <NavLink to='products' activeClassName={s.activeLink}> Products </NavLink>
                </div>

                <div className={`${s.menuItem}`}>
                    <NavLink to='cart' activeClassName={s.activeLink}> Cart </NavLink>
                </div>

                <div className={`${s.menuItem}`}>
                    <a href='/perform_logout'> Logout </a>
                </div>
            </div>
        </nav>
    )
};

export default Menu;