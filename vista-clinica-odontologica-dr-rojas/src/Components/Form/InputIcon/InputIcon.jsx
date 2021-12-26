import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import "./input-icon.css";

function InputIcon(props){
    return(
        <div className="input-group input-group-icon">
            <input type={props.type} placeholder={props.placeholder} name={props.id}/>
            <div className="input-icon">
                {props.icon? <FontAwesomeIcon icon={props.icon} className="i"/> : ""}
            </div>
        </div>
    );
}

export default InputIcon;