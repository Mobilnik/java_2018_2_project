import React from 'react';
import styles from './Product.module.css'

const Product = (props) => {

    if (props.hidden) {
        return null;
    }

    const onCartButtonClick = () => {
        props.putProductToCart(props.id);
    };

    return (
        <div className={`${styles.productItemWrapper}`}>
            <div className={`${styles.productItemPhoto}`}>
                {/*Если хотим отображать base 64
                <img src={`data:image/jpeg;base64,${props.photo}`} />
                */}
                <img src={props.photoUrl}/>
            </div>

            <div className={`${styles.productItemDescription}`}>
                {props.name}
                <div className={`${styles.price}`}>
                    ₽{props.price}
                </div>
            </div>
            <div className={`${styles.productItemAddButton}`}>
                <img src="/images/cart.png" onClick={onCartButtonClick}/>
            </div>
        </div>
    )
};

export default Product;