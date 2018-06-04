document.addEventListener("DOMContentLoaded", onLoad);

let user;

function onLoad(){
    document.getElementById("loginButton").addEventListener("click",login);
    document.getElementById("territories").addEventListener("click",onTerritoriesClicked);
    document.getElementById("subordinates").addEventListener("click",onSubordinatesClicked);
    document.getElementById("myTerritories").addEventListener("click",onMyTerritoriesClicked);
    document.getElementById("addNewTerritory").addEventListener("click",onAddTerritoryClicked);
    document.getElementById("sendTerritory").addEventListener("click",addTerritory);


}

function onAddTerritoryClicked(){
   showContents(["territoryAddingDiv"]);
}

function addTerritory(){
        const params = new URLSearchParams();
        params.append("territoryName",document.getElementById("territoryNameField").value);
        const xhr = new XMLHttpRequest();
        xhr.addEventListener("load",backToProfile);
        xhr.open('PUT', 'territoriesServlet');
        xhr.send(params);

}

function onTerritoriesClicked(){
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load",showTerritories);
    xhr.open('GET', 'territoriesServlet');
    xhr.send();

}

function onMyTerritoriesClicked(){
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load",showMyTerritories);
    xhr.open('POST', 'territoriesServlet');
    xhr.send();

}

function backToProfile(){
    if(user.companyName != null){
        showContents(["profileDiv"]);
    }else{
        showContents(["profileDiv","employeeOnly"]);
    }

}

function showMyTerritories(){
        const territories = JSON.parse(this.responseText);
        const territoryDiv = document.getElementById("myTerritoriesDiv");
        const backToProfileButton = document.createElement("button");
        backToProfileButton.innerHTML = "Back to profile";
        backToProfileButton.addEventListener("click", backToProfile);
        territoryDiv.appendChild(backToProfileButton);
        showContents(["myTerritoriesDiv"]);
        const territoriesTable = document.getElementById("myTerritoryTable");

        for(let i=0;i<territories.length;i++){
            const tr = document.createElement("tr");

            const territoryIdTd = document.createElement("td");
            const territoryDescriptionTd = document.createElement("td");
            const regionIdTd = document.createElement("td");
            const regionDescriptionTd = document.createElement("td");


            territoryIdTd.innerHTML = territories[i].id;
            territoryDescriptionTd.innerHTML = territories[i].description;
            regionIdTd.innerHTML = territories[i].regionId;
            regionDescriptionTd.innerHTML = territories[i].regionDescription;

            tr.appendChild(territoryIdTd);
            tr.appendChild(territoryDescriptionTd);
            tr.appendChild(regionIdTd);
            tr.appendChild(regionDescriptionTd);


            territoriesTable.appendChild(tr);

        }
    }


function onSubordinatesClicked(){
    const params = new URLSearchParams();

    console.log(document.getElementById("userId").value);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load",showSubordinates);
    xhr.open('POST', 'subordinatesServlet');
    xhr.send(params);

}

function showSubordinates(){

    const subs = JSON.parse(this.responseText);
    const subsDiv = document.getElementById("subordinatesDiv");
    showContents(["subordinatesDiv"]);
    const backToProfileButton = document.createElement("button");
    backToProfileButton.innerHTML = "Back to profile";
    backToProfileButton.addEventListener("click", backToProfile);
    subsDiv.appendChild(backToProfileButton);
    const subsTable = document.getElementById("subordinateTable");

    for(let i=0;i<subs.length;i++){
        const tr = document.createElement("tr");

        const firstNameTd = document.createElement("td");
        const lastNameTd = document.createElement("td");
        const title = document.createElement("td");
        const fullAddressTd = document.createElement("td");
        const birthDateTd = document.createElement("td");
        const hireDateTd = document.createElement("td");
        const numOfTerTd = document.createElement("td");


        firstNameTd.innerHTML = subs[i].firstName;
        lastNameTd.innerHTML = subs[i].lastName;
        title.innerHTML = subs[i].title;
        fullAddressTd.innerHTML = subs[i].fullAddress;
        birthDateTd.innerHTML = subs[i].birthDate;
        hireDateTd.innerHTML = subs[i].hireDate;
        numOfTerTd.innerHTML = subs[i].numberOfTerritories;


        tr.appendChild(firstNameTd);
        tr.appendChild(lastNameTd);
        tr.appendChild(title);
        tr.appendChild(fullAddressTd);
        tr.appendChild(birthDateTd);
        tr.appendChild(hireDateTd);
        tr.appendChild(numOfTerTd);


        subsTable.appendChild(tr);

    }

}

function showTerritories(){
    const territories = JSON.parse(this.responseText);
    const territoryDiv = document.getElementById("territoriesDiv");
    showContents(["territoriesDiv"]);

    const backToProfileButton = document.createElement("button");
    backToProfileButton.innerHTML = "Back to profile";
    backToProfileButton.addEventListener("click", backToProfile);
    territoryDiv.appendChild(backToProfileButton);
    const territoriesTable = document.getElementById("territoryTable");

    for(let i=0;i<territories.length;i++){
        const tr = document.createElement("tr");

        const territoryIdTd = document.createElement("td");
        const territoryDescriptionTd = document.createElement("td");
        const regionIdTd = document.createElement("td");
        const regionDescriptionTd = document.createElement("td");


        territoryIdTd.innerHTML = territories[i].id;
        territoryDescriptionTd.innerHTML = territories[i].description;
        regionIdTd.innerHTML = territories[i].regionId;
        regionDescriptionTd.innerHTML = territories[i].regionDescription;

        tr.appendChild(territoryIdTd);
        tr.appendChild(territoryDescriptionTd);
        tr.appendChild(regionIdTd);
        tr.appendChild(regionDescriptionTd);


        territoriesTable.appendChild(tr);

    }
}

function login(){

    const params = new URLSearchParams();
    params.append("supplierId", document.getElementById("supplierId").value);
    params.append("employeeId", document.getElementById("employeeId").value);
    showContents(["profileDiv"]);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener("load",showProfile);
    xhr.open('POST', 'login');

    xhr.send(params);

}

function showProfile(){
    user = JSON.parse(this.responseText);
    if(user.companyName != null){
        showContents(["profileDiv"]);
    }else{
        showContents(["profileDiv","employeeOnly"]);
    }

}


function showContents(ids) {
    const contentEls = document.getElementsByClassName('content');
    for (let i = 0; i < contentEls.length; i++) {
        const contentEl = contentEls[i];
        if (ids.includes(contentEl.id)) {
            contentEl.classList.remove('hidden');
        } else {
            contentEl.classList.add('hidden');
        }
    }
}

