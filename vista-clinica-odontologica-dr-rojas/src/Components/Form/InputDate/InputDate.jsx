import Column from "../Column/Column";

function InputDate(props){
    return(         
        <div className="input-group">
            <Column className="col-third"><input type="text" placeholder="DD" id="dia"/></Column>
            <Column className="col-third"><input type="text" placeholder="MM" id="mes"/></Column>
            <Column className="col-third"><input type="text" placeholder="YYYY" id="anio"/></Column>
        </div>
 );
}

export default InputDate;