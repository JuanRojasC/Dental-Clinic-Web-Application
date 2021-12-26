import fetchDelete from '../../../Utilities/fetchDelete';
import { useState } from "react";
import { useHistory } from "react-router";

function ApplicationTab(props){

    let [linkAPIDelete] = useState(`${sessionStorage.getItem("linkAPI")}/${props?.userAccountData.usuario}/${props?.userAccountData.id}`);
    const history = useHistory();

    let deleteUser = async () => {
        let response = await fetchDelete(linkAPIDelete);
        if(response){
            history.push("/admin/home");
            window.location.reload();
            return;
        }
        alert("El usuario no pudo ser eliminado");
    }

    return(
        <div className="tab-pane fade" id="application-tab" role="tabpanel" aria-labelledby="application-tab">
            <h3 className="mb-4">Ajustes</h3>
            <div>
                <button className="btn btn-primary" onClick={deleteUser} >Borrar Cuenta</button>
            </div>
        </div>
    );
}

export default ApplicationTab;