import {connect} from "react-redux";
import {deleteUnacceptedOrderCreator, fetchOrdersCreator, setMustFetchOrdersCreator} from "../../redux/ordersReducer";
import OrderPage from "./OrderPage";


let mapStateToProps = (state) => {
    return {
        mustFetch: state.ordersPage.mustFetch,
        orders: state.ordersPage.orders,
        cartOrderComment: state.cartPage.cartOrderComment
    }
};

let mapDispatchToProps = (dispatch) => {
    return {
        setMustFetch: (newValue) => {
            dispatch(setMustFetchOrdersCreator(newValue));
        },
        fetchOrders: () => {
            dispatch(fetchOrdersCreator());
        },
        deleteUnprocessedOrder: (orderId) => {
            dispatch(deleteUnacceptedOrderCreator(orderId))
        }
    };
};

let OrderPageContainer = connect(mapStateToProps, mapDispatchToProps)(OrderPage);


export default OrderPageContainer;