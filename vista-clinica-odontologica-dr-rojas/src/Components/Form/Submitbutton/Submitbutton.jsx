import Row from "../Row/Row";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import "./submit-button.css";


function Submitbutton(props){
    return(
        <Row class={props.class? `row-submit ${props.class}` : "row-submit"}>
            {props.fetch? <FontAwesomeIcon icon={faSpinner} className="spinner-fetch" /> : ""}
            {/* <input id={props.id} type="submit" value={props.value} className="submit" disabled={props.fetch}/> */}
            <button type="submit" className="submit" disabled={props.fetch}>{props.value}</button>
        </Row>
    );
}

export default Submitbutton;