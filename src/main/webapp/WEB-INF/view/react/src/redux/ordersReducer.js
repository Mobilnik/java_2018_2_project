import axios from "axios";

const DELETE_UNACCEPTED_ORDER = 'DELETE_UNACCEPTED_ORDER';

const SET_MUST_FETCH_ORDERS = 'SET_MUST_FETCH_ORDERS';
const FETCH_ORDERS = 'FETCH_ORDERS';
const FETCH_ORDERS_PENDING = 'FETCH_ORDERS_PENDING';
const FETCH_ORDERS_REJECTED = 'FETCH_ORDERS_REJECTED';
const FETCH_ORDERS_FULFILLED = 'FETCH_ORDERS_FULFILLED';

let initialState = {
    //todo hack
    mustFetch: true,

    fetching: false,
    fetched: false,
    error: null,

    orders: []
};

const ordersReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_MUST_FETCH_ORDERS:
            return {
                ...state,
                mustFetch: action.newValue
            };

        case FETCH_ORDERS_PENDING:
            return {
                ...state,
                fetching: false,
                error: null
            };

        case FETCH_ORDERS_REJECTED:
            return {
                ...state,
                fetching: false,
                error: action.error
            };

        case FETCH_ORDERS_FULFILLED:
            let orders = prepareOrdersToDisplay(action.payload.data);
            return {
                ...state,
                fetching: false,
                fetched: true,
                orders: orders
            };

        case DELETE_UNACCEPTED_ORDER:
            return deleteUnacceptedOrder(state, action.orderId);
        default:
            return state;
    }
};

const prepareOrdersToDisplay = (orderDtos) => {
    orderDtos.forEach(o => {
        //o.updatedDateTime = new Date(o.updatedDateTime + "Z"); //если работаем на бэке с LocalDateTime: начинаем воспринимать пришедшее время как время в UTC
        o.updatedDateTime = new Date(o.updatedDateTime.substring(0, o.updatedDateTime.indexOf('Z') + 1)); //если работаем на бэке с ZonedDateTime: пришло время в UTC, отрезали все, что после "Z" (доп. инфа о таймзоне)
        o.totalCost = calculateTotalCost(o.products);
    });
    return orderDtos;
};

const calculateTotalCost = (products) => {
    let totalCost = 0;
    products.forEach(p => {
        totalCost += p.productPrice * p.quantity;
    });
    return totalCost;
};


const deleteUnacceptedOrder = (state, orderId) => {
    let stateCopy = {...state};
    stateCopy.orders = [...state.orders];

    let orderToDelete = findByOrderId(stateCopy.orders, orderId);
    let orderToDeleteIndex = stateCopy.orders.indexOf(orderToDelete);

    stateCopy.orders.splice(orderToDeleteIndex, 1);
    return stateCopy;
};

const findByOrderId = (array, orderId) => {
    return array.filter(item => item.id === orderId)[0];
};

//Action Creators
export const setMustFetchOrdersCreator = (newValue) => {
    return {
        type: SET_MUST_FETCH_ORDERS,
        newValue: newValue
    }
};

export const fetchOrdersCreator = () => {
    return {
        type: FETCH_ORDERS,
        payload: axios.get("http://localhost:8080/mipt-shop/orders")
    }
};

export const deleteUnacceptedOrderCreator = (orderId) => {
    return {
        type: DELETE_UNACCEPTED_ORDER,
        orderId: orderId
    }
};

export default ordersReducer;