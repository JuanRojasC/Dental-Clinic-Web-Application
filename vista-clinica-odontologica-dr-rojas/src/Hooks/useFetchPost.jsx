import { useState, useEffect } from "react";

function useFetchPost(props){
    let [link, setLink] = useState(props?.linkAPI);
    let [userData, setUserData] = useState(null)
    let [reponsePost, setResponsePost] = useState(null);
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
                    method: "POST",
                    headers: {
                        "Content-type": "application/json",
                        "Authorization": sessionStorage.getItem("jwt")
                    },
                    body: JSON.stringify(userData)
                });
                const status = request.status;
                const response = await request.json();
                if(status === 200){
                    setResponsePost(Array.isArray(response)? response : [response]);
                }else{
                    setResponsePost(false)
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

    return [ reponsePost, setData ]
}

export default useFetchPost;