function toggleResetPswd(e) {
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle() // display:block or none
    $('#logreg-forms .form-reset').toggle() // display:block or none
}

function toggleSignUp(e) {
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup').toggle(); // display:block or none
}

$(() => {
    // Login Register Form
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);
});


const onSignUpFormSubmit = () => {
    let signUpForm = document.getElementById("form-signup");

    let name = signUpForm.elements["name"].value;
    let email = signUpForm.elements["email"].value;
    let password = signUpForm.elements["password"].value;

    let createUserPostDto = {
        name: name,
        email: email,
        password: password
    };

    axios.post("api/users/create", createUserPostDto)
        .then((response) => {
            document.getElementById("cancel_signup").click();
        })
        .catch((err) => {
            console.log(err);
        })
};