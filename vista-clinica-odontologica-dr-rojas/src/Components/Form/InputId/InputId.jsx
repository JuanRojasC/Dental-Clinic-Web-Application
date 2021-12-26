import Column from "../Column/Column";
import InputIcon from "../InputIcon/InputIcon";
import { faIdCard } from '@fortawesome/free-solid-svg-icons';


function InputId(props){
    return (                    
    <>            
        <Column className={`col-half ${props.classSelect}`}>
            <select name="tipo_dni" defaultValue="">
                <option value="" hidden disabled>Tipo de Documento</option>
                <option value="1">Libreta Electoral</option>
                <option value="2">Carnet de Extranjeria</option>
                <option value="3">Registro Unico de Contribuyentes</option>
                <option value="4">Pasaporte</option>
                <option value="5">Partida de Nacimiento</option>
            </select>
        </Column>
        <Column className="col-half">
            <InputIcon type="text" placeholder="Numero de Identificacion" id="numero_dni" icon={ faIdCard }/>
        </Column>
    </>
);
}

export default InputId;