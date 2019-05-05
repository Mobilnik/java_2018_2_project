import React from "react";
import s from "./CancelOrderButton.module.css";

const CancelOrderButton = (props) => {
    return (
        <div className={`${s.orderCancelButton}`}
             hidden={props.statusCode !== 1}>
            <img src="/images/red_cross.png" onClick={() => props.deleteUnprocessedOrder(props.id)}/>
        </div>
    )
};
export default CancelOrderButton;