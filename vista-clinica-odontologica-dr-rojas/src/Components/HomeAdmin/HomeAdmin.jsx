import { useState, useEffect } from "react";
import HomeUserNav from "../HomeUserNav/HomeUserNav";
import { faUsers, faNotesMedical, faClipboardList } from '@fortawesome/free-solid-svg-icons';
import List from "../List/List";
import UserAccount from "../UserAccount/UserAccount";
import SignupOdontologoForm from "../SignupForm/SignupOdontologoForm";
import SignupPacienteForm from "../SignupForm/SignupPacienteForm";
import useFetch from "../../Hooks/useFetch";
import AppointmentForm from "../AppointmentForm/AppointmentForm";

function HomeAdmin(props){

    let [homeNav] = useState({
        pacientes: {text:"Pacientes", icon:faUsers},
        empleados: {text:"Empleados", icon:faNotesMedical},
        turnos: {text:"Turnos", icon:faClipboardList}
    });
    let [pacientesHeaders] = useState(["ID", "PACIENTE", "DOCUMENTO", "NUMERO", "DOMICILIO"]);
    let [empleadosHeaders] = useState(["ID", "EMPLEADO", "MATRICULA", "GRADO", "TITULO"]);
    let [turnoHeaders] = useState(["ID", "PACIENTE", "ODONTOLOGO", "FECHA"])
    let [linkAPIPacientes] = useState("/paciente/list");
    let [linkAPIOdontologos] = useState("/odontologo/list");
    let [linkAPITurnos] = useState("/turno/list")
    let [navSelected, setNavSelected] = useState("Pacientes");
    let [userScreen, setUserScreen] = useState(null);
    let [userSelected, setUserSelected, setUserSelectedValue] = useFetch();

    useEffect(()=>{
        let userId = userScreen?.target.getAttribute("data-id");
        if(userScreen!==null && userId !== null){
            // console.log("id: " + userScreen?.target.getAttribute("data-id"))
            let userUrl = navSelected === "Pacientes"? "/paciente/" : "/odontologo/";
            setUserSelected(sessionStorage.getItem("linkAPI")+userUrl+userId)
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [userScreen])

    useEffect(()=>{
        setUserSelectedValue(null)
    // eslint-disable-next-line react-hooks/exhaustive-deps
    },[navSelected])
    useEffect(()=>{},[userSelected])

    let renderComponent = (component) => {
        switch(component){
            case "Pacientes" :
                return <List title={navSelected} headers={pacientesHeaders} linkAPI={linkAPIPacientes} onClickRow={(e)=>setUserScreen(e)} onClickAdd={(e)=>setNavSelected(e.target.textContent)} />
            case "Empleados":
                return <List title={navSelected} headers={empleadosHeaders} linkAPI={linkAPIOdontologos} onClickRow={(e)=>setUserScreen(e)} onClickAdd={(e)=>setNavSelected(e.target.textContent)} />
            case "Turnos":
                return <List title={navSelected} headers={turnoHeaders} linkAPI={linkAPITurnos} onClickRow={(e)=>setUserScreen(e)} onClickAdd={(e)=>setNavSelected(e.target.textContent)} />
            case "Nuevo Empleado":
                return <SignupOdontologoForm />
            case "Nuevo Paciente":
                return <SignupPacienteForm />
            case "Nuevo Turno":
                return <AppointmentForm />
            default:
                return <List title={navSelected} headers={pacientesHeaders} linkAPI={linkAPIPacientes} onClickRow={(e)=>setUserScreen(e)} onClickAdd={(e)=>setNavSelected(e.target.textContent)} />
        }
    }

    return(
        <>
            <HomeUserNav options={homeNav} onClickNav={(e)=>setNavSelected(e.target.textContent)}/>
            {userSelected? 
                    <UserAccount userData={userSelected? userSelected[0] : ""} />
                :
                    renderComponent(navSelected)
            }
        </>
    );
            
}


export default HomeAdmin;