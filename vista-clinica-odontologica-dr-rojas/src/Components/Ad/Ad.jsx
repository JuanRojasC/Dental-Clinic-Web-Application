import { Link } from "react-router-dom";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowCircleRight } from '@fortawesome/free-solid-svg-icons';
import "./ad.css";

function Ad(props){
    return (
    <div className={`ad ${props.class}`}>
        <h2>{props.text}</h2>
        <p>{props.price}</p>
        <Link to={props.link}>Info<FontAwesomeIcon icon={faArrowCircleRight} className="i"/></Link>
    </div>
    );
}

export default Ad;