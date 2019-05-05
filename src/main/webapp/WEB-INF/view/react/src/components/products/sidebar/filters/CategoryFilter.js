import React from "react";

const CategoryFilter = (props) => {

    const onCategorySelect = (event) => {
        props.selectCategory(event.target.value);
    };

    let categories = [
        <option key={'0'} value={""}/>
    ];

    props.categories.forEach(c => {
        categories.push(
            <option key={c.categoryId}
                    value={c.categoryId}>
                {c.categoryName}
            </option>
        );
    });

    return (
        <div>
            Select a category:
            <div>
                <select value={props.selectedCategoryId}
                        onChange={onCategorySelect}>
                    {categories}
                </select>
            </div>
        </div>
    );
};

export default CategoryFilter;