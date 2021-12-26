let getDataAPI = async (link, token) => {
    let status = 0;
    try{
        const request = await fetch(link, {
            method: "GET",
            headers:{
                "Content-type": "application/json",
                "Authorization": token
            }
        });
        const response = await request.json();
        status = await response.status;
        if(((status >= 400 && status < 500) && status !== 0) || status === 0){
            return [];
        }
        return await response;
    }catch(e){
        console.log("FAILED CONECTION API\n" + e + "\nlink:" + link)
        return [];
    }
}

export default getDataAPI;