import React from 'react';
import Product from "./item/Product";
import s from "./ProductPage.module.css";


let ProductPage = (props) => {
    console.log('render')
    if (props.mustFetch) {
        props.fetchProducts();
        props.setMustFetch(false);
    }

    let products = props.products
        .map(product => <Product key={product.id}
                                 id={product.id}
                                 name={product.name}
                                 photoUrl={product.photoUrl}
                                 price={product.price}
                                 hidden={product.hidden}
                                 putProductToCart={props.putProductToCart}/>);

    const onFilterTextChange = (event) => {
        props.updateProductFilterText(event.target.value);
    };

    return (
        <div className={`${s.productPageWrapper}`}>

            <textarea className={`${s.productPageSearch}`}
                      value={props.filterText}
                      onChange={onFilterTextChange}
                      placeholder={'Enter a product name'}
                      maxLength={20}
            />

            <div className={`${s.productPageGrid}`}>
                {products}
            </div>

        </div>
    );

};

export default ProductPage;