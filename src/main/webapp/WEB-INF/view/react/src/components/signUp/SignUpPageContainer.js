import {connect} from "react-redux";
import {
    postNewUserCreator,
    updateEmailCreator,
    updatePasswordCreator,
    updateUserNameCreator
} from "../../redux/signUpReducer";
import SignUpPage from "./SignUpPage";

let mapStateToProps = (state) => {
    return {
        userName: state.signUpPage.userName,
        eMail: state.signUpPage.eMail,
        password: state.signUpPage.password,
    }
};

let mapDispatchToProps = (dispatch) => {
    return {
        updateUserName: (newValue) => {
            dispatch(updateUserNameCreator(newValue));
        },

        updateEmail: (newValue) => {
            dispatch(updateEmailCreator(newValue));
        },

        updatePassword: (newValue) => {
            dispatch(updatePasswordCreator(newValue));
        },

        postNewUser: () => {
            dispatch(postNewUserCreator());
        }
    };
};


let SignUpPageContainer = connect(mapStateToProps, mapDispatchToProps)(SignUpPage);

export default SignUpPageContainer;