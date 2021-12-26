import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import "./achieved.css";

function Achieved(props){
    return(
        <div className="achieved">
            <FontAwesomeIcon icon={faCheck} className="i" />
            <h2 className="achieved-text">{`${props.user} Creado`}</h2>
        </div>
    );
}

export default Achieved;