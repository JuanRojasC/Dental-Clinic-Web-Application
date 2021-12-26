import { useState } from "react";
import Header from "../Header/Header";
import Loginform from "../Loginform/Loginform";

function Loginpage(){

    let [nav] = useState({
        agendar: {link: "/agendar", text: "Agendar"},
        iniciarSesion: {link: "/login", text: "Iniciar Sesion"},
        registro: {link: "/registro", text: "Registrarse"}
    })

    return(
        <>
            <Header dataNav={nav} id="header-loginpage" className="white-header"/>
            <main className="main-loginpage">
                <Loginform />
            </main>
        </>
    );
}

export default Loginpage;