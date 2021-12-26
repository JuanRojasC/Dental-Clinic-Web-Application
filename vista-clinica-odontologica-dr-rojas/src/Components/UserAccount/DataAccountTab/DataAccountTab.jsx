import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner, faCheck } from '@fortawesome/free-solid-svg-icons';

function DataAccountTab(props){

    let collectData = (e) => {
        let node = e.target.parentNode.parentNode;
        return {
            id: props?.userAccountData.id,
            nombre: props?.userAccountData.nombre,
            apellido: props?.userAccountData.apellido,
            usuario: props?.userAccountData.usuario,
            fechaNacimiento: props?.userAccountData.fechaNacimiento,
            genero: props?.userAccountData.genero,
            tipoDocumento: props?.userAccountData.tipoDocumento,
            numeroDocumento: props?.userAccountData.numeroDocumento,
            domicilio: {
                id: props?.userAccountData.domicilio.id,
                calle: node.querySelector("#calle-config").value,
                numero: node.querySelector("#numero-calle-config").value,
                localidad: node.querySelector("#localidad-config").value,
                provincia: node.querySelector("#provincia-config").value
            }
        }
    }

    return(
        <div className="tab-pane fade" id="security-tab" role="tabpanel" aria-labelledby="security-tab">
            <h3 className="mb-4">
                Informacion
                {!props?.buttonDisable? <FontAwesomeIcon icon={faCheck} className="changes-saved" /> : ""}
            </h3>
            <h4 className="mb-4">Domicilio</h4>
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Calle</label>
                            <input type="text" className="form-control" id="calle-config" defaultValue={props?.userAccountData.domicilio.calle} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                        <label>Numero</label>
                        <input type="text" className="form-control" id="numero-calle-config" defaultValue={props?.userAccountData.domicilio.numero} />
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Localidad</label>
                            <input type="text" className="form-control" id="localidad-config" defaultValue={props?.userAccountData.domicilio.localidad} />
                    </div>
                </div>
                <div className="col-md-6">
                    <div className="form-group">
                            <label>Provincia</label>
                            <input type="text" className="form-control" id="provincia-config" defaultValue={props?.userAccountData.domicilio.provincia} />
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

export default DataAccountTab;