import React from "react";
import s from "./ProductFilters.module.css";

const MaxPriceFilter = (props) => {
    const onMaxPriceChange = (event) => {
        props.updateMaxPrice(event.target.value);
    };

    return (
        <div>
            Max price
            <div>
                <input className={s.priceLimitFilter}
                       type={'number'}
                       onChange={onMaxPriceChange}
                       value={props.maxPrice}
                >
                </input>
            </div>
        </div>
    )

};

export default MaxPriceFilter;