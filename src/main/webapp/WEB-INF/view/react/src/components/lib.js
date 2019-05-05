export const convertJsDateToStringToDisplay = (date) => {
    return (
        date.getFullYear() + "."
        + (parseInt(date.getMonth(), 10) + 1 < 10 ? "0" + (parseInt(date.getMonth(), 10) + 1) : date.getMonth()) + "."
        + (parseInt(date.getDate(), 10) < 10 ? "0" + (parseInt(date.getDate(), 10)) : date.getDate()) + " "
        + (parseInt(date.getHours(), 10) < 10 ? "0" + (parseInt(date.getHours(), 10)) : date.getHours()) + ":"
        + (parseInt(date.getMinutes(), 10) < 10 ? "0" + (parseInt(date.getMinutes(), 10)) : date.getMinutes()) + ":"
        + (parseInt(date.getSeconds(), 10) < 10 ? "0" + (parseInt(date.getSeconds(), 10)) : date.getSeconds()));
};

export const isCorrectFloat = (str) => {
    let num = parseFloat(str);
    return !isNaN(num);
};

export const isCorrectIntegerOrEmpty = (str) => {
    if (str === "") {
        return true;
    }
    return /^\+?(0|[1-9]\d*)$/.test(str);
};