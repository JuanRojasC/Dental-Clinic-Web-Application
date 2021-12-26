import { useState, useEffect } from "react";
import { useHistory } from "react-router";
import Row from "../Form/Row/Row";
import InputIcon from "../Form//InputIcon/InputIcon";
import Submitbutton from "../Form/Submitbutton/Submitbutton";
import { faEnvelope, faKey } from '@fortawesome/free-solid-svg-icons';
import "./loginform.css";

function Loginform(props){

    let [linkAPI] = useState(sessionStorage.getItem("linkAPI")+"/login");
    let [wrongData, setWrongData] = useState(false);
    let [fetching, setFetching] = useState(false);
    let [connection, setConnection] = useState(true);
    const history = useHistory();

    let login = async (e) => {
        e.preventDefault();
        let status = 0;
        try{
            setFetching(true);
            const request = await fetch(linkAPI, {
                method: "POST",
                headers:{
                    "Content-type": "application/json",
                },
                body: JSON.stringify({email: e.target.elements.email.value, password: e.target.elements.password.value}) 
            });
            const response = await request.json();
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
            const typeUser = await response.authorities.authority.toLowerCase();
            sessionStorage.setItem("jwt", response.token);
            history.push(`/${typeUser}/home`);
        }catch(e){
            console.log("FAILED CONECTION API: " + e)
            setConnection(false);
            setFetching(false);
        }
    }

    // let resetForm = (e) => {
    //     e.target.elements.email.value = "";
    //     e.target.elements.password.value = "";
    // }

    useEffect(() => {
    }, [wrongData, fetching, connection])

    useEffect(() => {
        return() => {
            setWrongData(false);
            setFetching(false);
            setConnection(true);
        };
    }, [])

    return (
        <>
            <div className={`container-loginform ${wrongData? "wrong-inputs" : ""}`}>
                <h2>Iniciar Sesion</h2>
                <form action="" method="POST" id="form-loginpage" onSubmit={login}>
                    <hr />
                    <Row class="container-inputs">
                        {wrongData? <span>*Usuario o Contraseña Incorrectos</span> : ""}
                        {connection? "" : <span>*Sin Conexion</span>}
                    </Row>
                    <Row class="container-inputs">
                        <InputIcon type="text" placeholder="Email" id="email" icon={faEnvelope}/>
                    </Row>
                    <Row class="container-inputs">
                        <InputIcon type="password" placeholder="Password" id="password" icon={faKey} />
                    </Row>
                    <hr />
                    {fetching? <Submitbutton value="Iniciar" class="row-submit-disable" id="submit-formlogin" fetch={true}/>
                            : <Submitbutton value="Iniciar" id="submit-formlogin"/>}
                </form>
            </div>
        </>
    );
}

export default Loginform;