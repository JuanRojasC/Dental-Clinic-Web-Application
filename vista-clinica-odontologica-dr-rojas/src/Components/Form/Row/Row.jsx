import "./row.css";

function Row(props){
    return (
        <div className={`row ${props.class}`}>
            {props.children}
        </div>
    );
}

export default Row;