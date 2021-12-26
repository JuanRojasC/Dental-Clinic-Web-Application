import { useState, useEffect } from "react";
import Header from "../Header/Header";
import Row from "../Form/Row/Row";
import Column from "../Form/Column/Column";
import SignupPacienteForm from "../SignupForm/SignupPacienteForm";
import SignupOdontologoForm from "../SignupForm/SignupOdontologoForm";

function Signuppage(props){
    let [nav] = useState({
        agendar: {link: "/agendar", text: "Agendar"},
        iniciarSesion: {link: "/login", text: "Iniciar Sesion"},
        registro: {link: "/registro", text: "Registrarse"}
    })
    let [formType, setFormType] = useState("paciente");

    let checked = (e) => {
        setFormType(e.target.value);
    }

    useEffect(()=>{       
    },[formType])

    return(
        <>
            <Header dataNav={nav} id="header-loginpage" className="white-header"/>
            <main className="main-sign-up-page">
                <form action="">
                    <Row class="form-type">
                        <Column className="col-half paciente-form option-form">
                            <input id="paciente" className="opcion" type="radio" name="form" value="paciente" defaultChecked onClick={checked}/>
                            <label htmlFor="paciente">Paciente</label>
                        </Column>
                        <Column className="col-half odontologo-form option-form">
                            <input id="odontologo" className="opcion" type="radio" name="form" value="odontologo" onClick={checked}/>
                            <label htmlFor="odontologo">Odontologo</label>
                        </Column>
                    </Row>
                </form>
                {formType === "paciente"? <SignupPacienteForm /> : <SignupOdontologoForm />}
            </main>
        </>
    );
}

export default Signuppage;