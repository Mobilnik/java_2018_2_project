import {connect} from "react-redux";
import ProductPage from "./ProductPage";
import {
    fetchProductsCreator,
    updateProductFilterTextCreator,
    setMustFetchProductsCreator,
    putProductToCartCreator
} from "../../redux/productsReducer";
import {setMustFetchCartCreator} from "../../redux/cartReducer";

let mapStateToProps = (state) => {
    return {
        filterText: state.productsPage.filters.filterText,
        products: state.productsPage.products,
        mustFetch: state.productsPage.mustFetch
    };
};

let mapDispatchToProps = (dispatch) => {
    return {
        setMustFetch: (newValue) => {
            dispatch(setMustFetchProductsCreator(newValue));
        },
        fetchProducts: () => {
            dispatch(fetchProductsCreator());
        },
        updateProductFilterText: (newValue) => {
            dispatch(updateProductFilterTextCreator(newValue));
        },
        putProductToCart: (productId) => {
            dispatch(putProductToCartCreator(productId));
            dispatch(setMustFetchCartCreator(true));
        }
    };
};

let ProductPageContainer = connect(mapStateToProps, mapDispatchToProps)(ProductPage);

export default ProductPageContainer;