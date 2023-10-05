import React, { useState } from 'react';

const SearchButton = ({ originalData, setItem, setText, placeholder, quiz, category, question, result, student }) => {

    function filterFunction(search) {
        if (quiz) {
            return (item) => (item.topicName || '').toLowerCase().includes(search)
        }
        if (category) {
            return (item) => (item.categoryName || '').toLowerCase().includes(search)
        }
        if (question) {
            return (item) => (item.question || '').toLowerCase().includes(search)
        }
        if (result || student) {
            return (item) => (item.email || '').toLowerCase().includes(search)
        }

    }
    const handleSearch = (e) => {
        const search = e.target.value.toLowerCase();
        setText(search);

        const filteredData = originalData.filter(filterFunction(search)

        );
        setItem(filteredData);
    };

    return (
        <input
            className="search"
            type="text"
            placeholder={placeholder}
            onChange={handleSearch}
        />
    );
}

export default SearchButton;
