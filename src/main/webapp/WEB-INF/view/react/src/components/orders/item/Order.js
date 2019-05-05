import React from 'react';
import s from './Order.module.css'
import OrderStatus from "./OrderStatus";
import CancelOrderButton from "./cancel/CancelOrderButton";
import {convertJsDateToStringToDisplay} from "../../lib";

const Order = (props) => {
    return (
        <div className={`${s.orderWrapper}`}>

            <div className={s.orderId}>
                Order #{props.id}
            </div>

            <div className={s.orderDateUpdated}>
                Status updated: {convertJsDateToStringToDisplay(props.updatedDateTime)}
            </div>

            <div className={s.orderTotalCost}>
                ₽{props.totalCost}
            </div>

            <div className={s.orderStatus}>
                <OrderStatus statusCode={props.statusCode}/>
            </div>

            <CancelOrderButton id={props.id}
                               statusCode={props.statusCode}
                               deleteUnprocessedOrder={props.deleteUnprocessedOrder}
            />

            <div className={s.orderComment}>
                Comment: {props.comment}
            </div>


        </div>
    )
};

export default Order;