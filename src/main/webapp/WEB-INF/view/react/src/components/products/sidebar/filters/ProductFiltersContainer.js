import {connect} from "react-redux";
import ProductFilters from "./ProductFilters";
import {
    selectProductCategoryCreator,
    updateMaxPriceFilterCreator,
    updateMinPriceFilterCreator
} from "../../../../redux/productsReducer";
import {isCorrectFloat} from "../../../lib";

let mapStateToProps = (state) => {
    return {
        filters: state.productsPage.filters
    }
};

let mapDispatchToProps = (dispatch) => {
    return {
        selectCategory: (categoryId) => {
            dispatch(selectProductCategoryCreator(categoryId));
        },
        updateMinPrice: (newValue) => {
            if (isCorrectFloat(newValue)) {
                dispatch(updateMinPriceFilterCreator(newValue));
            }
        },
        updateMaxPrice: (newValue) => {
            if (isCorrectFloat(newValue)) {
                dispatch(updateMaxPriceFilterCreator(newValue));
            }
        }
    };
};


let ProductFiltersContainer = connect(mapStateToProps, mapDispatchToProps)(ProductFilters);
export default ProductFiltersContainer;