import { useEffect, useState } from "react";
import typeIdValues from "../../../Utilities/typeIdValues.json";

function AccountSelectId(props){

    let [selectOptions, setSelectOptions] = useState();
    let [option, setOption] = useState();
    let [widthSelect, setWidthSelect] = useState(55);
    let [defaultValue] = useState(props?.defaultValue);
    let [defaultId] = useState(typeIdValues.find((id)=>id.value===parseInt(props?.defaultValue)).nick);
    let [didMount, setDidMount] = useState(false);

    useEffect(()=>{
        setDidMount(true);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[])

    useEffect(()=>{
        setSelectOptions(document.querySelector("#tipo_dni_account_tab"));
        setOption(defaultId);
        setWidthSelect(document.querySelector("#select-temp").offsetWidth);
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [didMount])

    useEffect(()=>{
        // console.log("width: " + widthSelect)
        let indexOption = parseInt(selectOptions?.value);
        let index = 0;
        if(selectOptions)
            for(let opt of selectOptions.options){
                if(index===indexOption){
                    opt.innerHTML = `${typeIdValues.find((id)=>id.value===parseInt(opt.value)).nick} &nbsp;&nbsp;&#xf078`;
                }else{
                    opt.innerHTML = `${typeIdValues.find((id)=>id.value===parseInt(opt.value)).name}`;
                }
                index++;
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    },[option])

    useEffect(()=>{
    },[widthSelect])

    let selectOption = (e) => {
        setSelectOptions(e.target);
        setOption(e?.target.options[e.target.value].textContent);
        setWidthSelect(document.querySelector("#select-temp").offsetWidth);  
    }
    
    return(
        <>
            <select name="tipo_dni" id="tipo_dni_account_tab" defaultValue={defaultValue} onChange={(e)=>selectOption(e)} style={{width: widthSelect}}>
                <option value="0" id="default_select" hidden disabled>Tipo &nbsp;&nbsp;&#xf078;</option>
                <option value="1" data-text="Libreta Electoral">Libreta Electoral</option>
                <option value="2" data-text="Carnet de Extranjeria">Carnet de Extranjeria</option>
                <option value="3" data-text="Registro Unico de Contribuyentes">Registro Unico de Contribuyentes</option>
                <option value="4" data-text="Pasaporte">Pasaporte</option>
                <option value="5" data-text="Partida de Nacimiento">Partida de Nacimiento</option>
            </select>
            <select id="select-temp" style={{ visibility: "hidden", position: "absolute"}}>
                <option>{option? option.split(" ")[0] : defaultValue.split(" ")[0]} &nbsp;&nbsp;&#xf078;</option>
            </select>
        </>
    );
}

export default AccountSelectId;