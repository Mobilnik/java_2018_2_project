import {
    updateCartOrderCommentCreator,
    createNewOrderCreator,
    updateCartItemQuantityCreator,
    deleteCartItemCreator,
    fetchCartCreator,
    setMustFetchCartCreator
} from "../../redux/cartReducer";
import CartPage from "./CartPage";
import {connect} from "react-redux";
import {setMustFetchOrdersCreator} from "../../redux/ordersReducer";

//При любых изменениях connect пытается перерисовать этот объект.
//Проверка на наличие изменений проводится через сравнение ссылок на объекты.
//Если ссылки равны, объект не будет перерисован. Поэтому нам нужны копии (оператор ...)
let mapStateToProps = (state) => {
    return {
        totalCost: state.cartPage.totalCost,
        cartItems: state.cartPage.cartItems,
        cartOrderComment: state.cartPage.cartOrderComment,
        mustFetch: state.cartPage.mustFetch
    }
};

let mapDispatchToProps = (dispatch) => {
    return {
        setMustFetch: (newValue) => {
            dispatch(setMustFetchCartCreator(newValue));
        },
        fetchCart: () => {
            dispatch(fetchCartCreator());
        },

        updateCartItemQuantity: (productId, event) => {
            let newValue = event.target.value;
            dispatch(updateCartItemQuantityCreator(productId, newValue));
        },

        deleteCartItem: (productId) => {
            dispatch(deleteCartItemCreator(productId));
        },

        changeOrderComment: (event) => {
            let newText = event.target.value;
            dispatch(updateCartOrderCommentCreator(newText))
        },

        saveCartAsOrder: () => {
            dispatch(createNewOrderCreator());
            dispatch(setMustFetchOrdersCreator(true));
            //import {push} from "react-router-redux";
            //dispatch(push('/orders')) -- Если хотите использовать редиректы.
        }
    };
};


let CartPageContainer = connect(mapStateToProps, mapDispatchToProps)(CartPage);

export default CartPageContainer;