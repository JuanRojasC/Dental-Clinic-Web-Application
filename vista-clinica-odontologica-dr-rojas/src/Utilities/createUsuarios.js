import pacientesList from "./pacientes.json";
import odontologosList from "./odontologos.json";

async function createPacientes(){
    for(let p of pacientesList){
        try{
            const request = await fetch("http://localhost:8080/signup/paciente", {
            method: "POST",
            headers:{
                "Content-type": "application/json",
            },
            body: JSON.stringify(p) 
            })
            const status = await request.json();
            return status;    
        }catch(e){
            console.log(e)
        }
    }
}

async function createOdontologos(){
    for(let o of odontologosList){
        try{
            const request = await fetch("http://localhost:8080/signup/odontologo", {
            method: "POST",
            headers:{
                "Content-type": "application/json",
            },
            body: JSON.stringify(o) 
            })
            const status = await request.json();
            return status; 
        }catch(e){
            console.log(e)
        }
    }
}

export {createPacientes, createOdontologos};