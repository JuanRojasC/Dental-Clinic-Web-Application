import "./home-user-nav.css"
import React, { Fragment } from "react";

function HomeUserNav(props){
    return(                
    <nav className="home-user-nav">
        <ul className="home-user-nav-options-list" >
            {Object.values(props.options).map((option, index)=> index === 0?
                <li className="home-user-nav-option" key={index} onClick={props.onClickNav}>{option.text}</li>
            :
            <Fragment key={index}>
                <div className="line-vertical"></div>
                <li className="home-user-nav-option" onClick={props.onClickNav}>{option.text}</li>
            </Fragment>
            )}
        </ul>
        <hr />
    </nav>
);
}

export default HomeUserNav;