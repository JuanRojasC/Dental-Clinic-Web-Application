import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner, faCheck } from '@fortawesome/free-solid-svg-icons';

function PasswordTab(props){

    let collectData = (e) => {
        let node = e.target.parentNode.parentNode;
        return {
            id: props?.userAccountData.id,
            nombre: props?.userAccountData.nombre,
            apellido: props?.userAccountData.apellido,
            usuario: {
                email: props?.userAccountData.usuario.email,
                password: node.querySelector("#new-password-config").value,
                rol: props?.userAccountData.usuario.rol
            },
            fechaNacimiento: props?.userAccountData.fechaNacimiento,
            genero: props?.userAccountData.genero,
            tipoDocumento: props?.userAccountData.tipoDocumento,
            numeroDocumento: props?.userAccountData.numeroDocumento,
            domicilio: props?.userAccountData.domicilio,
        }
    }

    return(
        <div className="tab-pane fade" id="password" role="tabpanel" aria-labelledby="password-tab">
        <h3 className="mb-4">
            Contraseña
            {!props?.buttonDisable? <FontAwesomeIcon icon={faCheck} className="changes-saved" /> : ""}
        </h3>
        <div className="row">
            <div className="col-md-6">
                <div className="form-group">
                      <label>Actual contraseña</label>
                      <input type="password" className="form-control" />
                </div>
            </div>
        </div>
        <div className="row">
            <div className="col-md-6">
                <div className="form-group">
                      <label>Nueva Contraseña</label>
                      <input type="password" className="form-control" id="new-password-config" />
                </div>
            </div>
            <div className="col-md-6">
                <div className="form-group">
                      <label>Confirmar Nueva Contraseña</label>
                      <input type="password" className="form-control" />
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

export default PasswordTab;