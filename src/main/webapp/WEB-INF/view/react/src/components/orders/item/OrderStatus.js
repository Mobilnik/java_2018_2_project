import React from "react";

const OrderStatus = (props) => {
    switch (props.statusCode) {
        case -1:
            return (
                <div>
                    Canceled
                </div>
            );
        case 1:
            return (
                <div>
                    Unaccepted
                </div>
            );
        case 2:
            return (
                <div>
                    Accepted
                </div>
            );
        case 3:
            return (
                <div>
                    Ready
                </div>
            );
        default: {
            return (
                <div>
                    Unrecognized
                </div>
            );
        }
    }
};

export default OrderStatus;