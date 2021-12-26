
async function fetchDelete(link){
    const request = await fetch(link, {
        method: "DELETE",
        headers: {
            "Content-type": "application/json",
            "Authorization": sessionStorage.getItem("jwt")
        }
    });
    const status = request.status;
    return status === 200? true : false;
}

export default fetchDelete;