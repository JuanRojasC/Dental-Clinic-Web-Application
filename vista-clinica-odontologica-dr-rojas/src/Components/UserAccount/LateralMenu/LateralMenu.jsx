import "./lateral-menu.css";
import defaultUserPhoto from "../../img/profilePhotoDefault.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHome, faKey, faUser, faTv } from '@fortawesome/free-solid-svg-icons';

function LateralMenu(props){
    return(
        <div className="profile-tab-nav border-right">
            <div className="p-4">
                <div className="img-circle text-center mb-3">
                    <img src={defaultUserPhoto} alt="User Profile" className="shadow"/>
                </div>
                <h4 className="text-center" id="user_name">Usuario</h4>
            </div>
            <div className="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a className="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab" aria-controls="account" aria-selected="true" onClick={props.onClickOption}>
                    <FontAwesomeIcon icon={faHome} className="fa fa-home text-center mr-1 icon-lateralmenu-option" />
                    Cuenta
                </a>
                <a className="nav-link" id="password-tab" data-toggle="pill" href="#password" role="tab" aria-controls="password" aria-selected="false" onClick={props.onClickOption}>
                    <FontAwesomeIcon icon={faKey} className="fa fa-key text-center mr-1 icon-lateralmenu-option" />
                    Contraseña
                </a>
                <a className="nav-link" id="security-tab" data-toggle="pill" href="#security" role="tab" aria-controls="security" aria-selected="false" onClick={props.onClickOption}>
                    <FontAwesomeIcon icon={faUser} className="fa fa-user text-center mr-1 icon-lateralmenu-option" />
                    Informacion
                </a>
                <a className="nav-link" id="application-tab" data-toggle="pill" href="#application" role="tab" aria-controls="application" aria-selected="false" onClick={props.onClickOption}>
                    <FontAwesomeIcon icon={faTv} className="fa fa-tv text-center mr-1 icon-lateralmenu-option" /> 
                    Ajustes
                </a>
            </div>
        </div>
    );
}

export default LateralMenu;