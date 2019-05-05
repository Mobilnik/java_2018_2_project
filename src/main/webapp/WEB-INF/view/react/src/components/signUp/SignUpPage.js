import React from "react";
import s from "../cart/CartPage.module.css";

const SignUpPage = (props) => {

    const onUserNameChange = (event) => {
        props.updateUserName(event.target.value);
    };

    const onEmailChange = (event) => {
        props.updateEmail(event.target.value);
    };

    const onPasswordChange = (event) => {
        props.updatePassword(event.target.value);
    };

    const onCreateUserButtonClick = () => {
        props.postNewUser();
    };

    return (
        <div>
            <input value={props.userName}
                      onChange={onUserNameChange}
                      placeholder={'User name'}
            />
            <br/>
            <input value={props.eMail}
                      onChange={onEmailChange}
                      placeholder={'E-mail'}
            />
            <br/>
            <input value={props.password}
                      onChange={onPasswordChange}
                      placeholder={'Password'}
            />
            <br/>
            <div>
                <button onClick={onCreateUserButtonClick}>
                    Create user
                </button>
            </div>
        </div>
    )

};


export default SignUpPage;