import axios from "axios";

const UPDATE_USER_NAME = 'UPDATE_USER_NAME';
const UPDATE_E_MAIL = 'UPDATE_E_MAIL';
const UPDATE_PASSWORD = 'UPDATE-CART-ITEM-QUANTITY';

const POST_NEW_USER = 'POST_NEW_USER';

const initialState = {
    userName: "",
    email: "",
    password: ""
};

const signUpReducer = (state = initialState, action) => {
    switch (action.type) {
        case UPDATE_USER_NAME:
            return {
                ...state,
                userName: action.userName
            };

        case UPDATE_E_MAIL:
            return {
                ...state,
                email: action.email,
            };

        case UPDATE_PASSWORD:
            return {
                ...state,
                password: action.password,
            };

        case POST_NEW_USER:
            return postNewUser(state);

        default:
            return state;
    }
};


const postNewUser = (state) => {
    httpPostNewUser(state);
    return state;
};

const httpPostNewUser = (state) => {
    axios.post("http://localhost:8080/mipt-shop/users/create",
        buildCreateUserPostDto(state));
};

const buildCreateUserPostDto = (state) => {
    return {
        name: state.userName,
        email: state.email,
        password: state.password
    };
};

export const updateUserNameCreator = (userName) => {
    return {
        type: UPDATE_USER_NAME,
        userName: userName
    }
};

export const updateEmailCreator = (email) => {
    return {
        type: UPDATE_E_MAIL,
        email: email
    }
};

export const updatePasswordCreator = (password) => {
    return {
        type: UPDATE_PASSWORD,
        password: password
    }
};

export const postNewUserCreator = () => {
    return {
        type: POST_NEW_USER,
    }
};

export default signUpReducer;