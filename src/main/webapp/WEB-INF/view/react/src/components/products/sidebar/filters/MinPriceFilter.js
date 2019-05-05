import React from "react";
import s from "./ProductFilters.module.css";

const MinPriceFilter = (props) => {
    const onMinPriceChange = (event) => {
        props.updateMinPrice(event.target.value);
    };

    return (
        <div>
            Min price
            <div>
                <input className={s.priceLimitFilter}
                       type={'number'}
                       onChange={onMinPriceChange}
                       value={props.minPrice}
                >
                </input>
            </div>
        </div>
    )
};

export default MinPriceFilter;