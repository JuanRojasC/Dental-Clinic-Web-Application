import { useState } from "react";
import Header from "../Header/Header";
import HomeAdmin from "../HomeAdmin/HomeAdmin";
import { faSignOutAlt, faHome } from '@fortawesome/free-solid-svg-icons';

function HomeUser(props){
    let [nav] = useState({
        home: {link:window.location.href.split("3000")[1], text:"Inicio", icon: faHome, event: () => window.location.reload()},
        logout: {link: "/", text: "Cerrar Sesion", icon: faSignOutAlt }
    })

    return(        
        <>
            <Header dataNav={nav} id="header-loginpage" className="white-header"/>
            <main className="main-home-user">
                {window.location.href.includes("admin")? <HomeAdmin /> : window.location.href.includes("paciente")? "" : ""}
            </main>
        </>
    );
}

export default HomeUser;