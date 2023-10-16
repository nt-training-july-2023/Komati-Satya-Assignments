import React from "react";

var Hello = React.createClass({
    render: function() {
        function click(event) {
            event.preventDefault();
            console.log('You can see me even is user clicked on "open link in new tab"')
        }
        return (
        <div>
            < a href = '#'
        target = "_blank"
        onClick = { click } > Go there < /a> < /div>);
    }
});

React.render( < Hello / > , document.body);