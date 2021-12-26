import { useState, useEffect } from "react";

function useFetchPut(props){
    let [link, setLink] = useState(props?.linkAPI);
    let [userData, setUserData] = useState(null)
    let [reponsePut, setResponsePut] = useState(null);
    let [, setFetching] = useState(false);


    let setData = (d) => {
        setUserData(d.data);
        setLink(d.linkAPI);
    }

    useEffect(()=>{
        let getFetch = async () => {
            if(!link) return;
            setFetching(true);
            try{
                const request = await fetch(link, {
                    method: "PUT",
                    headers: {
                        "Content-type": "application/json",
                        "Authorization": sessionStorage.getItem("jwt")
                    },
                    body: JSON.stringify(userData)
                });
                const status = request.status;
                const response = await request.json();
                if(status === 200){
                    setResponsePut(Array.isArray(response)? response : [response]);
                }else{
                    setResponsePut(false)
                }
            }catch(e){
                // if(link) console.log(e)
            }finally{
                setFetching(false);
            }
        }

        getFetch();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [link])

    return [ reponsePut, setData ]
}

export default useFetchPut;