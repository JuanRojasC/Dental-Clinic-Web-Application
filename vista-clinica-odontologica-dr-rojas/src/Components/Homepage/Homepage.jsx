import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { isMobile } from "react-device-detect";
import Header from "../Header/Header";
import Ad from "../Ad/Ad";
import {createPacientes, createOdontologos} from "../../Utilities/createUsuarios";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleLeft, faAngleRight, faPhone } from '@fortawesome/free-solid-svg-icons';
import backgroundImageDesktop from "../img/Smile-Image-2.jpg";
import backgroundImageMobile from "../img/Smile-Image-4.jpg";
import "./homepage.css"

function Homepage(){

    let [nav] = useState({
        agendar: {link: "/agendar", text: "Agendar"},
        iniciarSesion: {link: "/login", text: "Iniciar Sesion"},
        registro: {link: "/registro", text: "Registrarse"}
    })

    let [ads] = useState({
        uno: {text:"Ortodoncia General", price: "Desde $20 Mensuales", link:""},
        dos: {text:"Diseño de Sonrisa", price:"Desde $32 Mensuales", link:""},
        tres: {text:"Implantes Dentales", price:"Desde $50 Mensuales", link:""}
    })

    useEffect(() => {
        let backgroundImage = isMobile? backgroundImageMobile : backgroundImageDesktop;
        document.body.style.backgroundImage = `url(${backgroundImage})`;
        // sessionStorage.setItem("linkAPI", prompt("Ingrese el link base de la api\nEjemplo: http://localhost:8080\n*Sin la ultima / NO http://localhost:8080/"));
        sessionStorage.setItem("linkAPI", "http://localhost:8080");
        createPacientes();
        createOdontologos();

        return () => {
            document.body.style.backgroundImage = "none";
        }
        
    }, [])

    return(
        <>
            <Header dataNav={nav} id="headerIndex" className="white-header"/>
            <main className="main-homepage">
                <div className="ads-homepage">
                    <FontAwesomeIcon icon={faAngleLeft} className="i"/>
                    {Object.values(ads).map((ad, index) => 
                        <Ad text={ad.text} price={ad.price} link={ad.link} key={index} class={`ad-${index}`}></Ad>)
                    }
                    <FontAwesomeIcon icon={faAngleRight} className="i"/>
                </div>
                <div className="text-homepage">
                    <h2>Sonrie</h2>
                    <p>Aprovecha nuestros tratamientos con los mejores especialistas al mejor precio.</p>
                </div>
                <Link to="" className="contactus-homepage">
                    <FontAwesomeIcon icon={faPhone} className="i" />
                </Link>
            </main>
        </>
    );
}

export default Homepage;