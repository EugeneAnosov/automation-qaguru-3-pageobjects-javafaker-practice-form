function getDataFromTable(tr, td, keyTd = 0, valueTd = 1){
jsonObject = {};
Array.from(document.querySelectorAll(tr)).forEach(e =>{
        tds =  e.querySelectorAll(td);
        if (typeof tds[keyTd] != "undefined") { //if th skip
            jsonObject[tds[keyTd].innerHTML] = tds[valueTd].innerHTML;
        }
    }
);
return JSON.stringify(jsonObject);
}