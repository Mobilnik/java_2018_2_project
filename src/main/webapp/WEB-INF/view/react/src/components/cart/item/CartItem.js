import React from 'react';
import s from './CartItem.module.css'

const CartItem = (props) => {


    const onQuantityChange = (event) => {
        props.updateCartItemQuantity(props.productId, event);
    };

    const onDelete = () => {
        props.deleteCartItem(props.productId);
    };

    return (
        <div className={`${s.cartItemWrapper}`}>

            <div className={`${s.cartItemDescription}`}>
                {props.productName}

                <div className={`${s.price}`}>
                    ₽{props.productPrice} apiece
                </div>
            </div>

            <div className={`${s.cartItemQuantity}`}>

                <input onChange={onQuantityChange}
                       value={props.quantity}
                >
                </input>
            </div>
            <div className={`${s.cartItemRemoveButton}`}>
                <img src="/images/red_cross.png" onClick={onDelete}/>
            </div>
        </div>
    );
};

export default CartItem;