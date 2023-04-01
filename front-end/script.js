
const addBtn = document.querySelector(".add");
const saveBtn = document.querySelector("#saveButton");
const connectBtn = document.querySelector("#connectButton");

const row = document.querySelector(".container .row");
const inputList = row.querySelectorAll('.inputs');

stringArr = []

inputList.forEach( item => {stringArr.push(item.value)})


function addInput(){

    const row = document.querySelector(".container .row");

    const col = document.createElement("div");
    col.classList.add("col");
    row.appendChild(col);

    const div1 = document.createElement("div");
    div1.classList.add("input-group");
    div1.classList.add("input-group-sm");
    div1.classList.add("mt-5");
    div1.classList.add("m-3");
    col.appendChild(div1);

    const words = document.createElement("input");
    words.type = "text"; 
    words.classList.add("form-control");
    words.classList.add("inputs");
    div1.appendChild(words);
    console.log("input eklendi")

}

addBtn.addEventListener("click",addInput);

    
function saveInput(){
    
    const row = document.querySelector(".container .row");
    const inputs = row.querySelectorAll('.inputs');

    stringArr = []

    inputs.forEach( item => {stringArr.push(item.value)})

    for(let i=0;i<inputs.length;i++){
        console.log(stringArr);
    }

    axios.post('http://localhost:8072/text/add',{
        input:stringArr
    }).then(response => {
        console.log(response);
    }).catch(err => {
        console.log(err);
    });


}

saveBtn.addEventListener("click", saveInput);

const connect = () =>{
    axios.get('http://localhost:8072/text/get').then(response =>{
        const data = response.data
        var dataLen = data.length
        var output1 = data[dataLen-1].output
        var time1 = data[dataLen-1].time
        console.log(data)
        console.log(output1)
        console.log(time1)
        document.querySelector(".output").innerHTML = output1
        document.querySelector(".time").innerHTML = time1
    })
};


connectBtn.addEventListener('click', connect);