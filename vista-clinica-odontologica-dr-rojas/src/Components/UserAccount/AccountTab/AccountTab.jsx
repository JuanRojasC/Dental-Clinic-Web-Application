import { useEffect } from "react/cjs/react.development";
import AccountSelectId from "../AccountSelectId/AccountSelectId";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner, faCheck } from '@fortawesome/free-solid-svg-icons';
import "./account-tab.css";

function AccountTab(props){

    let collectData = (e) => {
        let node = e.target.parentNode.parentNode;
        return {
            id: props?.userAccountData.id,
            nombre: node.querySelector("#nombre_config").value,
            apellido: node.querySelector("#apellido_config").value,
            usuario: {
                email: node.querySelector("#email_config").value,
                password: props?.userAccountData.usuario.password,
                rol: props?.userAccountData.usuario.rol
            },
            fechaNacimiento: `${node.querySelector("#dia_nacimiento_config").value}/${node.querySelector("#mes_nacimiento_config").value}/${node.querySelector("#anio_nacimiento_config").value}`,
            genero: node.querySelector("input[name=gender]:checked").value,
            tipoDocumento: node.querySelector("#tipo_dni_account_tab").value,
            numeroDocumento: node.querySelector("#numeroid_config").value,
            domicilio: props?.userAccountData.domicilio
        }
    }

    useEffect(()=>{
        let nodeGender = document.querySelector(`#genero_${props?.userAccountData.genero}`);
        nodeGender.setAttribute("checked", true);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[])

    return(
        <div className="tab-pane fade show active" id="account-tab" role="tabpanel" aria-labelledby="account-tab">
            <h3 className="mb-4">
                Configuracion
                {!props?.buttonDisable? <FontAwesomeIcon icon={faCheck} className="changes-saved" /> : ""}
            </h3>
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Nombre</label>
                            <input type="text" className="form-control" id="nombre_config" defaultValue={props?.userAccountData.nombre} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Apellido</label>
                            <input type="text" className="form-control" id="apellido_config" defaultValue={props?.userAccountData.apellido} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Email</label>
                            <input type="text" className="form-control" id="email_config" defaultValue={props?.userAccountData.usuario.email} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Fecha de Nacimiento</label>
                            <div className="fechaNacimiento">
                            <input type="text" className="form-control fecha" placeholder="Dia" id="dia_nacimiento_config" defaultValue={props?.userAccountData.fechaNacimiento.split("/")[0]} />
                            <input type="text" className="form-control fecha" placeholder="Mes" id="mes_nacimiento_config" defaultValue={props?.userAccountData.fechaNacimiento.split("/")[1]} />
                            <input type="text" className="form-control fecha" placeholder="Año" id="anio_nacimiento_config" defaultValue={props?.userAccountData.fechaNacimiento.split("/")[2]} /> 
                            </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Identificacion</label>
                            <div id="identificacion">
                                <AccountSelectId defaultValue={props?.userAccountData.tipoDocumento}/>
                                <input type="text" className="form-control" placeholder="Numero de Identificacion" id="numeroid_config" defaultValue={props?.userAccountData.numeroDocumento} />
                            </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Genero</label>
                            <div id="genero">
                                <input id="genero_hombre" type="radio" name="gender" value="hombre" />
                                <label htmlFor="hombre" className="form-control genero">Hombre</label>
                                <input id="genero_mujer" type="radio" name="gender" value="mujer" />
                                <label htmlFor="mujer" className="form-control genero">Mujer</label>
                                <input id="genero_otro" type="radio" name="gender" value="otro"/>
                                <label htmlFor="otro" className="form-control genero">Otro</label>
                            </div>
                    </div>
                </div>
                <div className="col-md-12">
                    <div className="form-group">
                            <label>Sobre mi</label>
                        <textarea className="form-control" rows="4"></textarea>
                    </div>
                </div>
            </div>
            <div>
                <button className={`btn btn-primary update-submit ${props?.buttonDisable}`} onClick={(e)=>props?.onSubmit(collectData(e))}>
                    {props?.buttonDisable?
                    <> 
                        <FontAwesomeIcon icon={faSpinner} className="spinner-fetch" />
                        Actualizando 
                    </>
                    : "Actualizar"}
                </button>
                <button className="btn btn-light">Cancelar</button>
            </div>
        </div>
    );
}

export default AccountTab;