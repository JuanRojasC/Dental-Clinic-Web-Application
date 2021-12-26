import Column from "../Column/Column";
import InputIcon from "../InputIcon/InputIcon";
import { faGraduationCap } from '@fortawesome/free-solid-svg-icons';

function InputStudies(props){
    return (
        <>            
            <Column className={`col-half ${props.classSelect}`}>
                <select name="tipo_estudio"defaultValue="">
                    <option value="" hidden disabled>Titulo</option>
                    <option value="1">Tecnico</option>
                    <option value="2">Profesional</option>
                    <option value="3">Especializado</option>
                    <option value="4">Magister</option>
                    <option value="5">Doctorado</option>
                </select>
            </Column>
            <Column className="col-half">
                <InputIcon type="text" placeholder="Titulo Obtenido" id="titulo_obtenido" icon={faGraduationCap}/>
            </Column>
        </>
    );
}

export default InputStudies;