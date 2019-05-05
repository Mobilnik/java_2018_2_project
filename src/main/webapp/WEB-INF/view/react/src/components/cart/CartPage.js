import React from 'react';
import CartItem from "./item/CartItem";
import s from "./CartPage.module.css";

const CartPage = (props) => {

    if (props.mustFetch) {
        props.fetchCart();
        props.setMustFetch(false);
    }

    //fixme this stuff does not work
    if (props.error != null) {
        return (
            <div className={s.emptyCartMessage}>
                Error communicating with server
            </div>
        )
    }

    let cartItems = props.cartItems.map(cartItem => <CartItem key={cartItem.productId}
                                     productId={cartItem.productId}
                                     productName={cartItem.productName}
                                     productPrice={cartItem.productPrice}
                                     quantity={cartItem.quantity}

                                     updateCartItemQuantity={props.updateCartItemQuantity}
                                     deleteCartItem={props.deleteCartItem}/>);

    const onCartOrderCommentChange = (event) => {
        props.changeOrderComment(event);
    };

    const onSaveButtonPress = () => {
        props.saveCartAsOrder();
    };

    if (props.cartItems.length === 0) {
        return (
            <div className={s.emptyCartMessage}>
                Your cart is currently empty
            </div>
        )
    }

    return (
        <div className={s.cartPageWrapper}>
            <div className={s.cartPageGrid}>
                {cartItems}
            </div>

            <div className={s.cartPageInnerWrapper}>
                <div className={s.totalCost}>
                    Total: ₽{props.totalCost}
                </div>
                <textarea className={s.comment}
                          value={props.cartOrderComment}
                          onChange={onCartOrderCommentChange}
                          placeholder={'Order comment'}
                />

                <div className={`${s.saveButton}`}>
                    <img src="/images/save-button.png" onClick={onSaveButtonPress}/>
                </div>
            </div>
        </div>
    )
};

export default CartPage;