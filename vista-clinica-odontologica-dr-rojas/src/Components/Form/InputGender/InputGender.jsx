import "./input-gender.css";
import Column from "../Column/Column";

function InputGender(props){
    return(         
        <div className="select-gender">
            <Column className="col-third">
                <input id="gender-male" type="radio" name="gender" value="hombre"/>
                <label htmlFor="gender-male" className="gender">Hombre</label>
            </Column>
            <Column className="col-third">
                <input id="gender-female" type="radio" name="gender" value="mujer"/>
                <label htmlFor="gender-female" className="gender">Mujer</label>
            </Column>
            <Column className="col-third">
                <input id="gender-other" type="radio" name="gender" value="otro"/>
                <label htmlFor="gender-other" className="gender">Otro</label>
            </Column>
        </div>
    );
}

export default InputGender;