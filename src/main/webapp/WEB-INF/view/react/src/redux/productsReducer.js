import axios from 'axios';

const SET_MUST_FETCH_PRODUCTS = 'SET_MUST_FETCH_PRODUCTS';
const FETCH_PRODUCTS = 'FETCH_PRODUCTS';
const FETCH_PRODUCTS_PENDING = 'FETCH_PRODUCTS_PENDING';
const FETCH_PRODUCTS_REJECTED = 'FETCH_PRODUCTS_REJECTED';
const FETCH_PRODUCTS_FULFILLED = 'FETCH_PRODUCTS_FULFILLED';

const SELECT_PRODUCT_CATEGORY = 'SELECT_PRODUCT_CATEGORY';
const UPDATE_MIN_PRICE_FILTER = 'UPDATE_MIN_PRICE_FILTER';
const UPDATE_MAX_PRICE_FILTER = 'UPDATE_MAX_PRICE_FILTER';
const UPDATE_PRODUCT_FILTER_TEXT = 'UPDATE_PRODUCT_FILTER_TEXT';

const PUT_PRODUCT_TO_CART = 'PUT_PRODUCT_TO_CART';

const initialState = {
    //todo hack
    mustFetch: true,

    fetching: false,
    fetched: false,
    error: null,
    products: [],

    filters: {
        filterText: "",
        selectedCategoryId: "",
        categories: [],
        minPrice: 0,
        maxPrice: 0
    }
};

const productsReducer = (state = initialState, action) => {
    switch (action.type) {
        case SET_MUST_FETCH_PRODUCTS:
            return {
                ...state,
                mustFetch: action.newValue
            };

        case FETCH_PRODUCTS_PENDING:
            return {
                ...state,
                fetching: false,
            };

        case FETCH_PRODUCTS_REJECTED:
            return {
                ...state,
                fetching: false,
                error: action.error
            };

        case FETCH_PRODUCTS_FULFILLED:
            return fetchProductsFulfilled(state, action);

        case SELECT_PRODUCT_CATEGORY:
            let stateToFilter = selectProductCategory(state, action);
            return applyFilters(stateToFilter);

        case UPDATE_MIN_PRICE_FILTER:
            stateToFilter = updateMinPriceFilter(state, action);
            return applyFilters(stateToFilter);

        case UPDATE_MAX_PRICE_FILTER:
            stateToFilter = updateMaxPriceFilter(state, action);
            return applyFilters(stateToFilter);

        case UPDATE_PRODUCT_FILTER_TEXT:
            stateToFilter = updateProductFilterText(state, action);
            return applyFilters(stateToFilter);

        case PUT_PRODUCT_TO_CART:
            return putProductToCart(state, action);

        default:
            return state;
    }
};

const fetchProductsFulfilled = (state, action) => {
    let stateCopy = {
        ...state,
        fetching: false,
        fetched: true,
        products: action.payload.data,
        filters: {
            ...state.filters,
        }
    };
    stateCopy.products.forEach(p => p.hidden = false);
    stateCopy.filters.categories = buildProductCategories(stateCopy.products);
    stateCopy.filters.minPrice = findMinPrice(stateCopy.products);
    stateCopy.filters.maxPrice = findMaxPrice(stateCopy.products);
    return stateCopy;
};

const buildProductCategories = (products) => {
    let categories = [];
    products.forEach(p => {
        let exists = false;

        categories.forEach(c => {
            if (c.categoryId === p.categoryId) {
                exists = true;
            }
        });
        if (!exists) {
            categories.push({
                categoryId: p.categoryId,
                categoryName: p.categoryName
            });
        }
    });
    return categories;
};

const findMinPrice = (products) => {
    let minPrice = 999999999;
    products.forEach(p => {
        if (p.price < minPrice) {
            minPrice = p.price;
        }
    });
    return minPrice;
};

const findMaxPrice = (products) => {
    let maxPrice = 0;
    products.forEach(p => {
        if (p.price > maxPrice) {
            maxPrice = p.price;
        }
    });
    return maxPrice;
};

const applyFilters = (state) => {
    let stateCopy = {...state};
    stateCopy.products = [...stateCopy.products];

    const filterTextLowerCase = stateCopy.filters.filterText.toLowerCase();

    stateCopy.products
        .forEach(p => {
            if (!p.name.toLowerCase().includes(filterTextLowerCase)) {
                p.hidden = true;
            } else if (p.price < stateCopy.filters.minPrice) {
                p.hidden = true;
            } else if (p.price > stateCopy.filters.maxPrice) {
                p.hidden = true;
            } else if (stateCopy.filters.selectedCategoryId !== null &&
                stateCopy.filters.selectedCategoryId !== "" &&
                p.categoryId !== stateCopy.filters.selectedCategoryId) {
                p.hidden = true;
            } else {
                p.hidden = false;
            }
        });

    return stateCopy;
};

const selectProductCategory = (state, action) => {
    return {
        ...state,
        filters: {
            ...state.filters,
            selectedCategoryId: action.categoryId
        },
    };
};

const updateMinPriceFilter = (state, action) => {
    return {
        ...state,
        filters: {
            ...state.filters,
            minPrice: parseFloat(action.newValue)
        }
    };
};

const updateMaxPriceFilter = (state, action) => {
    return {
        ...state,
        filters: {
            ...state.filters,
            maxPrice: parseFloat(action.newValue)
        }
    };
};

const updateProductFilterText = (state, action) => {
    return {
        ...state,
        filters: {
            ...state.filters,
            filterText: action.newValue
        },
    };
};

const putProductToCart = (state, action) => {
    axios.post("http://localhost:8080/mipt-shop/orders/create_cart_item?productId=" + action.productId);
    return state;
};


//Action Creators
export const setMustFetchProductsCreator = (newValue) => {
    return {
        type: SET_MUST_FETCH_PRODUCTS,
        newValue: newValue
    }
};

export const fetchProductsCreator = () => {
    return {
        type: FETCH_PRODUCTS,
        payload: axios.get("http://localhost:8080/mipt-shop/products")
    }
};

export const selectProductCategoryCreator = (categoryId) => {
    return {
        type: SELECT_PRODUCT_CATEGORY,
        categoryId: parseInt(categoryId, 10)
    }
};

export const updateMinPriceFilterCreator = (newValue) => {
    return {
        type: UPDATE_MIN_PRICE_FILTER,
        newValue: newValue
    }
};

export const updateMaxPriceFilterCreator = (newValue) => {
    return {
        type: UPDATE_MAX_PRICE_FILTER,
        newValue: newValue
    }
};

export const updateProductFilterTextCreator = (newValue) => {
    return {
        type: UPDATE_PRODUCT_FILTER_TEXT,
        newValue: newValue
    }
};

export const putProductToCartCreator = (productId) => {
    return {
        type: PUT_PRODUCT_TO_CART,
        productId: productId
    }
};

export default productsReducer;