import axios from "axios";
import {isCorrectIntegerOrEmpty} from "../components/lib";

const SET_MUST_FETCH_CART = 'SET_MUST_FETCH_CART';
const FETCH_CART = 'FETCH_CART';
const FETCH_CART_PENDING = 'FETCH_CART_PENDING';
const FETCH_CART_REJECTED = 'FETCH_CART_REJECTED';
const FETCH_CART_FULFILLED = 'FETCH_CART_FULFILLED';

const UPDATE_CART_ITEM_QUANTITY = 'UPDATE_CART_ITEM_QUANTITY';
const DELETE_CART_ITEM = 'DELETE_CART_ITEM';
const UPDATE_CART_ORDER_COMMENT = 'UPDATE_CART_ORDER_COMMENT';
const CREATE_NEW_ORDER_FROM_CART = 'CREATE_NEW_ORDER_FROM_CART';

const initialState = {
    //todo hack
    mustFetch: true,
    orderId: null,
    fetching: false,
    fetched: false,
    error: null,
    cartItems: [],
    cartOrderComment: "",
    totalCost: 0
};

const cartReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_MUST_FETCH_CART:
            return {
                ...state,
                mustFetch: action.newValue
            };

        case FETCH_CART_PENDING:
            return {
                ...state,
                fetching: false,
                error: null
            };

        case FETCH_CART_REJECTED:
            return {
                ...state,
                fetching: false,
                error: action.error
            };

        case FETCH_CART_FULFILLED:
            return {
                ...state,
                fetching: false,
                fetched: true,
                orderId: action.payload.data.id,
                cartItems: action.payload.data.products,
                totalCost: calculateTotalCost(action.payload.data.products)
            };


        case UPDATE_CART_ITEM_QUANTITY:
            return updateCartItemQuantity(state, action.productId, action.newValue);
        case DELETE_CART_ITEM:
            return deleteCartItem(state, action.productId);
        case UPDATE_CART_ORDER_COMMENT:
            return updateCartOrderComment(state, action.newText);
        case CREATE_NEW_ORDER_FROM_CART:
            return createNewOrderFromCart(state);
        default:
            return state;
    }
};

export default cartReducer;

//Функции, меняющие данные. Только функции, определенные в state, могут его менять
const updateCartItemQuantity = (state, productId, newValue) => {
    if (!isCorrectIntegerOrEmpty(newValue)) {
        return state;
    }

    let stateCopy = {...state};
    stateCopy.cartItems = [...state.cartItems];

    let itemToChange = findByProductId(stateCopy.cartItems, productId);
    if (newValue === "") {
        itemToChange.quantity = 1;
    } else {
        itemToChange.quantity = parseInt(newValue, 10);
    }

    httpUpdateCartItemQuantity(itemToChange);

    stateCopy.totalCost = calculateTotalCost(stateCopy.cartItems);

    return stateCopy;
};

const httpUpdateCartItemQuantity = (itemToChange) => {
    axios.post("api/orders/update_cart_item",
        buildOrderProductDto(itemToChange));
};

const buildOrderProductDto = (item) => {
    return {
        productId: item.productId,
        quantity: item.quantity
    };
};

const deleteCartItem = (state, productId) => {
    let stateCopy = {...state};
    stateCopy.cartItems = [...state.cartItems];

    let itemToDelete = findByProductId(stateCopy.cartItems, productId);
    let itemToDeleteIndex = stateCopy.cartItems.indexOf(itemToDelete);

    httpDeleteCartItem(itemToDelete);

    stateCopy.cartItems.splice(itemToDeleteIndex, 1);
    stateCopy.totalCost = calculateTotalCost(stateCopy.cartItems);
    return stateCopy;
};

const httpDeleteCartItem = (itemToDelete) => {
    axios.post("api/orders/delete_cart_item",
        buildOrderProductDto(itemToDelete));
};

const calculateTotalCost = (cartItems) => {
    let totalCost = 0;
    cartItems.forEach(p => {
        totalCost += p.productPrice * p.quantity;
    });
    return totalCost;
};

const findByProductId = (array, productId) => {
    return array.filter(item => item.productId === productId)[0];
};

const updateCartOrderComment = (state, newText) => {
    let stateCopy = {...state};
    stateCopy.cartOrderComment = newText;

    return stateCopy;
};

const createNewOrderFromCart = (state) => {
    let stateCopy = {...state};

    let createOrderFromCartPostDto = {
        comment: stateCopy.cartOrderComment
    };
    axios.post("api/orders/create_from_cart",
        createOrderFromCartPostDto);

    stateCopy.cartItems = [];
    stateCopy.cartOrderComment = "";

    return stateCopy
};


//Action Creators
export const setMustFetchCartCreator = (newValue) => {
    return {
        type: SET_MUST_FETCH_CART,
        newValue: newValue
    }
};

export const fetchCartCreator = () => {
    return {
        type: FETCH_CART,
        payload: axios.get("api/orders/cart")
    }
};

export const updateCartOrderCommentCreator = (newText) => {
    return {
        type: UPDATE_CART_ORDER_COMMENT,
        newText: newText
    }
};

export const createNewOrderCreator = () => {
    return {
        type: CREATE_NEW_ORDER_FROM_CART
    }
};

export const updateCartItemQuantityCreator = (productId, newValue) => {
    return {
        type: UPDATE_CART_ITEM_QUANTITY,
        productId: productId,
        newValue: newValue
    }
};
export const deleteCartItemCreator = (productId) => {
    return {
        type: DELETE_CART_ITEM,
        productId: productId,
    }
};