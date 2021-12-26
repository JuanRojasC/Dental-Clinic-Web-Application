import "./appointment-form.css";
import Row from "../Form/Row/Row";
import InputIcon from "../Form/InputIcon/InputIcon";
import { faUser, faEnvelope, faCalendar } from '@fortawesome/free-solid-svg-icons';
import Submitbutton from "../Form/Submitbutton/Submitbutton";
import useFetch from "../../Hooks/useFetch";
import { useEffect, useState } from "react/cjs/react.development";
import useFetchPost from "../../Hooks/useFetchPost";

function AppointmentForm(props){

    let [odontologos, setOdontologos] = useFetch();
    let [turno, setTurno] = useFetchPost();
    let [fetching, setFetching] = useState(false);

    useEffect(()=>{setOdontologos(sessionStorage.getItem("linkAPI")+"/odontologo/list")},[])

    useEffect(()=>{},[odontologos])
    useEffect(()=>{},[fetching])

    let collectData = (e) => {
        e.preventDefault();
        let nodeForm = e.target.elements
        let dataTurno = {
            nombre: nodeForm.nombre.value,
            email: nodeForm.email.value,
            fechaTurno: nodeForm.date.value,
            odontologo: odontologos.find((o)=>o.id===parseInt(nodeForm.odontologos_disponibles.value))
        }
        setFetching(true);
        setTurno({linkAPI: sessionStorage.getItem("linkAPI")+"/turno/new", data: dataTurno});
        setTimeout(()=>{setFetching(false)},1500)
    }

    return(
        <div className="container-form-appointment">
            <form className="form-appointment" onSubmit={collectData}>
                <h2>Agendamiento de Citas</h2>
                <Row>
                <InputIcon type="text" placeholder="Nombre" id="nombre" icon={faUser}/>
                </Row>
                <Row>
                    <InputIcon type="email" placeholder="Email" id="email" icon={faEnvelope}/>
                </Row>
                <Row>
                    <InputIcon type="datetime-local" placeholder="Fecha" id="date" icon={faCalendar}/>
                </Row>
                <Row>
                    <select name="odontologos_disponibles" defaultValue="" id="odontologos_disponibles">
                        <option value="" hidden disabled>Odontologo</option>
                        {odontologos?.map((o)=><option value={o.id} key={o.id}>{`${o.nombre} ${o.apellido}`}</option>)}
                    </select>
                </Row>
                {fetching? <Submitbutton value="Agendar" class="form-appointment-submit-disable" id="submit-form-singup" fetch={true}/>
                        : <Submitbutton value="Agendar" class="form-appointment-submit"/>}
            </form>
        </div>
    );
}

export default AppointmentForm;