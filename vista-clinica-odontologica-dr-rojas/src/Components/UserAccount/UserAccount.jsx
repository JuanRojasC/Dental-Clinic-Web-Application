import "./user-account.css";
import LateralMenu from './LateralMenu/LateralMenu';
import AccountTab from './AccountTab/AccountTab';
import PasswordTab from './PasswordTab/PasswordTab';
import DataAccountTab from './DataAccountTab/DataAccountTab';
import ApplicationTab from "./ApplicationTab/ApplicationTab";
import { useState } from 'react';
import { useEffect } from 'react';
import useFetchPut from "../../Hooks/useFetchPut";

function UserAccount(props){

    let [currentTab, setCurrentTab] = useState("account-tab");
    let [lastTab, setLastTab] = useState("account-tab");
    let [userData, setUserData] = useState(props?.userData);
    let [userSelected, setUserSelected] = useFetchPut();
    let [fetching, setFetching] = useState(false);

    useEffect(()=>{
        let optionSelected = (tab) => {
            let nodeCurrentOption = document.querySelector(`#${tab}`);
            let nodeLastOption = document.querySelector(`#${lastTab}`)
            // Style for the current Option
            nodeCurrentOption.style.backgroundColor = "#009FD6";
            nodeCurrentOption.style.color = "white";
            // Style for the last Option
            nodeLastOption.style.backgroundColor = "#ffffff";
            nodeLastOption.style.color = "black";
        }
        optionSelected(currentTab)
    },[currentTab, lastTab])

    useEffect(()=>{
        setFetching(false);
        if(userSelected) setUserData(userSelected[0])
    }, [userSelected])

    let renderTab = (tab) => {
        let data;
        switch(tab){
            case "account-tab":
                return <AccountTab userAccountData={userData} onSubmit={updateData} buttonDisable={fetching? "update-button-disable" : ""}/>;
            case "password-tab":
                return <PasswordTab  onSubmit={updateData} userAccountData={userData} buttonDisable={fetching? "update-button-disable" : ""}/>;
            case "security-tab":
                return <DataAccountTab userAccountData={userData} onSubmit={updateData} buttonDisable={fetching? "update-button-disable" : ""}/>;
            case "application-tab":
                data = {
                    id: userData?.id,
                    usuario: userData?.usuario.rol.name.toLowerCase()
                }
                return <ApplicationTab userAccountData={data}/>
            default:
                break;
        }
    }

    let manageTab = (e) => {
        if(currentTab===lastTab) {
            setCurrentTab(e.target.id)
        }else {
            setLastTab(currentTab);
            setCurrentTab(e.target.id)
        }
    }

    let updateData = (e) => {
        setFetching(true);
        let url = sessionStorage.getItem("linkAPI")+"/"+e.usuario.rol.name.toLowerCase()+"/settings"
        setUserSelected({data: e , linkAPI: url})
    }

    return(
        <section className="py-5 my-5" id="user-account">
            <div className="contianer-user-account" id="container-user-account">
                <div className="bg-white shadow rounded-lg d-block d-sm-flex" id="container-settings">
                    <LateralMenu  onClickOption={(e)=>manageTab(e)} />
                    <div className="tab-content p-4 p-md-5" id="v-pills-tabContent">
                        {renderTab(currentTab)}
                    </div>
                </div>
            </div>
        </section>
    );
}

export default UserAccount;