import { Link } from "react-router-dom";
import logo from "../img/Dr Rojas-Logo-White.png";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBars } from '@fortawesome/free-solid-svg-icons';
import "./header.css";

function Header(props){
    return (
        <header id={props.id} className={props.className}>
            <Link to="/"><img src={logo} alt="Clinic Dental Logo" className="logo-header" /></Link>
            <nav>
                {Object.values(props.dataNav).map((link, index) => 
                    <Link to={link.link} key={index} onClick={link.event? link.event : null}>
                        {link.icon? <FontAwesomeIcon icon={link.icon} className="i" /> : ""}
                        {link.text}
                    </Link>)}
            </nav>
            <FontAwesomeIcon icon={faBars} className="menu-mobile"/>
        </header>
    );
}

export default Header;