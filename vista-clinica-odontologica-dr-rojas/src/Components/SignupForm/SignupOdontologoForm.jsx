import "./signup-form.css";
import { useState, useEffect } from "react";
import { useHistory } from "react-router";
import Row from "../Form/Row/Row";
import InputIcon from "../Form/InputIcon/InputIcon";
import Submitbutton from "../Form/Submitbutton/Submitbutton";
import Column from "../Form/Column/Column";
import InputDate from "../Form/InputDate/InputDate";
import InputGender from "../Form/InputGender/InputGender";
import InputId from "../Form/InputId/InputId";
import InputStudies from "../Form/InputStudies/InputStudies";
import Achieved from "../Form/Achieved/Achieved";
import { faEnvelope, faKey, faUser, faSignature, faBookMedical} from '@fortawesome/free-solid-svg-icons';

function SignupOdontologoForm(props){
    let [linkAPI] = useState(sessionStorage.getItem("linkAPI")+"/signup/odontologo");
    let [wrongData, setWrongData] = useState(false);
    let [fetching, setFetching] = useState(false);
    let [connection, setConnection] = useState(true);
    let [signUpSuccess, setSignUpSuccess] = useState(false);
    const history = useHistory();

    useEffect(() => {
    }, [wrongData, fetching, connection, signUpSuccess])

    useEffect(() => {
        return() => {
            setWrongData(false);
            setFetching(false);
            setConnection(true);
            setSignUpSuccess(false);
        };
    }, [])

    let signUp = async (e) => {
        e.preventDefault();
        let status = 0;
        try{
            setFetching(true);
            const request = await fetch(linkAPI, {
                method: "POST",
                headers:{
                    "Content-type": "application/json",
                },
                body: JSON.stringify(collectData(e))
            });
            const response = await request;
            status = await response.status;
            if((status >= 400 && status < 500) && status !== 0){
                setWrongData(true);
                setFetching(false);
                return;
            }
            if(status === 0){
                setConnection(false);
                setFetching(false);
                return;
            }
            setSignUpSuccess(true)
            setTimeout(()=>{
                history.push("/login");
            }, 2500)
        }catch(err){
            console.log("FAILED CONECTION API: " + err)
            console.log(collectData(e));
            setConnection(false);
            setFetching(false);
        }
    }

    let collectData = (e) => {
        return {
            nombre: e.target.elements.nombre.value,
            apellido: e.target.elements.apellido.value,
            usuario: {
                email: e.target.elements.email.value,
                password: e.target.elements.password.value,
            },
            fechaNacimiento: `${e.target.elements.dia.value}/${e.target.elements.mes.value}/${e.target.elements.anio.value}`,
            genero: e.target.elements.gender.value,
            tipoDocumento: e.target.elements.tipo_dni.value,
            numeroDocumento: e.target.elements.numero_dni.value,
            numeroMatricula: e.target.elements.numero_matricula.value,
            tipoTitulo: e.target.elements.tipo_estudio.value,
            titulo: e.target.elements.titulo_obtenido.value
        }
    }

    return(
        <>
            {signUpSuccess?
                <Achieved user="Odontologo" />
                :
                <div className={`container-signupform ${wrongData? "wrong-inputs" : ""}`}>
                <h2>Registro Odontologos</h2>
                <form action="" method="POST" id="form-signup-odontologo" className="form-signup" onSubmit={signUp}>
                    <hr />
                    <Row class="container-inputs">  
                            {wrongData? <span>*Algunos datos son Invalidos</span> : ""}
                            {connection? "" : <span>*Sin Conexion</span>}
                    </Row>
                    <div className="form-flex">
                        <Column className="col1">
                            <h4>Informacion Personal</h4>
                            <Row class="container-inputs">
                                <InputIcon type="text" placeholder="Nombre" id="nombre" icon={faUser}/>
                            </Row>
                            <Row class="container-inputs">
                                <InputIcon type="text" placeholder="Apellido" id="apellido" icon={faSignature}/>
                            </Row>
                            <Row class="container-inputs">
                                <InputIcon type="email" placeholder="Email" id="email" icon={faEnvelope}/>
                            </Row>
                            <Row class="container-inputs">
                                <InputIcon type="password" placeholder="Password" id="password" icon={faKey} />
                            </Row>
                            <Row>
                                <Column className="col-half">
                                    <h4>Fecha de Nacimiento</h4>
                                    <InputDate />
                                </Column>
                                <Column className="col-half">
                                    <h4>Genero</h4>
                                    <InputGender />
                                </Column>
                            </Row>
                        </Column>
                        <div className="line-vertical"></div>
                        <Column className="col2">
                            <Row class="container-inputs">
                                <h4>Documento de Identidad</h4>
                                <InputId />
                            </Row>
                            <Row>
                                <h4>Numero de Matricula</h4>
                                <InputIcon type="text" placeholder="Numero de Matricula Profesional" id="numero_matricula" icon={faBookMedical} />
                            </Row>
                            <Row>
                                <h4>Estudios</h4>
                                <InputStudies />
                            </Row>
                        </Column>
                    </div>
                    <hr />
                    {fetching? <Submitbutton value="Registrarse" class="row-submit-disable" id="submit-form-singup" fetch={true}/>
                            : <Submitbutton value="Registrarse" id="submit-form-singup"/>}
                </form>
            </div>}          
        </>
    );
}

export default SignupOdontologoForm;