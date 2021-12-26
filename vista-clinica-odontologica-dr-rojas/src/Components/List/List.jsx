import "./list.css";
import { useState, useEffect } from "react";
import getDataAPI from "../../Utilities/getDataList";
import RowList from "./RowList/RowList";
import typeIdValues from "../../Utilities/typeIdValues.json";
import typeStudiesValues from "../../Utilities/typeStudiesValues.json";

function List(props){

    let [linkAPI, setLinkAPI] = useState("")
    let [columns, setColumns] = useState([]);
    let [rows, setRows] = useState([]);
    let [update, setUpdate] = useState(false);

    let dataFetch = async () =>{
        let dataResponse = await getDataAPI(linkAPI, sessionStorage.getItem("jwt"));
        if(dataResponse !== []){
            if(props.title ==="Pacientes") dataResponse = dataPacientes(dataResponse)
            if(props.title ==="Empleados") dataResponse = dataOdontologos(dataResponse)
            if(props.title === "Turnos") dataResponse = dataTurnos(dataResponse)
            setRows(dataResponse);
            setUpdate(false)
        }
        return dataResponse;
    }

    useEffect(()=>{
        if(linkAPI !== "") dataFetch();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [linkAPI])

    useEffect(()=>{
        setLinkAPI(sessionStorage.getItem("linkAPI")+props.linkAPI);
        setColumns(props.headers.map((h)=>h.toLowerCase()))
    }, [props.linkAPI, props.headers])

    useEffect(()=>{if(linkAPI !== "") dataFetch()},[update])

    let updateComponent = () => {
        setUpdate(true)
    }

    // Desglosa la informacion recibida de cada Paciente para ser mostrada en la lista
    let dataPacientes = (data) => {
        return data.map(function(p){
            return {            
                id: p.id, 
                paciente: `${p.nombre} ${p.apellido}`, 
                documento: `${typeIdValues.find((id)=> id.value === parseInt(p.tipoDocumento)).name}`,
                numero:  p.numeroDocumento,
                domicilio:`${p.domicilio.calle} ${p.domicilio.numero} - ${p.domicilio.localidad} / ${p.domicilio.provincia}`
            }
        })
    }

    // Desglosa la informacion recibida de cada Odontologo para ser mostrada en la lista
    let dataOdontologos = (data) => {
        return data.map(function(o){
            return {
                id: o.id, 
                empleado: `${o.nombre} ${o.apellido}`, 
                matricula: o.numeroMatricula,
                grado: typeStudiesValues.find((type)=>type.value===parseInt(o.tipoTitulo)).name,
                titulo: o.titulo
            }
        })
    }

    // Desglosa la informacion recibida de cada Turno para ser mostrada en la lista
    let dataTurnos = (data) => {
        return data.map(function(t){
            return {
                id: t.id, 
                paciente: `${t.paciente.nombre} ${t.paciente.apellido}`, 
                odontologo: `${t.odontologo.nombre} ${t.odontologo.apellido}`,
                fecha: t.fechaTurno
            }
        })
    }

    return(
        <>     
            <div className={`list list-${props.title.toLowerCase()}`}>
                <h2 className="list-title">{`${props.title} Registrados`}</h2>
                <button className="button-add-new-element" onClick={props?.onClickAdd}>{`Nuevo ${props.title.split("s")[0]}`}</button>
                <ul className="responsive-table">
                    <li className="responsive-table-header responsive-table-li">
                        {props.headers?.map((header, index)=>
                            <div className={`col responsive-table-col-${columns[index]}`} key={index}>{header}</div>
                        )}
                    </li>
                    {rows.map((row, index) => <RowList data={row} headers={columns} user={props.title.split("s")[0].toLowerCase()} key={index}  onClickRow={props.onClickRow} updateParent={updateComponent}/>)}
                </ul>
            </div>
        </>
  );
}


export default List;