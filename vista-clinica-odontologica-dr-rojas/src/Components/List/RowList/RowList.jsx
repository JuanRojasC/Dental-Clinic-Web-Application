import "./row-list.css";
import RowOption from "../RowOption/RowOption";

function RowList({ data, headers, onClickRow, user, updateParent }){
    return(        
        <li className="responsive-table-row responsive-table-li" data-id={data.id} onClick={onClickRow}>
            {Object.values(data).map((d, index)=>
                <div className={`col responsive-table-col-${headers[index]}`} data-label={headers[index]} key={index} data-id={data.id}>
                    {d}
                </div>
            )}
            <RowOption userId={data.id} user={user==="empleado"? "odontologo" : user} onClickOption={onClickRow} updateParent={updateParent} />
        </li>
    );
}

export default RowList;