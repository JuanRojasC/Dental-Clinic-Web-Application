import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUsersCog, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import "./row-option.css";
import { useEffect, useState } from 'react/cjs/react.development';
import fetchDelete from '../../../Utilities/fetchDelete';

function RowOption({ userId, user, onClickOption, updateParent }){

    let [linkAPIDelete, setLinkAPIDelete] = useState(`${sessionStorage.getItem("linkAPI")}/${user}/${userId}`);

    let deleteUser = async () => {
        let response = await fetchDelete(linkAPIDelete);
        if(response){
            updateParent(true)
        }
    }

    useEffect(()=>{setLinkAPIDelete(`${sessionStorage.getItem("linkAPI")}/${user}/${userId}`)},[user, userId])

    return(
        <div className="list-row-option">
            <FontAwesomeIcon icon={faUsersCog} className="i row-option-icon-setting" data-id={userId} onClick={onClickOption} />
            <FontAwesomeIcon icon={faTrashAlt} className="i row-option-icon-trash" onClick={updateParent} />
        </div>
    );
}

export default RowOption;