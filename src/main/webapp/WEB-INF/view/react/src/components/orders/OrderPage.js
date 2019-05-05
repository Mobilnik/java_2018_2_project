import React from 'react';
import Order from "./item/Order";
import s from './OrderPage.module.css';

const OrderPage = (props) => {
    if (props.mustFetch) {
        props.fetchOrders();
        props.setMustFetch(false);
    }

    let orders = props.orders
        .map((order) => <Order key={order.id}
                               id={order.id}
                               statusCode={order.statusCode}
                               changeDateTime={order.changeDateTime}
                               totalCost={order.totalCost}
                               comment={order.comment}
                               updatedDateTime={order.updatedDateTime}
                               deleteUnprocessedOrder={props.deleteUnprocessedOrder}/>);

    if (orders.length === 0) {
        return (
            <div className={`${s.noOrdersMessage}`}>
                You have not performed any orders. Go make a one!
            </div>
        )
    } else {
        return (
            <div className={`${s.orderPageWrapper}`}>
                <div className={`${s.orderPageGrid}`}>
                    {orders}
                </div>
            </div>
        )
    }
};

export default OrderPage;