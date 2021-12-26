import { useState, useEffect } from "react";

function useFetch(props){
    let [link, setLink] = useState(props?.linkAPI);
    let [reponseGet, setResponseGet] = useState(null);
    let [, setFetching] = useState(false);

    useEffect(()=>{
        let getFetch = async () => {
            setFetching(true);
            try{
                const request = await fetch(link, {
                    method: "GET",
                    headers: {
                        "Content-type": "application/json",
                        "Authorization": sessionStorage.getItem("jwt")
                    }
                });
                const status = request.status;
                const response = await request.json();
                if(status === 200){
                    setResponseGet(Array.isArray(response)? response : [response]);
                }else{
                    setResponseGet(false)
                }
            }catch(e){
                // if(link) console.log(e)
            }finally{
                setFetching(false);
            }
        }

        getFetch();
    }, [link])

    return [ reponseGet, setLink, setResponseGet ]
}

export default useFetch;